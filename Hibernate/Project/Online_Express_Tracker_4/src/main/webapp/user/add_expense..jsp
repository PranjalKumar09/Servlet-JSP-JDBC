<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index Page</title>
</head>
<body class="bg-light">
<c:if test="${empty user}">
    <c:redirect url="/login"></c:redirect>
</c:if>

<%@include file="../component/navbar.jsp"%>
<div class="container">

    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-header text-center">
                    <p class="fs-3">Add Expense</p>
                    <c:if test="${not empty msg}">
                        <p class="text-center text-success fs-4">${msg}</p>
                        <c:remove var="msg"/>
                    </c:if>
                </div>

                <div class="card-body">
                    <form action="../saveExpense" method="post"> <!-- Added the <form> tag with method -->
                        <div class="mb-3">
                            <label class="form-label">Title</label>
                            <input type="text" name="title" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Date</label>
                            <input type="date" name="date" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Time</label>
                            <input type="time" name="time" class="form-control" required> <!-- Changed type to 'time' -->
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Description</label>
                            <input type="text" name="description" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Price</label>
                            <input type="number" step="0.01" name="price" class="form-control" required> <!-- Changed type to 'number' -->
                        </div>
                        <button type="submit" class="btn btn-success col-md-12">Register</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>



