package chushka.web.servlets;

import chushka.domain.models.binding.TubeCreateBindingModel;
import chushka.domain.models.service.TubeSurviceModel;
import chushka.service.TubeService;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/create")
public class TubeCreateServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper mapper;

    @Inject
    public TubeCreateServlet(TubeService tubeService, ModelMapper mapper) {
        this.tubeService = tubeService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/create-tube.jsp")
                .forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        TubeCreateBindingModel model = (TubeCreateBindingModel) req.getAttribute("createModel");

        tubeService.saveProduct(mapper.map(model, TubeSurviceModel.class));

        res.sendRedirect("/tubes/details?name=" + model.getName());
    }
}
