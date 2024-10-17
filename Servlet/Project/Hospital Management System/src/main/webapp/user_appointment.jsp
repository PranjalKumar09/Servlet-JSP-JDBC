<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.db.DBConnect, java.util.List,  com.entity.Doctor, com.dao.DoctorDao, com.entity.User" %>
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
            <div class="col-md-5 p-5">
                <img src="img/doc1.png" alt="Doctor Image" class="img-fluid">
            </div>
            <div class="col-md-6">
                <div class="card paint-card">
                    <div class="card-body">
                        <p class="text-center fs-3">User Appointment</p>
                        <c:if test="${not empty succMsg}">
                            <p class="text-center text-success fs-5">${succMsg}</p>
                            <c:remove var="succMsg" scope="session"/>
                        </c:if>
                        <c:if test="${not empty errorMsg}">
                            <p class="text-center text-danger fs-5">${errorMsg}</p>
                            <c:remove var="errorMsg" scope="session"/>
                        </c:if>
                        <form class="row g-3" action="addAppointment" method="post">
                            <input type="hidden" name="user_id" value="${userObj.getId()}">
                            <div class="col-md-6">
                                <label for="fullName" class="form-label">Full Name</label>
                                <input required name="fullName" type="text" class="form-control" id="fullName">
                            </div>
                            <div class="col-md-6">
                                <label for="gender" class="form-label">Gender</label>
                                <select required name="gender" class="form-control" id="gender">
                                    <option value="">--Select--</option>
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label for="age" class="form-label">Age</label>
                                <input required name="age" type="number" class="form-control" id="age">
                            </div>
                            <div class="col-md-6">
                                <label for="appoint_date" class="form-label">Appointment Date</label>
                                <input required name="appoint_date" type="date" class="form-control" id="appoint_date">
                            </div>
							<div class="col-md-6">
                                <label for="email" class="form-label">Email </label>
                                <input required name="email"  type="email" class="form-control" id="email">
                            </div>
                            <div class="col-md-6">
                                <label for="phno" class="form-label">Phone No</label>
                                <input required name="phno" maxlength="13" type="tel" class="form-control" id="phno">
                            </div>
                            <div class="col-md-6">
                                <label for="disease" class="form-label">Disease</label>
                                <input required name="disease" type="text" class="form-control" id="disease">
                            </div>
                            <div class="col-md-6">
                                <label for="doct" class="form-label">Doctor</label>
                                <select required name="doctor_id" class="form-control" id="doct">
                                    <option value="">--Select--</option>
									<%  DoctorDao dao2 = new DoctorDao(DBConnect.getConn());
									List<Doctor> docList = dao2.getAllDoctor();
									for (Doctor d: docList){ %>
										<option value="<%=d.getId()%>">Dr <%=d.getFullName() %> (<%=d.getSpecailist() %>)</option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="col-md-12">
                                <label for="address" class="form-label">Full Address</label>
                                <input required name="address" type="text" class="form-control" id="address">
                            </div>
							<c:if test="${not empty userObj}">
								<button type="submit" class="btn btn-success col-md-12">Submit</button>
							</c:if>
							<c:if test="${empty userObj}">
								<a href="user_login.jsp" class="btn btn-success col-md-12 text-center">Login to Submit</a>
							</c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
