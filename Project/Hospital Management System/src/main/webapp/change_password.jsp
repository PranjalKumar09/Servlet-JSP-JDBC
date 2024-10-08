<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page import="com.entity.User" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Doctor ~ Admin</title>
    <%@include file = "component/allcss.jsp"%>
    <style type="text/css">
        .paint-card {
            box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
        }
    </style>
  </head>
  <body>
    <%@include file = "component/navbar.jsp" %>

    <c:if test="${empty userObj}">
        <c:redirect url="user_login.jsp"></c:redirect>
    </c:if>

    <div class="container p-4">
        <div class="row">
            <div class="col-md-4 offset-md-4">
                <div class="card paint-card">
                    <p class="text-center fs-3">Change Password</p>
                    <c:if test="${not empty succMsg}">
							<p class="text-center text-success fs-5">${succMsg}</p>
							<c:remove var="succMsg" scope="session"/>
						</c:if>


						<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger fs-5">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session"/>
						</c:if>
                    <div class="card-body">
                        <form action="userChangePassword" method="post">
                            <div class="mb-3"><label>Enter New Password</label><input name="newPassword" required type="text" class="form-control"></div>
                            <div class="mb-3"><label>Enter Old Password Password</label><input name="OldPassword" required type="text" class="form-control"></div>
                            <input type="hidden" name="uid" value="${userObj.id}">
                            <button class="btn btn-success col-md-12">Change Password</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


  </body>
</html>
    
