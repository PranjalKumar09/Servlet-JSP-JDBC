<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.entity.User" %>
<%@ page import="com.entity.Expense" %>
<%@ page import="com.dao.ExpenseDao" %>
<%@ page import="com.db.HibernateUtil" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index Page</title>
</head>
<body class="bg-light">
<c:if test="empty user">
    <c:redirect url="../login.jsp"></c:redirect>
</c:if>

<%@include file="../component/navbar.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-header text-center">
                    <p class="fs-3">Edit Expense</p>

                </div>

                <div class="card-body">
                    <form action="../updateExpense" method="post"> <!-- Changed method to POST -->
                        <div class="mb-3">
                            <%
                                int id = Integer.parseInt(request.getParameter("id"));
                                ExpenseDao dao = new ExpenseDao(HibernateUtil.getSessionFactory());
                                Expense expense = dao.getExpenseById(id);

                                // Set expense as request attribute to make it accessible in EL

                            %>
                            <label class="form-label">Title</label>
                            <input type="text" name="title" class="form-control" value="<%=expense.getTitle()%>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Date</label>
                            <input type="date" name="date" class="form-control" value="<%=expense.getDate()%>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Time</label>
                            <input type="time" name="time" class="form-control" value="<%=expense.getTime()%>"> <!-- Changed type to 'time' -->
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Description</label>
                            <input type="text" name="description" class="form-control" value="<%=expense.getDescription()%>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Price</label>
                            <input type="number" step="0.01" name="price" class="form-control" value="<%=expense.getAmount()%>"> <!-- Fixed to 'expense.amount' -->
                        </div>
                        <input type="hidden" name="id" value="<%=expense.getId()%>">
                        <button type="submit" class="btn btn-success col-md-12">Update</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
