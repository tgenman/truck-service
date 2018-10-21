<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/signin.css"/>
    <title>Title</title>
</head>
<body class="text-center">
<sf:form action="processData" modelAttribute="user" method="post" class="form-signin">
    <h1 class="h3 mb-3 font-weight-normal">Неверный id / пароль. Попробуйте еще раз.</h1>
    <sf:input id="companyId" path="companyId" class="form-control" placeholder="ID сотрудника"/>

    <sf:input path="password" type="password" id="inputPassword" class="form-control" placeholder="Пароль"
              required=""/>
    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Войти"/>
</sf:form>
</body>
</html>
