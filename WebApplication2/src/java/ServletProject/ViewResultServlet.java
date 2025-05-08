

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewResultServlet
 */
@WebServlet("/ViewResultServlet")
public class ViewResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
                int javamarks=Integer.parseInt(request.getParameter("tjava"));
		int phpmarks=Integer.parseInt(request.getParameter("tphp"));
		int testmarks=Integer.parseInt(request.getParameter("ttest"));
		int sqlmarks=Integer.parseInt(request.getParameter("tsql"));
		int pracmarks=Integer.parseInt(request.getParameter("tprac"));
		int projmarks=Integer.parseInt(request.getParameter("tproj"));
		
		
		Cookie cookies[]=request.getCookies();
		String studName="";
		String studClass="";
		String studAdd="";
//		for(Cookie cookie:cookies)
//		{
//			if(cookie.getName().equals("studName"))
//				studName=(String)cookie.getValue();
//			if(cookie.getName().equals("studClass"))
//				studClass=(String)cookie.getValue();
//			if(cookie.getName().equals("studAdd"))
//				studAdd=(String)cookie.getValue();
//		}
		
		for(Cookie cookie:cookies)
		{
			switch(cookie.getName())
			{
			case "studName" : studName=(String)cookie.getValue();break;
			case "studClass" : studClass=(String)cookie.getValue();break;
			case "studAdd" : studAdd=(String)cookie.getValue();break;
			}
		}
				// int n1 = Integer.parseInt(request.getParameter("t1"));

		
		int totalmarks=javamarks+phpmarks+pracmarks+projmarks+sqlmarks+testmarks;
		double per=totalmarks/6;
		String grade="";
		if(per>=70)
			grade="DIST";
		else if(per>=60)
			grade="FC";
		else if(per>=50)
			grade="SC";
		else if(per>=40)
			grade="PC";
		else
			grade="Not Pass";
		out.print("<table border='1'>");
		
		out.print("<tr>");
		out.print("<th> Subject </th>");
		out.print("<th> Marks </th>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td> Name </td>");
		out.print("<td> " + studName+ " </td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td> Class </td>");
		out.print("<td> " + studClass+ " </td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td> Address </td>");
		out.print("<td> " + studAdd+ " </td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td> Java </td>");
		out.print("<td> " + javamarks+ " </td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td> PHP </td>");
		out.print("<td> " + phpmarks+ " </td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td> Testing </td>");
		out.print("<td> " + testmarks+ " </td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td> SQL </td>");
		out.print("<td> " + sqlmarks+ " </td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td> Practical </td>");
		out.print("<td> " + pracmarks+ " </td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td> Project </td>");
		out.print("<td> " + projmarks+ " </td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td> Total Marks </td>");
		out.print("<td> " + totalmarks+ " </td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td> Percentage </td>");
		out.print("<td> " + per+ " </td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td> Grade </td>");
		out.print("<td> " + grade+ " </td>");
		out.print("</tr>");
		
		
//		out.print("<h6> Name " + studName+ "</h6>");
//		out.print("<h6> Class " +studClass+ "</h6>");
//		out.print("<h6> Address " +studAdd+ "</h6>");
//		out.print("<h6> Java Marks " +javamarks+ "</h6>");
//		out.print("<h6> PHP Marks " +phpmarks+ "</h6>");
//		out.print("<h6> Testing Marks " +testmarks+ "</h6>");
//		out.print("<h6> SQL Marks " +sqlmarks+ "</h6>");
//		out.print("<h6> Proj Marks " +projmarks+ "</h6>");
//		out.print("<h6> Practical Marks " +pracmarks+ "</h6>");
//		out.print("<h6> Total Marks " +totalmarks+ "</h6>");
//		out.print("<h6> Percentage " +per+ "</h6>");
//		out.print("<h6> Grade " +grade+ "</h6>");
		out.print("</table>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}