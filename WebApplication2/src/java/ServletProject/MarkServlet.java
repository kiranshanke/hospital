


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MarksServlet
 */
@WebServlet("/MarkServlet")
public class MarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String studName=request.getParameter("t1");
		String studClass= request.getParameter("t2");
		String studAdd= request.getParameter("t3");
		
		Cookie c1=new Cookie("studName",studName);
		Cookie c2=new Cookie("studClass",studClass);
		Cookie c3=new Cookie("studAdd",studAdd);
		
		response.addCookie(c1);
		response.addCookie(c2);
		response.addCookie(c3);
		
//		out.print("<form action='result' method='post'>");
//		out.print("Java :<input type='text' name='tjava'><br>");
//		out.print("PHP :<input type='text' name='tphp'><br>");
//		out.print("Software Testting :<input type='text' name='ttest'><br>");
//		out.print("SQL :<input type='text' name='tsql'><br>");
//		out.print("Practical :<input type='text' name='tprac'><br>");
//		out.print("Project :<input type='text' name='tproj'><br>");
//		out.print("<input type='submit' name='s1' value='View Result'>");
//		out.print("</form");
RequestDispatcher rd=request.getRequestDispatcher("/login.html");
			rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
