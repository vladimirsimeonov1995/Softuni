package chushka.web.servlets;

import chushka.service.ProductService;
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
public class IndexServlet extends HttpServlet {

    private final static String INDEX_HTML_FILE_PATH = "C:\\Users\\Vlad\\Desktop\\chushka\\src\\main\\resources\\views\\index.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Inject
    public IndexServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String htmlFileContent = htmlReader.readHtmlFile(INDEX_HTML_FILE_PATH);

        String productList = createProductsList();

        htmlFileContent = htmlFileContent.replace("{{ListOfProducts}}", productList);

        res.getWriter().println(htmlFileContent);
    }

    private String createProductsList() {
        StringBuilder productList = new StringBuilder();

        productService.getAll()
                .forEach(psm -> {
                    productList.append(String.format("<li><a href=/products/details?name=%s>%s</a></li>", psm.getName(), psm.getName()));
                });

        return productList.toString();

    }
}
