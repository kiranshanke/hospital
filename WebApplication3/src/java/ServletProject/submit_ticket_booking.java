package ServletProject;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class submit_ticket_booking extends HttpServlet {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tour_shop";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    // Process shared logic, can be used for both doGet and doPost
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // Handle GET request (for form submission)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);  // Call processRequest if needed

        // Get form data from the query parameters
        String ticketType = request.getParameter("ticket_type");
        String destination = request.getParameter("destination");
        String travelDate = request.getParameter("travel_date");
        String time = request.getParameter("time");
        String passengerName = request.getParameter("passenger_name");
        String email = request.getParameter("email");

        // Database connection and insertion logic
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO ticket_booking (ticket_type, destination, travel_date, time, passenger_name, email) "
                     + "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, ticketType);
                stmt.setString(2, destination);
                stmt.setDate(3, Date.valueOf(travelDate));
                stmt.setTime(4, Time.valueOf(time));
                stmt.setString(5, passengerName);
                stmt.setString(6, email);
                
                int rowsInserted = stmt.executeUpdate();
                
                if (rowsInserted > 0) {
                    response.getWriter().println("Booking successful!");
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error while processing your booking.");
        }
    }

    // Optionally, handle POST requests if necessary
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);  // Call processRequest if needed
    }
}