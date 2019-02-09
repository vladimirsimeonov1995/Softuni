package chushka.web.servlets;

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

@WebServlet("/products/details")
public class ProductDetailsServlet extends HttpServlet {

    private static final String PRODUCT_DETAIL_FILE_PATH = "C:\\Users\\Vlad\\Desktop\\chushka\\src\\main\\resources\\views\\product-detail.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;

    @Inject
    public ProductDetailsServlet(ProductService productService, HtmlReader htmlReader) {
        this.productService = productService;
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getQueryString().split("=")[1];

        ProductServiceModel productModel = this.productService.getByName(name);

        res.getWriter().println(getHtml(productModel));
    }

    private String getHtml(ProductServiceModel productModel) throws IOException {

        String htmlFileContent = this.htmlReader.readHtmlFile(PRODUCT_DETAIL_FILE_PATH);

        htmlFileContent = htmlFileContent.replace("{{name}}", productModel.getName());
        htmlFileContent = htmlFileContent.replace("{{description}}", productModel.getDescription());
        htmlFileContent = htmlFileContent.replace("{{type}}", productModel.getType());

        return htmlFileContent;
    }
}
