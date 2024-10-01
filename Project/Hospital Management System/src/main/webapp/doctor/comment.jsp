<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.db.DBConnect, java.util.List,  com.entity.Doctor, com.entity.Appointment, com.dao.AppointmentDAO , com.dao.DoctorDao" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Patient Details</title>
    <%@include file = "./../component/allcss.jsp"%>
    <style>
        .point-card {
            box-shadow: 0 0 10px 0 rgba(0, 0, 0 , 0.3);
        }
        .backImg {
            background: linear-gradient(rgba(0, 0, 0, .4), rgba(0, 0, 0, .4)), url("../img/hos2.jpg");
            height: 20vh;
            width: 100%;
            background-size: cover;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
    <c:if test="${empty docObj}">
        <c:redirect url="../doctor_login.jsp"></c:redirect>
    </c:if>
    
    <%@include file = "navbar.jsp"%>
    <div class="container-fluid backImg p-5">
        <p class="text-center fs-2 text-white"></p>
    </div>
    
    <div class="container p-3">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="card point-card">
                    <div class="card-body">
                        <p class="text-center fs-4">Patient Comment</p>

                        <%
                        int id = Integer.parseInt(request.getParameter("id"));
                        AppointmentDAO dao = new AppointmentDAO(DBConnect.getConn());
                        Appointment ap = dao.getAppointmentById(id);
                        %>
                        
                        <form action="../updateStatus" class="row" method="post">
                            <div class="col-md-6">
                                <label>Patient Name</label><input type="text" readonly value="<%=ap.getFullName() %>" class="form-control">
                            </div>
                            <div class="col-md-6">
                                <label>Age</label><input type="text" readonly value="<%=ap.getAge() %>" class="form-control">
                            </div>
                            <div class="col-md-6">
                                <label>Mob No</label><input type="text" readonly value="<%=ap.getPhno() %>" class="form-control">
                            </div>
                            <div class="col-md-6">
                                <label>Diseases</label><input type="text" readonly value="<%=ap.getDisease() %>" class="form-control">
                            </div>

                            <div class="col-md-12">
                                <label>Comment</label><textarea required name="comm" class="form-control" rows="3"> </textarea>
                            </div>

                            <input type="hidden" name="did" value="<%=ap.getDoctorId() %>">
                            <input type="hidden" name="id" value="<%=id %>">
                            
                            <button class="mt-3 btn btn-primary col-md-6 offset-md-3">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
