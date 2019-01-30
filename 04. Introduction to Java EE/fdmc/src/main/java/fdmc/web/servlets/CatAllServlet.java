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
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@WebServlet("/cats/all")
public class CatAllServlet extends HttpServlet{

    private final HtmlReader reader;
    private final static String NO_CATS_HTML_FILE_PATH = "D:\\SoftUni\\Java-Web\\04. Introduction to Java EE\\fdmc\\src\\main\\resources\\views\\no-cats.html";
    private final static String ALL_CATS_HTML_FILE_PATH = "D:\\SoftUni\\Java-Web\\04. Introduction to Java EE\\fdmc\\src\\main\\resources\\views\\all-Cats.html";
    @Inject
    public CatAllServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Cat> cats = ((Map<String, Cat>)req.getSession()
                .getAttribute("cats"));
    String allCats="";

        if(cats != null) {
            allCats = reader.readHtmlFile(ALL_CATS_HTML_FILE_PATH)
                    .replace("{{allCats}}",  cats
                            .keySet()
                            .stream()
                            .map(x -> String.format("<a href='/cats/profile?catName=%s'>%s</a>", x, x))
                            .collect(Collectors.joining("<br/>")));
        } else {
            allCats = reader.readHtmlFile(NO_CATS_HTML_FILE_PATH);
        }
        resp.getWriter().println(allCats);
    }
}
