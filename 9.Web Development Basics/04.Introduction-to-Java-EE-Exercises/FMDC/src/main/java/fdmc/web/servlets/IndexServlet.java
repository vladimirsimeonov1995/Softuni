package fdmc.web.servlets;

import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    private final static String indexHtmlFilePath = "C:\\Users\\Vlad\\Desktop\\FMDC\\src\\main\\resources\\views\\index.html";

    private final HtmlReader htmlReader;

    @Inject
    public IndexServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter writer = res.getWriter();

        writer.println(htmlReader.readHtmlFile(indexHtmlFilePath));

    }
}
