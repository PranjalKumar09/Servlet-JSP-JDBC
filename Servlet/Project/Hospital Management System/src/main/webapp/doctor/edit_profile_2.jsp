<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.db.DBConnect, java.util.List,  com.entity.Doctor,  com.dao.SpecialistDao, com.entity.Specialist" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Admin Login</title>
        <%@include file = "./../component/allcss.jsp"%>
        <style>
            .paint-card {
            box-shadow: 0 0 10px 0 rgba(0, 0, 0 , 0.3);
            }
        </style>


    </head>
<body>
  <c:if test="${empty docObj}">
    <c:redirect url="../doctor_login.jsp"></c:redirect>
  </c:if>

  <%@include file = "navbar.jsp"%>

  <div class="container p-4">
      <p class="text-center fs-3">Edit Profile</p>
      <div class="row">
            <div class="col-md-8 col-lg-4">
                <div class="card shadow-lg border-0">
                    <div class="card-body p-4">
                        <p class="fs-3 text-center mb-4 fw-bold">Update Doctor Details</p>

                    

                        <%
                        int id = docObj.getId();
                        %>

                        <form action="../updateDoctor" method="post">
                            <div class="mb-3">
                                <label class="form-label">Full Name</label>
                                <input required name="fullName" type="text" class="form-control" value="<%=d.getFullName() %>">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">DOB</label>
                                <input required name="dob" type="date" class="form-control" value="<%=d.getDob() %>">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Qualification</label>
                                <input required name="qualification" type="text" class="form-control" value="<%=d.getQualification() %>">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Specialist</label>
                                <select required name="specialist" class="form-control">
                                    <option><%=d.getSpecailist()%></option>
                                    <%
                                    SpecialistDao dao = new SpecialistDao(DBConnect.getConn());
                                    List<Specialist> specList = dao.getAllSpecialist();
                                    for (Specialist spec : specList) { %>
                                        <option><%=spec.getSpecialistName()%></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Email</label>
                                <input readonly required name="email" type="email" class="form-control" value="<%=d.getEmail() %>">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Mobile Number</label>
                                <input required name="mobNo" type="text" class="form-control" value="<%=d.getMobNo() %>">
                            </div>
                            
                            <input type="hidden" name="id" value="<%=d.getId()%>">
                            <button type="submit" class="btn btn-success text-white col-md-12">Update</button>
                        </form>
                    </div>
                </div>
            </div>

            
      </div>
    </div>


  </body>
</html>
    
