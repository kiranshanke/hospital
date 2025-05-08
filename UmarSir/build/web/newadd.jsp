<%-- 
    Document   : newadd
    Created on : 21-Mar-2025, 8:51:30 am
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
        <%!int a,b;%>
        <%
        
          int n1=Integer.parseInt(request.getParameter("t1"));
		int n2=Integer.parseInt(request.getParameter("t2"));
		
		
		int result=n1+n2;	
		out.print(result);
                out.print("sum"+);
         %>
    </body>
</html>
