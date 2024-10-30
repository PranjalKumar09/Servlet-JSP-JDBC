<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Image Load Test</title>
</head>
<body>
<h1>Image Load Test</h1>


<img src="resources/img/img2.jpg" alt="Relative Path Test 2" width="300" height="auto">
<img src="resources/img/notes1.webp" alt="Relative Path Test 2" width="300" height="auto">


<img src="<c:url value='/resources/img/img2.jpg' />" alt="JSP c:url Test 2" width="300" height="auto">
<img src="<c:url value='/resources/img/notes1.webp' />" alt="JSP c:url Test 1" width="300" height="auto">


</body>
</html>
