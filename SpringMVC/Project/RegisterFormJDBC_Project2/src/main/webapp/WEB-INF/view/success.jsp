<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>



<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="css.jsp"%>
    <h1 class="title-center">Register Successfully</h1>
    <hr>
    <h5> Name:    ${user.fullName}</h5>
    <h5> Email:   ${user.email}</h5>
    <h5> Password:${user.password}</h5>
    <h5> Age:     ${user.age}</h5>
</body>
</html>
