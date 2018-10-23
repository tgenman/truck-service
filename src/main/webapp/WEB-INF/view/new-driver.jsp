<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="static/css/sign-in.css">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
</head>
<body class="text-center">
<sf:form action="processNewDriverData" method="post" modelAttribute="driver" cssClass="form-signin">
    <h1 class="h3 mb-3 font-weight-normal">Новый водитель</h1>
    <sf:input path="firstName" class="form-control" placeholder="Имя"/>
    <sf:input path="lastName" class="form-control" placeholder="Фамилия"/>
    <sf:input path="city" class="form-control" placeholder="Город"/>
    <input type="submit" value="Добавить">
</sf:form>
</body>
</html>
