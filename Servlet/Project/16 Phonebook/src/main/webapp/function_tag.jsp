<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <h1>Function Tag</h1>
    <hr>

    <!-- Set channelName -->
    <c:set var="channelName" value="PranjalKumar   "></c:set>

    <!-- Case-sensitive check -->
    <c:if test="${fn:contains(channelName, 'Pranjal')}">
        <h4>Found by Case-Sensitive Check</h4>
    </c:if>

    <!-- Case-insensitive check -->
    <c:if test="${fn:contains(fn:toLowerCase(channelName), 'pranjal')}">
        <h4>Found by Case-Insensitive Check</h4>
    </c:if>

    <hr>

    <!-- String index of -->
    <h1>String Index of 'Ku' -> ${fn:indexOf(channelName, 'Ku')}</h1>

    <!-- Not found gives -1-->
    <h1>String Index of 'ku' (case-insensitive) -> ${fn:indexOf(channelName, 'ku')}</h1>

    <hr>

    <!-- Trimmed string and length -->
    <h1>String length without trim -> ${fn:length(channelName)}</h1>
    <c:set var="trimmedChannelName" value="${fn:trim(channelName)}" />
    <h1>String length with trim -> ${fn:length(trimmedChannelName)}</h1>




    <c:set var="originalString" value="Pranjal Kumar JSP Example" />

    <!-- Splitting the string by space into an array -->
    <c:set var="splitArray" value="${originalString.split(' ')}" />

    <h2>Split Result:</h2>
    <ul>
        <c:forEach var="part" items="${splitArray}">
            <li>${part}</li>
        </c:forEach>
    </ul>

    <!-- Joining the split array back into a single string using String.join -->
    <%
        // Using Java code to join the split array
        String[] splitArray = ((String)pageContext.getAttribute("originalString")).split(" ");
        String joinedString = String.join("-", splitArray); // Joining with a dash (-)
        pageContext.setAttribute("joinedString", joinedString);
    %>

    <hr>
    <hr>

    <!-- fn:toLowerCase -->
    <h2>Lowercase: ${fn:toLowerCase(originalString)}</h2>

    <!-- fn:toUpperCase -->
    <h2>Uppercase: ${fn:toUpperCase(originalString)}</h2>

    <!-- fn:substring(start, end) -->
    <h2>Substring (0 to 7): ${fn:substring(originalString, 0, 7)}</h2>

    <!-- fn:substringAfter -->
    <h2>Substring After "Pranjal": ${fn:substringAfter(originalString, 'Pranjal')}</h2>

    <!-- fn:substringBefore -->
    <h2>Substring Before "Kumar": ${fn:substringBefore(originalString, 'Kumar')}</h2>

    <!-- fn:replace -->
    <h2>Replace "JSP" with "Java": ${fn:replace(originalString, 'JSP', 'Java')}</h2>


</body>
</html>

<h1>End Page</h1>
