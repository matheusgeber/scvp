package com.scvp.config;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Object usuario = request.getSession().getAttribute("usuarioLogado");

        if (usuario == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}