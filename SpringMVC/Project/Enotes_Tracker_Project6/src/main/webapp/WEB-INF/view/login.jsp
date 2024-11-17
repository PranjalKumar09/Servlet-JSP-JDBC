<%--
  Created by IntelliJ IDEA.
  User: pranjal
  Date: 30/10/24
  Time: 9:10 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Login Page</title>
    <%@include file="../resoucres/component/css.jsp"%>
</head>
<body style="background-color: #f0f0f0;">
<%@include file="../resoucres/component/navbar.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="card">
                <div class="card-header text-center">
                    <h3>Login Page</h3>
                    <c:if test="${not empty msg}">
                        <h5 class="fs-5  text-danger">${msg}</h5>
                        <c:remove var="msg"/>
                    </c:if>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/loginUser" method="post">
                         <div class="mb-3"><label>Enter Email</label><input type="email" name="email" class="form-control"></div>
                        <div class="mb-3"><label>Enter Password</label><input type="password" name="password" class="form-control"></div>
                        <button type="submit" class="btn btn-primary col-md-12">Login</button>
                    </form>
                </div>
                <div class="card-footer text-center "> <p class="fs-6">
                    Don't have account? ..  <a href="register" class="text-decoration-none">register</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
