package com.taptake.backend.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoggerMiddleware implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(LoggerMiddleware.class);

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        log.info(
                String.format("%s %s",
                        Ansi.Cyan.format("[%s]", request.getMethod()), request.getRequestURI()));
        return true;
    }
}
