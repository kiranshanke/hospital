package ServletProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveStudentServlet
 */
@WebServlet(name = "CreateAccount", urlPatterns = {"/CreateAccount"})
public class CreateAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateAccount() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Getting form data
        String n1 = (request.getParameter("username"));
        String b = request.getParameter("email");
        String c = (request.getParameter("password"));

        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour_shop", "root", "root");

            // SQL Insert Query
            String sql = "INSERT INTO accounts VALUES(?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, n1);
            ps.setString(2, b);
            ps.setString(3, c);

            // Execute update
            int x = ps.executeUpdate();

            // Display result
            if (x > 0) {
                out.println("<h3>Login successfully!</h3>");
            } else {
                out.println("<h3>faild!</h3>");
            }

        } catch (SQLException | ClassNotFoundException e) {
            // Print stack trace for debugging
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

//@WebServlet("/CreateAccount")
//public class CreateAccount extends HttpServlet {
//
//    // Database connection details
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/tour_shop";
//    private static final String DB_USERNAME = "root";  // Update with your DB username
//    private static final String DB_PASSWORD = "root"; // Update with your DB password
//
//    // JDBC variables
//    private static final String INSERT_USER_SQL = "INSERT INTO accounts (username, email, password) VALUES (?, ?, ?)";
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Retrieve form data
//        String username = request.getParameter("username");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String confirmPassword;
//        confirmPassword = request.getParameter("confirmPpassword");
//
//        // Basic validation (check if passwords match)
//        if (!password.equals(confirmPassword)) {
//            response.getWriter().println("Passwords do not match.");
//            return;
//        }
//
//        // Validate email format
//        if (!isValidEmail(email)) {
//            response.getWriter().println("Invalid email format.");
//            return;
//        }
//
//        // Check if username already exists
//        if (isUsernameTaken(username)) {
//            response.getWriter().println("Username is already taken.");
//            return;
//        }
//
//        // If all checks pass, save account in database
//        boolean accountCreated = createAccount(username, email, password);
//
//        // Redirect to login page after successful account creation
//        if (accountCreated) {
//            response.sendRedirect("login.html");
//        } else {
//            response.getWriter().println("Error creating account.");
//        }
//    }
//
//    // Simple email validation regex
//    private boolean isValidEmail(String email) {
//        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
//        return email.matches(emailRegex);
//    }
//
//    // Check if username already exists
//    private boolean isUsernameTaken(String username) {
//        // Query the database to check if username exists
//        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM accounts WHERE username = ?")) {
//            
//            ps.setString(1, username);
//            ResultSet rs = ps.executeQuery();
//            
//            if (rs.next()) {
//                return rs.getInt(1) > 0;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    // Save account in database
//    private boolean createAccount(String username, String email, String password) {
//        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//             PreparedStatement ps = conn.prepareStatement(INSERT_USER_SQL)) {
//            
//            ps.setString(1, username);
//            ps.setString(2, email);
//            ps.setString(3, password);  // Store the password as is, but consider hashing it!
//
//            int rowsInserted = ps.executeUpdate();
//            return rowsInserted > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    // JDBC Connection setup
//    @Override
//    public void init() throws ServletException {
//        try {
//            // Load the JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new ServletException("JDBC Driver not found", e);
//        }
//    }
//}
