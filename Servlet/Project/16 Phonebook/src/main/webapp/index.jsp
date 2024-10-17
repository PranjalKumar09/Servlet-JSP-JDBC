<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="WEB-INF/mylib.tld" prefix="t" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>JSTL CORE TAGS EXAMPLE </h1>
    <hr>
    <c:out value="c:out"></c:out>
    <c:import url="next.jsp" var="data"></c:import>
    <h1><c:out value="${data}"></c:out></h1>

    <h1>
        <c:set var="result" value="${400*12}" scope="session"></c:set>
        Before : <c:out value="${result}"></c:out>
    </h1>
    <h1>
        <c:remove var="result"/>
        After: <c:out value="${result}"></c:out>
    </h1>

    <hr>
    <c:catch var="Exception">
    <% int a= 2/0; %></c:catch>
    <c:if test="${Exception!=null}">
    <p>${Exception}</p>
    <p>${Exception.message}</p>
    </c:if>

    <hr>
    <hr>

    <c:set value="${20}" var="age" > </c:set>
    <c:if test="${age>18}">
        <h1>Adult</h1>
    </c:if>

    <hr>
    <hr>
    <hr>

    <c:choose>
        <c:when test="${age>2}">
            <h1>Adult</h1>
        </c:when>
            
        <c:when test="${age<10}">
            <h1>Child</h1>
        </c:when>
        <c:otherwise>
            <h1>Teenager</h1>
        </c:otherwise>
        
    </c:choose>


    <hr>
    <hr>

    <h1>for each</h1>
    <c:forEach var="i" begin="1" end="10">
        <c:out value="${num}"></c:out>
    </c:forEach>

    <c:forTokens items="Pranjal-Kumar-Shukla" delims="-" var="newString">
        <c:out value="${newString}"></c:out>
    </c:forTokens>
    <p></p>
    <p></p>
    <p></p>
    <p></p>

    <c:url value='/index.jsp' var="pageUrl">
        <c:param name="em" value="demo@gmail.com"></c:param>
        <c:param name="ps" value="demo"></c:param>
    </c:url>
    ${pageUrl}

</body>
</html>

<h1>End Page</h1>
