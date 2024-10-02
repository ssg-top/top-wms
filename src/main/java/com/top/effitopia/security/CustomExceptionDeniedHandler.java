package com.top.effitopia.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
public class CustomExceptionDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("403 forbidden: 권한이 없는 사용자 (인가 실패)");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        String contentType = request.getHeader("Content-Type");

        if (contentType == null) {
            response.sendRedirect("/auth/login.html?error=ACCESS_DENIED");
            return;
        }
        boolean isJsonRequest = contentType.startsWith("application/json");
        if (!isJsonRequest) {
            response.sendRedirect("/auth/login.html?error=ACCESS_DENIED");
        } else {
            PrintWriter writer = response.getWriter();
            writer.println("권한이 없습니다.");
            writer.flush();
        }
    }
}
