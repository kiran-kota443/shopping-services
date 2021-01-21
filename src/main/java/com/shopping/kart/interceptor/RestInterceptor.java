/**
 * 
 */
package com.shopping.kart.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Kiran Kumar Kota
 *
 * Jan 21, 2021 11:54:41 AM
 *
 * TODO
 *
 * 
 */
@Component
public class RestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger interceptorLogger = LoggerFactory.getLogger(RestInterceptor.class.getSimpleName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        interceptorLogger.info("URI : " + request.getRequestURI());
        interceptorLogger.info("Headers\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            interceptorLogger.info(headerNames.nextElement());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

}

