package com.songhn.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulHeaders;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.util.HTTPRequestUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

@Component
public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("Fileter = PostFileter");
        try {
            writeResponse();
        } catch (Exception ex) {
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }


    private void writeResponse() throws Exception {

        RequestContext context = RequestContext.getCurrentContext();
        // there is no body to send
        if (context.getResponseBody() == null
                && context.getResponseDataStream() == null) {
            return;
        }

        HttpServletResponse servletResponse = context.getResponse();
        if (servletResponse.getCharacterEncoding() == null) { // only set if not set
            servletResponse.setCharacterEncoding("UTF-8");
        }

        boolean isGzipRequested = false;
        final String requestEncoding = context.getRequest()
                .getHeader(ZuulHeaders.ACCEPT_ENCODING);

        if (requestEncoding != null
                && HTTPRequestUtils.getInstance().isGzipped(requestEncoding)) {
            isGzipRequested = true;
        }

        OutputStream outStream = servletResponse.getOutputStream();

        InputStream is = context.getResponseDataStream();
        InputStream inputStream = is;
        try {
            if (is != null) {
                if (context.sendZuulResponse()) {
                    if (context.getResponseGZipped() && !isGzipRequested) {
                        final Long len = context.getOriginContentLength();
                        if (len == null || len > 0) {
                            try {
                                inputStream = new GZIPInputStream(is);
                            } catch (java.util.zip.ZipException ex) {
                                System.out.println(
                                        "gzip expected but not "
                                                + "received assuming unencoded response "
                                                + RequestContext.getCurrentContext()
                                                .getRequest().getRequestURL()
                                                .toString());
                                inputStream = is;
                            }
                        } else {
                            // Already done : inputStream = is;
                        }
                    } else if (context.getResponseGZipped() && isGzipRequested) {
                        servletResponse.setHeader(ZuulHeaders.CONTENT_ENCODING, "gzip");
                    }
                    String responseBody = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
                    System.out.println("服务返回结果：" + responseBody);
                    writeResponse(context, responseBody);
                }
            }
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            try {
                Object zuulResponse = RequestContext.getCurrentContext().get("zuulResponse");
                if (zuulResponse instanceof Closeable) {
                    ((Closeable) zuulResponse).close();
                }
                outStream.flush();
            } catch (IOException ex) {
                System.out.println("Error while sending response to client: " + ex.getMessage());
            }
        }
        return;
    }

    private void writeResponse(RequestContext context, String responseBody) throws Exception {
        context.setResponseBody(responseBody);
    }
}
