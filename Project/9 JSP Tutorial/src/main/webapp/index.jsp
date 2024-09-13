<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page session="false"%>
<%@ page isELIgnored="false" %>
<%@ page errorPage="error.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Hello World</h1>
    <h1>Declaration Tag:</h1>
    <%! 
        int a = 10;
        String name = "abc";
    %>
    <h1>Expression Tag: <%= a %> <%= name %> </h1>
    <% 
        int sum = 20 + 30;
        out.println("Sum= " + sum);
        
        int sub = sum - 10;
        out.println("Subtraction= " + sub);
    %>

    <h1 style="color:red"><%= sum %></h1>
    <h1 style="color:red"><%= sub %></h1>

    <% 
        Date currentDate = new Date();
    %>
    <h2>Date <%= currentDate.toString() %> </h2>

    <%@ include file="header.jsp" %> <!-- Ensure header.jsp exists and is correctly placed -->
</body>
</html>
