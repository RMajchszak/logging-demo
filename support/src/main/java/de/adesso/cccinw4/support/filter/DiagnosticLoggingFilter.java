package de.adesso.cccinw4.support.filter;

import org.apache.logging.log4j.CloseableThreadContext;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Ein Servletfilter der Request-Daten in den MDC schreibt
 */
public class DiagnosticLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try (CloseableThreadContext.Instance ctx = CloseableThreadContext.putAll(logAttributes(request))) {
            chain.doFilter(request, response);
        }
    }

    private Map<String, String> logAttributes(ServletRequest request) {
        Map<String, String> attr = new HashMap<>();
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            attr.put("request_uri", httpServletRequest.getRequestURI());
            attr.put("actor", httpServletRequest.getUserPrincipal().getName());
            attr.put("method", httpServletRequest.getMethod());
        }
        return attr;
    }
}
