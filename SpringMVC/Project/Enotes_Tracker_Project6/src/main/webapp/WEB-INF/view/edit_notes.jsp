<%--
  Created by IntelliJ IDEA.
  User: pranjal
  Date: 30/10/24
  Time: 9:10 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edit Page</title>
    <%@include file="../resoucres/component/css.jsp"%>
</head>
<body style="background-color: #f0f0f0;">
<%@include file="../resoucres/component/navbar.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-md-10 offset-md-1">
            <div class="card">
                <div class="card-header text-center">
                    <h3>Edit your notes </h3>
                </div>
                <div class="card-body">
                    <form action="update_notes" method="POST">
                        <div class="mb-3"><label>Enter Title</label><input type="text" name="title" class="form-control" value="${notes.title}"></div>
                        <div class="mb-3"><label>Enter Description</label>
                            <textarea rows="4" cols="" class="form-control" name="description">${notes.description}"</textarea>
                        </div>
                        <input type="hidden" name="id" value="${notes.id}" >
                        <button class="btn btn-primary mt-4">Update</button>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
