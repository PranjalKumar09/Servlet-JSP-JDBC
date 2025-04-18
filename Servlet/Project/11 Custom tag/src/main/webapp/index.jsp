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
    <h1>Greet Message</h1>
    <t:greet msg="good Aftenoon"/>
    <%-- even  <t: greet> </t:greet> causing error  --%>
    <t:Demo />

    <br><br>
    <t:area side="10"/>


</body>
</html>

<h1>End Page</h1>
