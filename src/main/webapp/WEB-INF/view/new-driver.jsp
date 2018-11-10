<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="static/css/sign-in.css">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
</head>
<body class="text-center">
<sf:form action="processNewDriverData" method="post" modelAttribute="driverDTO" cssClass="form-signin">
    <h1 class="h3 mb-3 font-weight-normal">New drivers</h1>
    <sf:input path="firstName" class="form-control" placeholder="First name"/>
    <sf:input path="lastName" class="form-control" placeholder="Last name"/>
    <sf:select path="cityId">
        <sf:options items="${cities}"/>
    </sf:select>

    <br><br>
    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Submit">
</sf:form>
</body>
</html>
