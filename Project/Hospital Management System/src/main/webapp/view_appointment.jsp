<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.db.DBConnect, java.util.List,  com.entity.Doctor, com.entity.User, com.entity.Appointment, com.dao.AppointmentDAO , com.dao.DoctorDao" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Appointment</title>
    <%@include file="component/allcss.jsp" %>
    <style type="text/css">
        .paint-card {
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.3);
        }
        .backImg {
            background: linear-gradient(rgba(0, 0, 0, .4), rgba(0, 0, 0, .4)), url("img/hos2.jpg");
            height: 20vh;
            width: 100%;
            background-size: cover;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
    <%@include file="component/navbar.jsp" %>
    <div class="container-fluid backImg p-5">
        <p class="text-center fs-2 text-white"></p>
    </div>
    <div class="container p-3">
        <div class="row">
            <div class="col-md-9">
                <div class="card paint-card">
                    <div class="card-body">
                        <p class="text-center fs-4 fw-bold text-success">Appointment List</p>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Full Name</th>
                                    <th scope="col">Gender</th>
                                    <th scope="col">Age</th>
                                    <th scope="col">Appoint Date</th>
                                    <th scope="col">Disease </th>
                                    <th scope="col">Address</th>
                                    <th scope="col">Doctor Name</th>
                                    <th scope="col">Status </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                User user = (User) session.getAttribute("userObj");
                                AppointmentDAO dao = new AppointmentDAO(DBConnect.getConn());
                                DoctorDao dao2 = new DoctorDao(DBConnect.getConn());
                                List<Appointment> list = dao.getAllAppointmentsByUserId(user.getId());
                                for (Appointment ap: list){
                                    Doctor d = dao2.getDoctorById(ap.getDoctorId());
                                %>
                                <tr>
                                    <th><%=ap.getFullName() %></th>
                                    <td><%=ap.getGender() %></td>
                                    <td><%=ap.getAge() %></td>
                                    <td><%=ap.getAppoint_date() %></td>
                                    <td><%=ap.getDisease() %></td>
                                    <td><%=ap.getAddress() %></td>
                                    <td><%=d.getFullName() %></td>
                                    <td>
                                        
                                        <% if ("Pending".equals(ap.getStatus())) { %>
                                            <a href="#" class="btn btn-sm btn-warning">Pending</a>
                                        <% } else { %>
                                            <%= ap.getStatus() %>
                                        <% } %>

                                    </td>
                                    
                                </tr>
                                <% } %>
                               
                            </tbody>
                            </table>

                    </div>
                </div>
            </div>
            <div class="col-md-3 p-3"><img src="img/doc1.png" alt="Image of Doctor"></div>
        </div>
    </div>
</body>
</html>
