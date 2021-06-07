package com.proyecto.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Permitimos las peticiones solamente de la url en origin.
 */

@Component
@Order(1)
public class CorsFilter implements Filter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${access.control.allow.origin}")
    private String origin;

    @Override
    public void init(final FilterConfig filterConfig) {
        log.info("Initializing filter :{}", this);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        setHeaders(response);
        chain.doFilter(req, res);
    }

    public void setHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

}