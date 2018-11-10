<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <link href="${pageContext.request.contextPath}/static/css/sign-in.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"/>
    <title>Sign In</title>
</head>
<body class="text-center">
<sf:form action="processAuthInput" modelAttribute="user" method="post" class="form-signin">
    <h1 class="h3 mb-3 font-weight-normal">Greetings!</h1>
    <sf:input id="companyId" path="companyId" class="form-control" placeholder="ID manager/ drivers"/>

    <sf:input path="password" type="password" id="inputPassword" class="form-control" placeholder="Password"
              required=""/>
    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign In"/>
</sf:form>
</body>
</html>
