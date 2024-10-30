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
    <title>Register Page</title>
    <%@include file="../resoucres/component/css.jsp"%>
</head>
<body style="background-color: #f0f0f0;">
    <%@include file="../resoucres/component/navbar.jsp"%>
    <div class="container">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-header text-center">
                        <h3>Register Page</h3>
                        <c:if test="${not empty msg}">
                            <h5 class="fs-5  text-success">${msg}</h5>
                            <c:remove var="msg"/>
                        </c:if>
                    </div>
                    <div class="card-body">
                        <form action="registerUser" method="post">
                            <div class="mb-3"><label>Enter Full Name</label><input type="text" name="fullName" class="form-control"></div>
                            <div class="mb-3"><label>Enter Qualification</label><input type="text" name="qualification" class="form-control"></div>
                            <div class="mb-3"><label>Enter Email</label><input type="email" name="email" class="form-control"></div>
                            <div class="mb-3"><label>Enter Password</label><input type="password" name="password" class="form-control"></div>
                            <button type="submit" class="btn btn-primary col-md-12">Register</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
