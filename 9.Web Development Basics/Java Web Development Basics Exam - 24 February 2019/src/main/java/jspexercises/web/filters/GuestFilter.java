package jspexercises.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({
    "/faces/view/home.xhtml", "/faces/view/schedule.xhtml", "/faces/view/print-document.xhtml"
        , "/faces/view/document-detail.xhtml"
})
public class GuestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (httpServletRequest.getSession().getAttribute("username") == null) {
            httpServletResponse.sendRedirect("/faces/view/login.xhtml");
        } else {
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

}
