package chushka.web.filters;

import chushka.domain.models.binding.TubeCreateBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if(req.getMethod().toLowerCase().equals("post")) {
            TubeCreateBindingModel model = new TubeCreateBindingModel();

            model.setName(req.getParameter("title"));
            model.setDescription(req.getParameter("description"));
            model.setYoutubeLink(req.getParameter("youtubeLink"));
            model.setUploader(req.getParameter("uploader"));

            req.setAttribute("createModel", model);
        }

        filterChain.doFilter(req, res);
    }
}
