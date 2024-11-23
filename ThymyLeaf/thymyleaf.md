we can get themyleaf ->
<head lang="en" xmlns:th="http://www.thymeleaf.org">\
    
    <!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
th:fragment="layout(content)">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--code1-->
</head>
<body>
    <!--code2 -->
    <div th:replace="${content}"></div>

    <!-- code3 -->
</body>
</html>


<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
th:replace="base::layout(~{::section})">

<body>
<section>
    <h1>Home</h1>
</section>

</body>
it must have sectoin as in th:repllace it is there  
