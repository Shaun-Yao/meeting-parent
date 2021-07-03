package com.honji.meeting.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Component
public class SessionTimeoutInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException {

        HttpSession session = request.getSession();
        Object admin = session.getAttribute("admin");

        if ("/admin/toLogin".equals(request.getRequestURI())) {
            if (admin != null) { //已经登录转到首页
                response.sendRedirect("/admin/index");
            }
            return true;
        }

        if (admin != null) {

            return true;
        }

        response.sendRedirect("/admin/toLogin");

        return false;
    }

}
