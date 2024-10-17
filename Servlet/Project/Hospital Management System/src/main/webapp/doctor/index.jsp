<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.db.DBConnect, java.util.List,  com.entity.Doctor, com.entity.Appointment, com.dao.AppointmentDAO , com.dao.DoctorDao" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Login</title>
    <%@include file = "./../component/allcss.jsp"%>
    <style>
      .point-card {
        box-shadow: 0 0 10px 0 rgba(0, 0, 0 , 0.3);
      }
    </style>

    
</head>
<body>
  <c:if test="${empty docObj}">
    <c:redirect url="../doctor_login.jsp"></c:redirect>
  </c:if>

  <%@include file = "navbar.jsp"%>
  <h1 class="text-center fs-3">Doctor Dashboard</h1>
  
  <div class="container-p-5">
    <%
      Doctor d = (Doctor) session.getAttribute("docObj");
      DoctorDao dao = new DoctorDao(DBConnect.getConn());

    %>
    <div class="row">
      <div class="col-md-4 offset-md-2">
        <div class="card paint-card">
          <div class="card-body text-center text-success">
            <i class="fa fa-user-md fa-3x"></i><br />
            <p class="fs-4 text-center">Doctor</p>
            <br /><%=dao.countDoctor() %>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card paint-card">
          <div class="card-body text-center text-success">
            <i class="fa fa-user-circle fa-3x"></i><br />
            <p class="fs-4 text-center">Appointment</p>
            <br /><%=dao.countAppointmentByDoctorId(d.getId()) %>
          </div>
        </div>
      </div>
    </div>
  </div>

</body>
</html>
