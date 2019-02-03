package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CatAllServlet extends HttpServlet {

    private final static String ALL_CATS_HTML_FILE_PATH = "C:\\Users\\Vlad\\Desktop\\FMDC\\src\\main\\resources\\views\\cat-all.html";

    private final HtmlReader reader;

    @Inject
    public CatAllServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String htmlFileContent = reader.readHtmlFile(ALL_CATS_HTML_FILE_PATH);


        if(req.getSession().getAttribute("cats") == null){

            htmlFileContent = htmlFileContent
                    .replace("{{allCats}}",
                            String.format("There are no cats. <a href=\"/cats/create\">Create some</a>"));
        }else {

            List<Cat> cats = new ArrayList<>(((Map<String, Cat>) req.getSession()
                    .getAttribute("cats"))
                    .values());

            StringBuilder sb = new StringBuilder();

            cats.stream().forEach(c -> {
                sb.append(String
                        .format("<a href=\"/cats/profile?catName=%s\">%s</a>",c.getName(), c.getName()))
                        .append("<br/>");
            });

            htmlFileContent = htmlFileContent
                    .replace("{{allCats}}", sb.toString());

        }

        res.getWriter().println(htmlFileContent);





    }
}
