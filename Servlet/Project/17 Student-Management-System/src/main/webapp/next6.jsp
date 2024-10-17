<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <% session.removeAttribute("myname");%>
    <% String name = (String)session.getAttribute("myname"); 
    out.print(name);%>

</body>
</html>

<h1>End Page</h1>
