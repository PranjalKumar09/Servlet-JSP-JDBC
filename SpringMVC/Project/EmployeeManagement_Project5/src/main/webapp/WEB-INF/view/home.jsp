<%--
  Created by IntelliJ IDEA.
  User: pranjal
  Date: 28/10/24
  Time: 12:59 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Home Page</title>
    <%@include file="../resources/css/css.jsp"%>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Emp Management System</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navzbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="addEmp">Add Employee</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="register">Register</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="login">Login</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
  <h1>Home Page</h1>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-body text-center">
                        <c:if test="${not empty msg}">
                            <h5>${msg}</h5>
                            <c:remove var="msg"/>
                        </c:if>

                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Full Name</th>
                                <th scope="col">Address</th>
                                <th scope="col">Email</th>
                                <th scope="col">Password</th>
                                <th scope="col">Designation</th>
                                <th scope="col">Salary</th>
                                <th scope="col">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${empList}" var="emp">
                            <tr>
                                <th scope="row">${emp.id}</th>
                                <td>${emp.fullName}</td>
                                <td>${emp.address}</td>
                                <td>${emp.email}</td>
                                <td>${emp.password  }</td>
                                <td>${emp.designation}</td>
                                <td>${emp.salary}</td>
                                <td>
                                    <a href="edit_emp/${emp.id}" class="btn btn-primary btn-sm">Edit</a>
                                    <a href="deleteEmp/${emp.id}" class="btn btn-danger btn-sm">Delete</a>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
