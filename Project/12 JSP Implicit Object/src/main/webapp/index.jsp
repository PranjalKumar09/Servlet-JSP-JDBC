<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page errorPage="error.jsp" %>
<%@ taglib uri="WEB-INF/mylib.tld" prefix="t" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <% out.print("Hello, world!"); %>
    <form action="next.jsp"><input type="text" name="username"><button type="submit">Ok</button></form>
    <a href="next2.jsp">3 JSP response implicit Object</a>    


    <br><br><br>
    <h1>4 JSP config implicit object</h1>
    <form action="welcome"><input type="text" name="username"><button type="submit">Method 4 and 6</button></form>

    <br><br>
    <% session.setAttribute("myname", "This is pranjal");
    %>
    
    

</body>
</html>

<h1>End Page</h1>
