<%--
  Created by IntelliJ IDEA.
  User: pranjal
  Date: 30/10/24
  Time: 9:10 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Add Notes Page</title>
    <%@include file="../resoucres/component/css.jsp"%>
</head>
<body style="background-color: #f0f0f0;">
<%@include file="../resoucres/component/navbar.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-md-10 offset-md-1">
            <div class="card">
                <div class="card-header text-center">
                    <h3>Add your notes </h3>
                    <c:if test="${not empty msg}">
                        <h5 class="fs-5  text-success">${msg}</h5>
                        <c:remove var="msg"/>
                    </c:if>
                </div>
                <div class="card-body">
                    <form action="saveNotes" method="POST">
                        <div class="mb-3"><label>Enter Title</label>
                            <input type="text" name="title" class="form-control">
                        </div>
                        <div class="mb-3"><label>Enter Description</label>
                            <textarea name="description"  rows="4" cols="" class="form-control"></textarea>
                        </div>

                        <button class="btn btn-primary mt-4">Save</button>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
