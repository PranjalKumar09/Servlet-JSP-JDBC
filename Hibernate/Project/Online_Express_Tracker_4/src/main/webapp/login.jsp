<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.entity.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="wwscale=1.0">
    <title>Index Page</title>

</head>
<body class="bg-light">
<%@include file="component/navbar.jsp"%>
<div class="container p-5">
<%--    <h1 class="text-center mb-4"></h1>--%>
    <div class="row justify-content-c~enter">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center">
                    <p class="text-center fs-3">Login Page</p>
                    <c:if test="${not empty msg}">

                        <p class="text-center text-danger fs-4">${msg}</p>
                        <c:remove var="msg"/>
                    </c:if>
                </div>

                <div class="card-body">
                    <form action="login" method="post"> <!-- Update with actual action -->

                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email"  name="Email" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password"  name="Password" class="form-control" required>
                        </div>

                        <button type="submit" class="btn btn-primary col-md-12">Login</button>
                        <div class="text-center mt-2">
                            Dont have account? <a href="register.jsp" class="text-decoration-none">Create One</a>

                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
