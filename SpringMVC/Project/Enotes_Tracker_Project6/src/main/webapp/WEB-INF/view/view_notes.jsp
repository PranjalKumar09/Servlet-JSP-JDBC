<%--
  Created by IntelliJ IDEA.
  User: pranjal
  Date: 30/10/24
  Time: 9:10 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>View Notes Page</title>
    <%@include file="../resoucres/component/css.jsp"%>
</head>
<body>
    <%@include file="../resoucres/component/navbar.jsp"%>
    <div class="container-fluid p-4">
        <h4 class="text-center">View All Notes</h4>
        <div class="row">
            <div class="text-center">
                <img src="<c:url value='/resources/img/img2.jpg' />" width="100px" height="100px" alt="">
            </div>
            <div class="col-md-10 offset-md-1 mt-2">
                <div class="card">
                    <div class="card-body">
                        <p>What is java</p>
                        <p>What is java2</p>
                        <p>What is java3</p>
                        <p>Publish Date: YYYY-MM-DD</p>
                        <div class="text-center">
                            <a href="edit_notes" class="btn btn-primary btn-sm">Edit</a>
                            <a href="#" class="btn btn-danger btn-sm">Delete</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-10 offset-md-1 mt-2">
                <div class="card">
                    <div class="card-body">
                        <p>What is Python</p>
                        <p>What is Python2</p>
                        <p>What is Python3</p>
                        <p>Publish Date: YYYY-MM-DD</p>
                        <div class="text-center">
                            <a href="edit_notes" class="btn btn-primary btn-sm">Edit</a>
                            <a href="#" class="btn btn-danger btn-sm">Delete</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
