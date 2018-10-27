<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qarnd
  Date: 26/10/2018
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/sign-in.css">
    <title>Title</title>
</head>
<body class="text-center">
<sf:form action="save-order" modelAttribute="orderDetails" method="post" class="form-signin">

    <h6>Customer</h6>
    <sf:input name="name" path="customerName" class="form-control"
              placeholder="Customer name"/>
    <br>
    <h6>Pick up cargo in:</h6>
    <sf:select path="cityPickUp" cssClass="form-control" >
        <sf:options items="${cities.keySet()}"/>
    </sf:select>
    <br>
    <h6>Drop off cargo in:</h6>
    <sf:select path="cityDropOff" cssClass="form-control" >
        <sf:options items="${cities.keySet()}"/>
    </sf:select>
    <h6>Cargo name, product name or smth else</h6>
    <sf:input path="cargoName" id="cargoName" class="form-control" placeholder="Name"
              required=""/>
    <h6>It should have any weight, man</h6>
    <sf:input path="cargoWeight" id="cargoName" class="form-control" placeholder="Weight"
              required=""/>
    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Next"/>
</sf:form>

</body>
</html>
