package ServletProject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the home.html
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.html");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet that serves the travel agency home page";
    }
}