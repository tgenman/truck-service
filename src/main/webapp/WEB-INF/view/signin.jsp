<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <link href="static/css/signin.css" rel="stylesheet">
    <link rel="stylesheet" href="static/css/bootstrap.min.css"/>
    <title>Sign In</title>
</head>
<body class="text-center">
<sf:form action="processData" modelAttribute="user" method="post" class="form-signin">
    <h1 class="h3 mb-3 font-weight-normal">Добрый день!</h1>
    <sf:input id="companyId" path="companyId" class="form-control" placeholder="ID сотрудника"/>

    <sf:input path="password" type="password" id="inputPassword" class="form-control" placeholder="Пароль"
              required=""/>
    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Войти"/>
</sf:form>
</body>
</html>
