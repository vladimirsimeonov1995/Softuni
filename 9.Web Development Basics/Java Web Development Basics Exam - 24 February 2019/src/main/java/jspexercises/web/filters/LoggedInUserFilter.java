package jspexercises.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({
        "/faces/view/index.xhtml", "/faces/view/login.xhtml", "/faces/view/register.xhtml"
})
public class LoggedInUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (httpServletRequest.getSession().getAttribute("username") != null) {
            httpServletResponse.sendRedirect("/faces/view/home.xhtml");
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
