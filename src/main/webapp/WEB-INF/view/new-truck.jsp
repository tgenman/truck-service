<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="static/css/sign-in.css">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <title>New Truck</title>
</head>
<body>
<sf:form action="processNewTruckData" method="post" modelAttribute="truckDTO" cssClass="form-signin">
    <h1 class="h3 mb-3 font-weight-normal">New truck</h1>
    <sf:input path="brand" class="form-control" placeholder="Brand"/>
    <sf:input path="model" class="form-control" placeholder="Model"/>
    <sf:input path="licensePlate" class="form-control" placeholder="License plate"/>
    <sf:input path="workingSession" class="form-control" placeholder="Max working session"/>
    <sf:input path="capacity" class="form-control" placeholder="Capacity (tons)"/>
    <sf:input path="maxDrivers" class="form-control" placeholder="Max drivers"/>
    <br>
    <h5>Current location</h5>
    <sf:select path="city" cssClass="form-control" >
        <sf:options items="${cities.keySet()}"/>
    </sf:select>
    <br>
    <h5>Operable status</h5>
    <sf:select path="status" cssClass="form-control">
        <sf:options items="${truckStatus}"/>
    </sf:select>
    <br><br>
    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Submit">
</sf:form>
</body>
</html>
