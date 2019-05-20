package com.songhn.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : songhn
 * @className: AuthTokenInterceptor
 * @company : Boboqi Software Co.,Ltd.
 * @date : 2019-05-20 19:29 
 * @description: <p>TODO</p>
 */
@Component
public class AuthTokenInterceptor implements HandlerInterceptor {

    public AuthTokenInterceptor(){
        System.out.println("我是AuthTokenInterceptor");
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("=======我是拦截器=======");
        String token = httpServletRequest.getParameter("token");
        if(null == token){
            System.out.println("拦截器未通过，Token验证失败！");
            return false;
        }
        System.out.println("拦截器通过，Token=" + token);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
