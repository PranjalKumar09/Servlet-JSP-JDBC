<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page isELIgnored="false"%>
<%@ page import="com.db.DBConnect, java.sql.Connection, java.util.List, com.entity.Specialist, com.dao.SpecialistDao, com.entity.Doctor, com.dao.DoctorDao" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Doctor ~ Admin</title>
    <%@include file = "./../component/allcss.jsp"%>
  </head>
  <body>
    <%@include file = "navbar.jsp" %>

    <div class="container-fluid p-3">
		<div class="row">
			<div class="col-md-12">
                <div class="card point-card">
                    <div class="card-body">
                        <p class="fs-3 text-center">Doctor Details</p>
                        <c:if test="${not empty succMsg}">
                                        <p class="text-center text-success fs-5">${succMsg}</p>
                                        <c:remove var="succMsg" scope="session"/>
                            </c:if>


                                    <c:if test="${not empty errorMsg}">
                                        <p class="text-center text-danger fs-5">${errorMsg}</p>
                                        <c:remove var="errorMsg" scope="session"/>
                                    </c:if>
                        <table class="table table-striped"> <!-- Added Bootstrap classes -->
                            <thead class="table-dark"> <!-- Added class for dark header -->
                                <tr>
                                    <th>Full Name</th>
                                    <th>DOB</th>
                                    <th>Qualification</th>
                                    <th>Specialist</th>
                                    <th>Email</th>
                                    <th>Mob No</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                DoctorDao dao2 = new DoctorDao(DBConnect.getConn());
                                List<Doctor> docList = dao2.getAllDoctor();
                                if (docList != null && !docList.isEmpty()) { // Check if the list is not null or empty
                                    for (Doctor doc : docList) { %>
                                        <tr>
                                            <td><%= doc.getFullName() %></td>
                                            <td><%= doc.getDob() %></td>
                                            <td><%= doc.getQualification() %></td>
                                            <td><%= doc.getSpecailist() %></td>
                                            <td><%= doc.getEmail() %></td>
                                            <td><%= doc.getMobNo() %></td>
                                            <td>
                                                <a href="edit_doctor.jsp?id=<%=doc.getId()%>" class="btn btn-primary btn-success">Edit</a>
                                                
                                                <a href="../deleteDoctor?id=<%=doc.getId()%>" class="btn btn-sn btn-danger">Delete</a>
                                            </td>
                                        </tr>
                                    <% }
                                } else { %>
                                    <tr>
                                        <td colspan="7" class="text-center">No doctors found.</td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
			</div>

		</div>
	</div>
  </body>
</html>
    
