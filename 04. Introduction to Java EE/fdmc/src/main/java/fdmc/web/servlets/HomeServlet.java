package fdmc.web.servlets;

import fdmc.web.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    private final static String INDEX_HTML_FILE_PATH = "D:\\SoftUni\\Java-Web\\04. Introduction to Java EE\\fdmc\\src\\main\\resources\\views\\index.html";

    private final HtmlReader htmlReader;

    @Inject
    public HomeServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println(htmlReader.readHtmlFile(INDEX_HTML_FILE_PATH));
    }
}
