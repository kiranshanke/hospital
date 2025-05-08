package ServletProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResetPasswordServlet
 */
@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/ResetPasswordServlet"})
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ResetPasswordServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Getting form data
        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");

        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour_shop", "root", "root");

            // SQL Update Query
            String sql = "UPDATE accounts SET password = ? WHERE username = ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, newPassword);  // Set new password
            ps.setString(2, username);     // Set username

            // Execute update
            int x = ps.executeUpdate();

            // Display result
            if (x > 0) {
                out.println("<h3>Password reset successful!</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("/login.html");
                rd.forward(request, response);  // Redirect to login page
            } else {
                out.println("<h3>Password reset failed. Username not found.</h3>");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            // Ensure resources are closed
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}