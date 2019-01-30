package fdmc.web.servlets;

import fdmc.web.domain.entities.Cat;
import fdmc.web.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cats/create")
public class CreateServlet extends HttpServlet {

    private final static String CREATE_HTML_FILE_PATH = "D:\\SoftUni\\Java-Web\\04. Introduction to Java EE\\fdmc\\src\\main\\resources\\views\\create.html";

    private final HtmlReader reader;
    @Inject
    public CreateServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println(reader.readHtmlFile(CREATE_HTML_FILE_PATH));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat = new Cat(req.getParameter("name"),
                req.getParameter("breed"), req.getParameter("color"),
                Integer.parseInt(req.getParameter("numberOfLegs")));

        if(req.getSession().getAttribute("cats") == null) {
            req.getSession().setAttribute("cats", new LinkedHashMap<>());
        }
        ((Map<String, Cat>)req.getSession().getAttribute("cats"))
                .putIfAbsent(cat.getName(), cat);

        resp.sendRedirect(String.format("/cats/profile?catName=%s", cat.getName()));
    }
}
