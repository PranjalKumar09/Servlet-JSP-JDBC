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
<body class="bg-light">
	<%@ include file="navbar.jsp" %>
    <div class="container p-4">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-body"></div>
                    <p class="fs-3 text-center">Edit Student</p>

                    <%
                    // Fix incorrect method name Integer.parseId -> Integer.parseInt
                    int id = Integer.parseInt(request.getParameter("id"));
                    StudentDAO dao = new StudentDAO(DBConnect.getConn());
                    Student s = dao.getStudentById(id);
                    %> 

                    <form action="update" method="post">
                        <input type="hidden" name="id" value="<%=s.getId() %>">
                        <div class="mb-3">
                            <label class="form-label">Full Name</label>
                            <input value="<%=s.getFullname() %>" type="text" class="form-control" name="fullname">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Date of Birth</label>
                            <input value="<%=s.getDob() %>" type="date" class="form-control" name="dob">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Address</label>
                            <input value="<%=s.getAddress() %>" type="text" class="form-control" name="address">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Qualification</label>
                            <input value="<%=s.getQualification() %>" type="text" class="form-control" name="qualification">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email address</label>
                            <input value="<%=s.getEmail() %>" type="email" class="form-control" name="email">
                        </div>
                        <input type="hidden" name="id" value="<%=s.getId()%>"> </input>
                        <button type="submit" class="btn btn-primary col-ms-12" name="submit">Update</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
