<%--
  Created by IntelliJ IDEA.
  User: pranjal
  Date: 28/10/24
  Time: 12:59 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Edit Employee Page</title>
    <%@include file="../resources/css/css.jsp"%>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Emp Management System</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="addEmp">Add Employee</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="card">
                <div class="card-header text-center">
                    <h1>Edit Employee</h1>

                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/updateEmp" , method="POST">
                            <input type="hidden" name="id" value="${emp.id}"></input>
                            <div class="mb-3"><label>Enter Full Name</label><input value="${emp.fullName}"   type="text" name="fullName" class="form-control"></div>
                            <div class="mb-3"><label>Enter Address</label><input value="${emp.address}"   type="text" name="address" class="form-control"></div>
                            <div class="mb-3"><label>Enter Email</label><input value="${emp.email}"   type="email" name="email" class="form-control"></div>
                            <div class="mb-3"><label>Enter Password</label><input value="${emp.password}"   type="password" name="password" class="form-control"></div>
                            <div class="mb-3"><label>Enter Designation</label><input value="${emp.designation}"   type="text" name="designation" class="form-control"></div>
                            <div class="mb-3"><label>Enter Salary</label><input value="${emp.salary}" type="number" name="salary" class="form-control"></div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>