package ServletProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Database connection parameters
        String dbUrl = "jdbc:mysql://localhost:3306/tour_shop";
        String dbUsername = "root";  // Replace with your DB username
        String dbPassword = "root";  // Replace with your DB password

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish the connection to the database
            con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // SQL query to check if the user exists with the provided credentials
            String sql = "SELECT * FROM accounts WHERE username = ? AND password = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            // Execute query and retrieve result
            rs = ps.executeQuery();

            if (rs.next()) {
                // Login success - user found in database
                response.sendRedirect("HomeServlet");  // Redirect to HomeServlet after successful login
            } else {
                // Login failure - incorrect username or password
                out.println("<html><body>");
                out.println("<h3>Invalid username or password!</h3>");
                out.println("<a href='login.html'>Try again</a>");
                out.println("</body></html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            // Close database resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "LoginServlet that checks user credentials against the database";
    }
}