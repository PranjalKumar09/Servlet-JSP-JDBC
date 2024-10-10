<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ page import="com.entity.User" %>
<%@ page import="com.dao.ExpenseDao" %>
<%@ page import="com.db.HibernateUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.entity.Expense" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index Page</title>
</head>
<body class="bg-light"
        <c:if test="${empty user}">
            <c:redirect url="../login.jsp"></c:redirect>
        </c:if>>
<%@include file="../component/navbar.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card">
                <div class="card-header text-center">
                    <p class="fs-3">All Expenses</p>
                    <c:if test="${not empty msg}">
                        <p class="text-center text-success fs-4">${msg}</p>
                        <c:remove var="msg"/>
                    </c:if>
                </div>

                <div class="card-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Title</th>
                            <th scope="col">Description</th>
                            <th scope="col">Date</th>
                            <th scope="col">Time</th>
                            <th scope="col">Price</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                            <%
                                User user = (User) session.getAttribute("user");
                                ExpenseDao dao = new ExpenseDao(HibernateUtil.getSessionFactory());
                                List<Expense> expenses=dao.getAllExpenseByUser(user);

                                for (Expense expense : expenses) {
                            %>
                                <tr>
                                    <th scope="row"> <%= expense.getTitle() %></th>
                                    <td><%=expense.getDescription()%></td>
                                    <td><%=expense.getDate()%></td>
                                    <td><%=expense.getTime()%></td>
                                    <td><%=expense.getAmount()%></td>
                                    <td>
                                        <a href="edit_expense.jsp?id=<%=expense.getId()%>" class="btn btn-sm btn-primary me-1">Edit</a>
                                        <a href="../deleteExpense?id=<%=expense.getId()%>" class="btn btn-sm btn-danger me-1">Delete</a>
                                    </td>
                                </tr>

                            <%
                                }
                            %>


                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
