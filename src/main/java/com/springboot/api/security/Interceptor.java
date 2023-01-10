package com.springboot.api.security;

import org.apache.commons.logging.Log;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Interceptor implements HandlerInterceptor {

    // aqui nao pode ter o autowired se n da erro de cycle
    // interceptor é os middlewares in spring boot
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
                
                System.out.println("EXEMPLOEXEMPLOEXEMPLOEXEMPLOEXEMPLO");
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {
                
                
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex)
            throws Exception {
                System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
    }
}