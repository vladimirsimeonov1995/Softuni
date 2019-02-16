package chushka.web.filters;

import chushka.domain.models.binding.TubeCreateBindingModel;
import chushka.service.TubeService;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/tubes/all")
public class TubeAllFilter implements Filter {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeAllFilter(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if(req.getMethod().toLowerCase().equals("get")){

            List<TubeCreateBindingModel> tcbms = new ArrayList<>();

            tubeService.getAll()
                    .forEach(model -> {
                        TubeCreateBindingModel tcbm = modelMapper.map(model, TubeCreateBindingModel.class);
                        tcbms.add(tcbm);
                    });

            req.setAttribute("tcbms", tcbms);
        }

        filterChain.doFilter(req, res);

    }
}
