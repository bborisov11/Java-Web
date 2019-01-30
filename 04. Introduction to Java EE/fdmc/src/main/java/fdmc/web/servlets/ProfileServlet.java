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

@WebServlet("/cats/profile")
public class ProfileServlet extends HttpServlet{

    private final HtmlReader reader;
    private final static String PROFILE_HTML_FILE_PATH = "D:\\SoftUni\\Java-Web\\04. Introduction to Java EE\\fdmc\\src\\main\\resources\\views\\profile.html";
    private final static String ERROR_HTML_FILE_PATH = "D:\\SoftUni\\Java-Web\\04. Introduction to Java EE\\fdmc\\src\\main\\resources\\views\\non-existent.html";

    @Inject
    public ProfileServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cat cat = ((Map<String, Cat>) req.getSession().getAttribute("cats"))
                .get(req.getQueryString().split("=")[1]);
        String profile;
        if (cat != null) {
            profile = reader.readHtmlFile(PROFILE_HTML_FILE_PATH)
                    .replace("{{catName}}", cat.getName())
                    .replace("{{catBreed}}", cat.getBreed())
                    .replace("{{catColor}}", cat.getColor())
                    .replace("{{catLegs}}", cat.getNumberOfLegs().toString());
        } else {
            profile = reader.readHtmlFile(ERROR_HTML_FILE_PATH)
                    .replace("{{catName}}", req.getQueryString().split("=")[1]);
        }
        resp.getWriter().println(profile);
    }
}
