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
			<div class="col-md-5 offset-md-4">
				<div class="card point-card">
					<div class="card-body">
						<p class="fs-3 text-center">Add Doctor</p>

						<c:if test="${not empty succMsg}">
							<p class="text-center text-success fs-5">${succMsg}</p>
							<c:remove var="succMsg" scope="session"/>
						</c:if>


						<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger fs-5">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session"/>
						</c:if>

						<form action="../addDoctor" method="post">
							<div class="mb-3">
								<label class="form-label">Full Name</label>
								<input required name="fullName" type="text" class="form-control">
							</div>
							<div class="mb-3">
								<label class="form-label">DOB</label>
								<input required name="dob" type="date" class="form-control">
							</div>
							<div class="mb-3">
								<label class="form-label">Qualification</label>
								<input required name="qualification" type="text" class="form-control">
							</div>
							<div class="mb-3">
								<label class="form-label">Specialist</label>
								<select required name="specialist" class="form-control">
								<option>--Select--</option>
								<% SpecialistDao dao = new SpecialistDao(DBConnect.getConn());
								List<Specialist> specList = dao.getAllSpecialist();
								for (Specialist spec : specList) { %>
									<option><%=spec.getSpecialistName()%></option>
								<% } %>
								</select>
							</div>
                            
							<div class="mb-3">
								<label class="form-label">Email</label>
								<input required name="email" type="email" class="form-control">
							</div>
							<div class="mb-3">
								<label class="form-label">Mobile Number</label>
								<input required name="mobNo" type="text" class="form-control">
							</div>
							<div class="mb-3">
								<label class="form-label">Password</label>
								<input required name="password" type="password" class="form-control">
							</div>

                            
							<button type="submit" class="btn bg-success text-white col-md-12">Submit</button>
						</form>
					</div>
				</div>
			</div>
			

		</div>
	</div>
  </body>
</html>
    
