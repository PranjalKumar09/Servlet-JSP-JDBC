<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ page import="com.entity.User" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<nav class="navbar navbar-expand-lg navbar-dark bg-success">
  <div class="container-fluid">
    <a class="navbar-brand" href="#"><i class="fa-solid fa-money-bill"></i>Expense Tracker</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">

        <c:if test="${not empty user}">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="../user/home.jsp"><i class="fa-solid fa-house"></i> Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="add_expense..jsp"><i class="fa-solid fa-plus"></i>Add Expense</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="../user/view_expense.jsp" tabindex="-1"> <i class="fa-solid fa-eye"></i></i>View Expense</a>
          </li>
        </c:if>
        <c:if test="${empty user}">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="index.jsp"><i class="fa-solid fa-house"></i> Home</a>
          </li>
        </c:if>


      </ul>
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">

          <c:if test="${not empty user}">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#"><i class="fa-solid fa-circle-user"></i>${user.fullName}</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" href="../logout" tabindex="-1"> <i class="fa-solid fa-arrow-right-from-bracket"></i>Logout</a>
            </li>
        </c:if>


          <c:if test="${empty user}">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="login.jsp"><i class="fa-solid fa-right-to-bracket"></i> Login</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="register.jsp" tabindex="-1"><i class="fa-solid fa-right-to-bracket"></i> Register</a>
              </li>
          </c:if>
      </ul>
    </div>
  </div>
</nav>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<style>
  body {
    background-color: #f8f9fa; /* Light background */
  }
  .card {
    border-radius: 10px; /* Rounded corners for the card */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Subtle shadow effect */
  }
  .btn-primary {
    transition: background-color 0.3s, transform 0.2s;
  }
  .btn-primary:hover {
    background-color: #0056b3; /* Darker blue on hover */
    transform: translateY(-2px); /* Slight lift effect */
  }
</style>