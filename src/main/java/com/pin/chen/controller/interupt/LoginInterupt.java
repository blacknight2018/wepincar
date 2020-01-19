package com.pin.chen.controller.interupt;

import com.pin.chen.utils.Response;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterupt implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //return true;
        if(request.getSession().getAttribute("openid")==null)
        {
            response.getWriter().println(new Response(Response.NOTLOGIN).getMsgString());

            return false;
        }
        else
            return true;
    }
}
