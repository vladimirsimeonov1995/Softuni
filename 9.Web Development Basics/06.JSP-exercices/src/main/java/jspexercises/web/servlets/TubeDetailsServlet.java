package chushka.web.servlets;

import chushka.domain.models.binding.TubeCreateBindingModel;
import chushka.service.TubeService;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/details")
public class TubeDetailsServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeDetailsServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String name = req
                .getQueryString()
                .split("=")[1]
                .replace("%20", " ");

        TubeCreateBindingModel model = modelMapper.map(tubeService.getByName(name), TubeCreateBindingModel.class);

        req.setAttribute("viewModel", model);

        req.getRequestDispatcher("/jsps/details-tube.jsp")
                .forward(req, res);

    }
}
