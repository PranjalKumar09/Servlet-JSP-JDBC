<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page isELIgnored="false"%>
<%@ page import="com.db.DBConnect, java.sql.Connection, com.entity.Doctor, com.dao.DoctorDao" %>



<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Login</title>
    <%@include file = "./../component/allcss.jsp"%>
  </head>
  <body>
    <%@include file = "navbar.jsp" %>

    <div class="container p-5 fs-2">
      <p class="text-center">Admin Dashboard</p>

      <c:if test="${empty adminObj}">
        <c:redirect url="../admin_login.jsp"></c:redirect>
      </c:if>
      <c:if test="${not empty succObj}">
        <p class="text-center text-success fs-3">${succObj}</p>
        <c:remove var="succObj" scope="session"/>
      </c:if>
      <c:if test="${not empty errorMsg}">
        <p class="text-center text-danger fs-3">${errorMsg}</p>
        <c:remove var="errorMsg" scope="session"/>
      </c:if>





      <%
      DoctorDao dao = new DoctorDao(DBConnect.getConn());  
      %>
      <div class="row">
        <div class="col-md-4">
          <div class="card paint-card">
            <div class="card-body text-center text-success">
              <i class="fa fa-user-md fa-3x"></i><br />
              <p class="fs-4 text-center">Doctor</p>
              <br /> <%=dao.countDoctor() %>
            </div>
          </div>
        </div>

        <div class="col-md-4">
          <div class="card paint-card">
            <div class="card-body text-center text-success">
              <i class="fa fa-user-circle fa-3x"></i><br />
              <p class="fs-4 text-center">User</p>
              <br /><%=dao.countUser() %>
            </div>
          </div>
          <!-- Missing closing tag fixed -->
        </div>

        <div class="col-md-4">
          <div class="card paint-card">
            <div class="card-body text-center text-success">
              <i class="fa fa-calendar-check fa-3x"></i><br />
              <p class="fs-4 text-center">Total Appointment</p>
              <br /><%=dao.countAppointment() %>
            </div>
          </div>
        </div>

        <div class="col-md-4 mt-2">
          <div
            class="card paint-card"
            data-bs-toggle="modal"
            data-bs-target="#exampleModal"
          >
            <div class="card-body text-center text-success">
              <i class="fa fa-calendar-check fa-3x"></i><br />
              <p class="fs-4 text-center">Specialist</p>
              <br /><%=dao.countSpecialist() %>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div
      class="modal fade"
      id="exampleModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <form action="../addSpecialist" method="post">
              <div class="form-group"><label>Enter Specialist Name</label><input type="text" name="specName" class="form-control"></div>
              <div class="text-center mt-3">
                <button type="submit" class="btn btn-primary">Add</button>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Close
            </button>
          </div>
        </div>
      </div>
    </div>
</body>
</html>
