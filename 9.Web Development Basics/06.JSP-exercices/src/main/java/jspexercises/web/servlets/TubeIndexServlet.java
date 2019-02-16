package chushka.web.servlets;

import chushka.service.TubeService;
import chushka.util.HtmlReader;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class TubeIndexServlet extends HttpServlet {

    private final TubeService tubeService;

    @Inject
    public TubeIndexServlet(TubeService productService) {
        this.tubeService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/index.jsp")
                .forward(req, resp);
    }
}
