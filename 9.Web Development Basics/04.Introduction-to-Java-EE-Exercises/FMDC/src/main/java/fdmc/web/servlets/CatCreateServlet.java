package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.WriteAbortedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@WebServlet("/cats/create")
public class CatCreateServlet extends HttpServlet {

    private final static String catCreateHtmlFilePath="C:\\Users\\Vlad\\Desktop\\FMDC\\src\\main\\resources\\views\\cat-create.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatCreateServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().println(htmlReader.readHtmlFile(catCreateHtmlFilePath));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Cat cat = new Cat();
        cat.setName(req.getParameter("name"));
        cat.setBreed(req.getParameter("breed"));
        cat.setColor(req.getParameter("color"));
        cat.setAge(Integer.parseInt(req.getParameter("age")));

        if(req.getSession().getAttribute("cats") == null){
            HashMap<String, Cat> cats = new LinkedHashMap();
            req.getSession().setAttribute("cats", cats);
        }

        ((HashMap<String, Cat>)req.getSession().getAttribute("cats")).putIfAbsent(cat.getName(), cat);

        res.sendRedirect(String.format("/cats/profile?catName=%s", cat.getName()));
    }
}
