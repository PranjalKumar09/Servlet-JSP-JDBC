<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Taglib Directive Example</h1>
    
    <!-- Set a variable named "name" with the value "Hello World!" -->
    <c:set var="name" value="Hello World!"/>
    
    <!-- Output the value of the variable "name" -->
    <c:out value="${name}" />

    <!-- Conditional statement to check if 3 > 2 -->
    <c:if test="${3 > 2}">
        <h2>True Block</h2>
    </c:if>
</body>
</html>
