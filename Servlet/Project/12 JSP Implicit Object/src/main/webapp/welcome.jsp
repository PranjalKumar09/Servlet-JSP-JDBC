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
    <%
    String name = config.getInitParameter("username");
    out.print(name);
    
    %>
    <%
    String name2 = application.getInitParameter("username");
    out.print(name2);
    %>
    <br><br>
    <%
    String name6 = (String)session.getAttribute("myname");
    out.print(name6);
    %>
    <a href="next6.jsp">Remove Session</a>
</body>
</html>

<h1>End Page</h1>
