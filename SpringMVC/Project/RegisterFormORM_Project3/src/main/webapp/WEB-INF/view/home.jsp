<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../resources/css/css.jsp"%>
</head>
<body>
<h1>Home Page</h1>
<a href="register">Register Page</a>


<div class="text-center">
    <a href="register" class="btn btn-primary btn-sm">Reigster</a>
    <a href="google" class="btn btn-danger btn-sm">Go To register</a>
    <a href="file_upload" class="btn btn-primary btn-sm">File Upload</a>
</div>


<div class="container p-5">
    <div class="col-md-8 offset-md-2">
        <div class="card">
            <div class="card-body">
                <h4> My Search Engine</h4>
                <form action="search" method="post">

                    <div class="mb-3">
                        <label>
                            <input type="text" class="form-control" placeholder="Search here..." name="keyword">
                        </label>
                        <button class="btn btn-danger btn-sm mt-3">Go To google</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>


<h1>Displaying an Image</h1>

<!-- Image Display -->
<img src="<c:url value='/resource/img/image.jpeg'/>" alt="My Image" style="max-width: 100%; height: auto;">


</body>
</html>
