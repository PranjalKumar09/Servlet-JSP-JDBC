<%--
  Created by IntelliJ IDEA.
  User: pranjal
  Date: 30/10/24
  Time: 9:10 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>View Notes Page</title>
    <%@include file="../resoucres/component/css.jsp"%>
</head>
<body>
    <%@include file="../resoucres/component/navbar.jsp"%>
    <div class="container-fluid p-4">
        <h4 class="text-center">View All Notes</h4>
        <c:if test="${not empty msg}">
            <h5 class="fs-5  text-danger text-center">${msg}</h5>
            <c:remove var="msg"/>
        </c:if>
        <div class="row">
            <div class="text-center">
                <img src="<c:url value='/resources/img/img2.jpg' />" width="100px" height="100px" alt="">
            </div>
            <jsp:useBean id="notesList" scope="request" type="java.util.List"/>
            <c:forEach items="${notesList}" var="note" >
                <div class="col-md-10 offset-md-1 mt-2">
                    <div class="card">
                        <div class="card-body">
                            <p>${note.title}</p>
                            <p>${note.description}</p>
                            <p>Publish Date: ${note.publishDate}</p>
                                <div class="text-center">
                                    <a href="edit_notes?id=${note.id}" class="btn btn-primary btn-sm">Edit</a>
                                    <a href="deleteNotes?id=${note.id}" class="btn btn-danger btn-sm">Delete</a>
                                </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
