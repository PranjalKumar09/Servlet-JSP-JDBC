<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.db.DBConnect, java.sql.Connection" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index Page</title>
    <%@include file = "component/allcss.jsp"%>


        <style type="text/css">
        /* Make body take full height and use flexbox */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
        }

        /* Make content take up available space */
        .content {
            flex: 1;
        }

        /* Basic footer styles */
        footer {
            background-color: #28a745;
            color: white;
            text-align: center;
            padding: 10px;
        }
        .paint-card {
            box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
        }
    
    </style>

</head>
<body>
	<%@include file = "component/navbar.jsp"%>


    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="img/hos.jpg" class="d-block w-100" alt="..." height="500px">
            </div>
            <div class="carousel-item">
                <img src="img/hos2.jpg" class="d-block w-100" alt="..." height="500px">
            </div>
            <div class="carousel-item">
                <img src="img/hos3.jpg" class="d-block w-100" alt="..." height="500px">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>



    <div class="container p-3">
        <div class="row">
            <div class="col-md-8 p-5">
                <div class="row">
                    <div class="col-md-6">
                        <div class="card paint-card">
                            <div class="card-body">
                                <p class="fs-5">100% Safety</p>
                                <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Doloremque nulla fugiat error velit voluptates qui explicabo commodi culpa illum sunt?</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card paint-card">
                            <div class="card-body">
                                <p class="fs-5">Clean Environment</p>
                                <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Doloremque nulla fugiat error velit voluptates qui explicabo commodi culpa illum sunt?</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mt-2">
                        <div class="card paint-card">
                            <div class="card-body">
                                <p class="fs-5">Friendly Doctors</p>
                                <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Doloremque nulla fugiat error velit voluptates qui explicabo commodi culpa illum sunt?</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mt-2">
                        <div class="card paint-card">
                            <div class="card-body">
                                <p class="fs-5">Medical Research</p>
                                <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Doloremque nulla fugiat error velit voluptates qui explicabo commodi culpa illum sunt?</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 p-5">
                <img src="img/doc1.png" alt="Doctor Image" class="img-fluid">
            </div>
        </div>
    </div>

    <hr>
    <div class="container p-2">
        <p class="text-center fs-2">Our Team</p>

        <div class="row">

            <div class="col-md-3">
                <div class="card paint-card">
                    <div class="card-body text-center">
                        <img src="img/doc2.jpg" width="250px" height="300px">
                        <p class="fw-bold fs-5">Dr Apurva Kacher</p>
                        <p class="fs-7">heart Specialist </p>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <div class="card paint-card">
                    <div class="card-body text-center">
                        <img src="img/doc8.jpg" width="250px" height="300px">
                        <p class="fw-bold fs-5">Dr Nicole Dull</p>
                        <p class="fs-7">CEO & Chairmen </p>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <div class="card paint-card">
                    <div class="card-body text-center">
                        <img src="img/doc3.jpg" width="250px" height="300px">
                        <p class="fw-bold fs-5">Dr Siva Kumar</p>
                        <p class="fs-7">Chief Doctor </p>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <div class="card paint-card">
                    <div class="card-body text-center">
                        <img src="img/doc6.jpg" width="250px" height="300px">
                        <p class="fw-bold fs-5">Dr Niuse Paule</p>
                        <p class="fs-7">Chief Doctor </p>
                    </div>
                </div>
            </div>
        
        </div>
    </div>
    </hr>

    <%@include file="component/footer.jsp" %>
</body>
</html>
