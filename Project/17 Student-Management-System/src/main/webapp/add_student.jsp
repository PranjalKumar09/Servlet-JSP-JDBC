<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="WEB-INF/mylib.tld" prefix="t" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%@include file="all_css.jsp" %>
</head>
<body class="bg-light">
	<%@include file="navbar.jsp" %>
    <div class="container p-4">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-body"></div>
                    <p class="fs-3 text-center">Add Student</p>
                    <c:if test="${not empty succMsg}">
                        <p class="text-center">${succMsg}</p>
                        <c:remove var="succMsg" />
                    </c:if>
                    
                    <c:if test="${not empty errorMsg}">
                        <p class="text-center">${errorMsg}</p>
                        <c:remove var="errorMsg" />
                    </c:if>




                    <form action="register" method="post">
                        <div class="mb-3">
                            <label class="form-label">
                            Full Name
                            </label>
                            <input type="text" class="form-control" name="name">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">
                            Date of Birth
                            </label>
                            <input type="text" class="form-control" name="dob">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">
                            Address
                            </label>
                            <input type="text" class="form-control" name="address">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">
                            Qualificication
                            </label>
                            <input type="text" class="form-control" name="qualification">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                            Email address
                            </label>
                            <input type="email" class="form-control" name="email">
                        </div>
                        
                        </div>
                        <button type="submit" class="btn btn-primary" name="submit">
                            Submit
                        </button>
                    </form>
                </div>
            
            
            </div>
        </div>
    
    </div>
    
    

</body>
</html>

