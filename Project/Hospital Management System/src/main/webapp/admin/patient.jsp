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
    </style>
</head>
<body>
    
    <%@include file = "navbar.jsp"%>
    
    <div class="container p-3">
        <div class="row">
            <div class="col-md-12">
                <div class="card point-card">
                    <div class="card-body">
                        <p class="text-center fs-4 fw-bold text-success">Patient Details</p>
                        
                        <c:if test="${not empty succMsg}">
                            <p class="text-center text-success fs-3">${succMsg}</p>
                            <c:remove var="succMsg" scope="session"/>
                        </c:if>

                        <c:if test="${not empty errorMsg}">
                            <p class="text-center text-danger fs-3">${errorMsg}</p>
                            <c:remove var="errorMsg" scope="session"/>
                        </c:if>

                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Full Name</th>
                                    <th scope="col">Gender</th>
                                    <th scope="col">Age</th>
                                    <th scope="col">Appointment Date</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Mobile No</th>
                                    <th scope="col">Disease</th>
                                    <th scope="col">Doctor</th>
                                    <th scope="col">Address</th>
                                    <th scope="col">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    AppointmentDAO dao = new AppointmentDAO(DBConnect.getConn());
                                    DoctorDao dao2 = new DoctorDao(DBConnect.getConn());
                                    List<Appointment> list = dao.getAllAppointments();
                                    for (Appointment ap: list) {
                                        Doctor d = dao2.getDoctorById(ap.getDoctorId());
                                %>
                                <tr>
                                    <td><%=ap.getFullName() %></td>
                                    <td><%=ap.getGender() %></td>
                                    <td><%=ap.getAge() %></td>
                                    <td><%=ap.getAppoint_date() %></td>
                                    <td><%=ap.getEmail() %></td>
                                    <td><%=ap.getPhno() %></td>
                                    <td><%=d.getFullName() %></td>
                                    <td><%=ap.getDisease() %></td>
                                    <td><%=ap.getAddress() %></td>
                                    <td>
                                        <% if ("Pending".equals(ap.getStatus())) { %>
                                            <a href="#" class="btn btn-sm btn-warning">Pending</a>
                                        <% } else { %>
                                            <%= ap.getStatus() %>
                                        <% } %>
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
