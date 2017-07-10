package com.project.web.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by qiaowentao on 2016/8/19.
 */
public class AbstractInterceptor extends HandlerInterceptorAdapter {

    protected boolean checkTicketFromRedis(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return true;
    }

}
