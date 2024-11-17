<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page isELIgnored="false" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><i class="fa-solid fa-book me-1"></i>Enotes</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="home"><i class="fa-solid fa-house me-1"></i>Home</a>
                </li>
                <c:if test="${not empty user}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/user/add_notes">Add Notes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/user/view_notes">View Notes</a>
                    </li>
                </c:if>

            </ul>
            <form class="d-flex">
                <c:if test="${empty user}">
                    <a href="login" class="btn btn-light me-2" type="submit"><i class="fa-solid fa-right-to-bracket me-1"></i>Login</a>
                    <a href="register" class="btn btn-light" type="submit">Register</a>
                </c:if>
                <c:if test="${not empty user}">
                    <a href="#" class="btn btn-light me-2"><i class="fa-solid fa-right-to-bracket me-1"></i><i class="fa-solid fa-user"></i>${user.fullName}</a>
                    <a href="${pageContext.request.contextPath}/user/logout" class="btn btn-light" type="submit">Logout</a>
                </c:if>
            </form>
        </div>
    </div>
</nav>