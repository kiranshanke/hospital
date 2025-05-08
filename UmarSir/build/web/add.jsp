<%-- 
    Document   : add
    Created on : 20-Mar-2025, 10:11:04 am
    Author     : Kiran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            int n1=Integer.parseInt(request.getParameter("t1"));
		int n2=Integer.parseInt(request.getParameter("t2"));
		
		
		int result=n1+n2;	
		out.print(result);
                %>
    
    </body>
</html>
