package ServletProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveStudentServlet
 */
@WebServlet("/SaveStudentServlet")
public class SaveStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SaveStudentServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Getting form data
        int n1 = Integer.parseInt(request.getParameter("t1"));
        String b = request.getParameter("t2");
        double c = Double.parseDouble(request.getParameter("t3"));

        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/k", "root", "root");

            // SQL Insert Query
            String sql = "INSERT INTO student VALUES(?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, n1);
            ps.setString(2, b);
            ps.setDouble(3, c);

            // Execute update
            int x = ps.executeUpdate();

            // Display result
            if (x > 0) {
                out.println("<h3>Student saved successfully!</h3>");
            } else {
                out.println("<h3>Failed to save student!</h3>");
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
