<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
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
    <h1 class="text-center mb-4">Register</h1>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center">
                    <h2>Register Page</h2>
                    <c:if test="${not empty msg}">
                        <p class="text-center text-success fs-4">${msg}</p>
                        <c:remove var="msg"/>
                    </c:if>
                </div>

                <div class="card-body">
                    <form action="userRegister" method="POST"> <!-- Update with actual action -->
                        <div class="mb-3">
                            <label class="form-label">Full Name</label>
                        <input type="text"  name="FullName" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                        <input type="email"  name="Email" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password"  name="Password" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">About</label>
                        <input type="text"  name="About" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-primary col-md-12">Register</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
