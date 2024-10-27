<%--
  Created by IntelliJ IDEA.
  User: pranjal
  Date: 26/10/24
  Time: 10:30 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>File Upload</title>
    <%@ include file="../resources/css/css.jsp"%>
</head>
<body>
    <div class="container text-center">
        <h1>File Uploading Example</h1>
        <form action="fileupload" enctype="multipart/form-data" method="post">
            <div class="mb-3 col-md-6 offset-md-3 mt-4"><input type="file" name="img"class="form-control"></div>
            <div class="mb-3 col-md-4 offset-md-4 mt-4">
                <button class="btn btn-primary">Upload</button>
            </div>

        </form>
    </div>
</body>
</html>
