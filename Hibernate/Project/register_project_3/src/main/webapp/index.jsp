<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index Page</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    

</head>
<body clas="bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-6 offset-md-3 mt-3">
                <div class="card">
                    <div class="card-header text-center fs-3">
                        Emp Register
                    </div>
                    <%
                    String msg  = (String) session.getAttribute("msg");           
                    if (msg!=null) {
                    %>
                        <p class="text-center fs-4 text-success"><%=msg %></p>
                    <%    
                    }
                    session.removeAttribute("msg");
                    %>
                    <div class="card-body">
                        <div class="mb-3">
                            <form method="post" action="register">
                                <div class="mb-3">
                                    <label for="exampleInputEmail1" class="form-label">Name</label>
                                    <input type="name" class="form-control" name="name">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleInputEmail1" class="form-label">Dapartment</label>
                                    <input type="name" class="form-control" name="department">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleInputEmail1" class="form-label">Salary</label>
                                    <input type="name" class="form-control" name="salary">
                                </div>
                                
                                <div class="mb-3">
                                    <label for="exampleInputEmail1" class="form-label">Email address</label>
                                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email">
                                    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                                </div>
                                
                                <div class="mb-3">
                                    <label for="exampleInputPassword1" class="form-label">Password</label>
                                    <input type="password" class="form-control">
                                </div>
        
                                <button type="submit" class="btn btn-primary col-md-12">Register</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>