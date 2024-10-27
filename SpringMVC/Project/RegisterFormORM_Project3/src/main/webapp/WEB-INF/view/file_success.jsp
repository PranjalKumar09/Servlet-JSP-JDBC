<%--
  Created by IntelliJ IDEA.
  User: pranjal
  Date: 27/10/24
  Time: 10:05 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../resources/css/css.jsp"%>

</head>
<body>
<div class="text-center">
    <h1>File Upload Successful</h1>
    <img src="<c:url value="resource/img/${imgName}"/> " >
</div>
</body>
</html>
