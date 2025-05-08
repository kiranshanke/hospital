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
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateAccountServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Getting form data
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");

        // Validation: Check if password and confirm password match
        if (username == null || email == null || password == null || confirmPassword == null || !password.equals(confirmPassword)) {
            out.println("<html><body>");
            out.println("<h3>Error: Passwords do not match or fields are empty. Please try again.</h3>");
            out.println("<a href='create-account.html'>Go Back</a>");
            out.println("</body></html>");
            return;
        }

        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour_shop", "root", "root");

            // SQL Insert Query
            String sql = "INSERT INTO accounts (username, email, password) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);

            // Execute the update
            int x = ps.executeUpdate();

            // Display result
            if (x > 0) {
                out.println("<html><body>");
                out.println("<h3>Account created successfully!</h3>");
                out.println("<p>Your account has been created. You can now log in.</p>");
                out.println("<a href='login.html'>Go to Login Page</a>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h3>Account creation failed. Please try again later.</h3>");
                out.println("</body></html>");
            }

        } catch (SQLException | ClassNotFoundException e) {
            // Print stack trace for debugging
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
            out.println("</body></html>");
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}