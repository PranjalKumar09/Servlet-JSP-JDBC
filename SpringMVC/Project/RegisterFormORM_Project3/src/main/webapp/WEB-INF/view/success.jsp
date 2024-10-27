
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Success Page</title>
</head>
<body>
    <%@ include file="../resources/css/css.jsp"   %>

    <h1>Success Page</h1>
    <h2>Name:           ${user.fullName}</h2>
    <h2>Id:           ${user.id}</h2>
    <h2>Email:           ${user.email}</h2>
    <h2>Password:           ${user.password}</h2>
    <h2>Age:           ${user.age}</h2>
</body>
</html>
