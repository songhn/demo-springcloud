package com.songhn.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

// @Component
public class AccessFilter extends ZuulFilter {

    @Override
    public String filterType() {
        // ”pre”、”route”、”post”、”error”
        // pre:可以在请求被路由之前调用
        // routing:在路由请求时被调用
        // post:在routing和error过滤器之后被调用
        // error:处理请求时发生错误时被调用
        return "pre";
    }

    @Override
    public int filterOrder() {
        // 通过int值来定义过滤器的执行顺序，数值越小优先级越高
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 返回一个布尔值来判断该过滤器是否要执行。我们可以通过此方法指定过滤器的有效范围
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 过滤器的具体业务逻辑。
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();

        Object object = httpServletRequest.getParameter("token");
        if (null == object) {
            System.out.println("token is null");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            return null;
        }else {
            // 设置当前请求是否被路由
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(200);
        }
        System.out.println("token is " + String.valueOf(object));
        return null;
    }
}
