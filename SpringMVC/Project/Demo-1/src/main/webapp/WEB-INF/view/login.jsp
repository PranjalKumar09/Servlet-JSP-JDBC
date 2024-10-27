<%--
  Created by IntelliJ IDEA.
  User: pranjal
  Date: 19/10/24
  Time: 9:24 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Login Page</h1>
<%
    String name = (String) request.getAttribute("name");
    Integer regNo = (Integer) request.getAttribute("regNumber");

%>
    <h4> regNo :<%= regNo%></h4>
    <h4> Name :  <%= name%></h4>
</body>
</html>
