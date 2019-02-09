package chushka.web.servlets;

import chushka.domain.entities.enums.Type;
import chushka.domain.models.service.ProductServiceModel;
import chushka.service.ProductService;
import chushka.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/products/create")
public class CreateServlet extends HttpServlet {

    private static final String CREATE_PRODUCT_HTML_FILE_PATH = "C:\\Users\\Vlad\\Desktop\\chushka\\src\\main\\resources\\views\\create-product.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;

    @Inject
    public CreateServlet(ProductService productService, HtmlReader htmlReader) {
        this.productService = productService;
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String htmlFileContent = htmlReader.readHtmlFile(CREATE_PRODUCT_HTML_FILE_PATH);

        htmlFileContent = htmlFileContent.replace("{{typeOptions}}", formatTypeOptions()).trim();

        res.getWriter().println(htmlFileContent);

    }


    private String formatTypeOptions(){
        StringBuilder options = new StringBuilder();

        Arrays.stream(Type.values())
                .forEach(type -> {
                    options.append(String.format("<option value=\"%s\">%s</option>", type.name(), type.name()))
                            .append(System.lineSeparator());
                });

        return options.toString();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        ProductServiceModel productServiceModel = new ProductServiceModel();

        productServiceModel.setName(req.getParameter("name"));
        productServiceModel.setDescription(req.getParameter("description"));
        productServiceModel.setType(req.getParameter("typeSelect"));

        productService.saveProduct(productServiceModel);

        res.sendRedirect("/");

    }
}
