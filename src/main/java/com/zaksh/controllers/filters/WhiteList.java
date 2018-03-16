package com.zaksh.controllers.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WhiteList implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String login = (String) ((HttpServletRequest) request)
                .getSession()
                .getAttribute("login");
        if(login != null) {
            chain.doFilter(request, response);
        }
        else {
            ((HttpServletResponse)response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/");
        }
    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig filterConfig) {}
}
