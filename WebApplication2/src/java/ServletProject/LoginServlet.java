/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ServletProject;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Kiran
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
       response.setContentType("text/html");
        //PrintWriter out = response.getWriter();
         String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Hard-coded login credentials for demo purposes
        String validUsername = "touradmin";
        String validPassword = "tourpassword123";
        
        // Check if the entered credentials are correct
        //if (username.equals(validUsername) && password.equals(validPassword)) {
          if (username != null && username.equals(password)) {         
// Login success - redirect to another page (e.g., tour dashboard)
        //JOptionPane.showMessageDialog(null, "Login Successfully");
             // setVisible(false);
        
        
       // RequestDispatcher rd=request.getRequestDispatcher("/createAccount.html");
			//rd.forward(request, response);

            response.sendRedirect("dashboard.jsp");
        } else {
            // Login failure - show error message
            response.setContentType("text/html");
        PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h3>Invalid username or password!</h3>");
            out.println("<a href='login.html'>Try again</a>");
            out.println("</body></html>");
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    //private void setVisible(boolean b) {
      //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}

}
