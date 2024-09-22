<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="WEB-INF/mylib.tld" prefix="t" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.sql.Connection, java.util.List, com.conn.DBConnect, com.dao.StudentDAO, com.entity.Student" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%@ include file="all_css.jsp" %>
</head>
<body>
	<%@ include file="navbar.jsp" %>

    <%
    Connection conn = null;
    try {
        conn = DBConnect.getConn();
        StudentDAO dao = new StudentDAO(conn);
        List<Student> students = dao.getAllStudents();
    %>

    <div class="container p-3">
        <div class="card">
            <div class="card-body">
                <p class="text-center fs-1">All Student Details</p>
                <c:if test="${not empty succMsg}">
                        <p class="text-center">${succMsg}</p>
                        <c:remove var="succMsg" />
                    </c:if>
                    
                    <c:if test="${not empty errorMsg}">
                        <p class="text-center">${errorMsg}</p>
                        <c:remove var="errorMsg" />
                    </c:if>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Full Name</th>
                            <th scope="col">DOB</th>
                            <th scope="col">Address</th>
                            <th scope="col">Qualification</th>
                            <th scope="col">Email</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        for (Student s : students) {
                        %>
                        <tr>
                            <td><%= s.getFullname() %></td>
                            <td><%= s.getDob() %></td>
                            <td><%= s.getAddress() %></td>
                            <td><%= s.getQualification() %></td>
                            <td><%= s.getEmail() %></td>
                            <td>
                                <a href="edit_student.jsp?id=<%=s.getId() %>" class="btn btn-sm btn-primary">Edit</a>
                                <a href="delete?id=<%= s.getId() %>" class="btn btn-sm btn-danger ms-1">Delete</a>
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
    <%
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (conn != null) {
            conn.close();
        }
    }
    %>
</body>
</html>
