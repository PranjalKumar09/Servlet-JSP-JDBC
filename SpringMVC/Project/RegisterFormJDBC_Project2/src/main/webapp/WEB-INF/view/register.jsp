<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>

</head>
<body>
<%@include file="css.jsp"%>


<div class="container p">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="card">
                <div class="card-header text-center fs-3">Register Page</div>
                <div class="card-body">
                    <form action="createUser" method="post">
                        <div class="mb-3">
                            <label class="form-label">Full Name</label>
                            <input type="text" class="form-control" name="fullName">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Age</label>
                            <input type="number" class="form-control"  name="age">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email Address: </label>
                            <input type="email" class="form-control" name="email" aria-describedby="emailHelp">
                        </div>

                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Password</label>
                            <input type="password" class="form-control" name="password" id="exampleInputPassword1">
                        </div>

                        <button type="submit" class="btn btn-primary col-md-12">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
