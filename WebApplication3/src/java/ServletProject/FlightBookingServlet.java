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

@WebServlet(name = "FlightBookingServlet", urlPatterns = {"/FlightBookingServlet"})
public class FlightBookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FlightBookingServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Getting form data from the request
        String departureCity = request.getParameter("departureCity");
        String destinationCity = request.getParameter("destinationCity");
        String departureDate = request.getParameter("departureDate");
        int passengerCount = Integer.parseInt(request.getParameter("passengerCount"));

        // Price calculation (example)
        double pricePerPassenger = 100.00; // Example price per passenger
        double totalPrice = pricePerPassenger * passengerCount;

        // Set the attributes to be used in the HTML page
        request.setAttribute("departureCity", departureCity);
        request.setAttribute("destinationCity", destinationCity);
        request.setAttribute("departureDate", departureDate);
        request.setAttribute("passengerCount", passengerCount);
        request.setAttribute("totalPrice", totalPrice);

        // Database connection and SQL query to insert the booking details
        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour_shop", "root", "root");

            // SQL Insert Query
            String sql = "INSERT INTO Flight_Booking (departureCity, destinationCity, departureDate, passengerCount, totalPrice) VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, departureCity);
            ps.setString(2, destinationCity);
            ps.setString(3, departureDate);
            ps.setInt(4, passengerCount);
            ps.setDouble(5, totalPrice);

            // Execute the SQL insert statement
            int result = ps.executeUpdate();

            if (result > 0) {
                // If the insert was successful, forward to the flight price HTML page
                RequestDispatcher dispatcher = request.getRequestDispatcher("/flight_price.html");
                dispatcher.forward(request, response);
            } else {
                out.println("<html><body>");
                out.println("<h3>Error: Booking failed. Please try again later.</h3>");
                out.println("</body></html>");
            }

        } catch (SQLException | ClassNotFoundException e) {
            // Handle any exceptions during SQL or JDBC operations
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
        // Forward to the doGet method to handle the request
        doGet(request, response);
    }
}