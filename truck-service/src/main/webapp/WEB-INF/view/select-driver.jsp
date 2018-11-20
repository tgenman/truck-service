<%--suppress ALL --%>
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
    <link rel="stylesheet" href="static/css/order.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>
    <title>Title</title>
</head>
<body>
<div class="container-fluid">
    <div class="row border-between">
        <div class="col-4">

            <h5>Customer: ${orderDTO.customer}</h5>
            <br>
            <sf:form action="save-order" modelAttribute="orderDTO">

                <h5>Select available drivers:</h5>
                <sf:checkboxes element="div class='checkbox'" path="drivers" items="${drivers}"/>
                <br>
                <br><br>
                <input type="submit"
                       class="btn btn-lg btn-primary" value="Confirm"/>
            </sf:form>

            <br>
            <br>
            <br>
            <sf:form action="abort-order" method="post">
                <input type="submit" value="Abort Order" class="btn btn-danger"/>
            </sf:form>
        </div>
        <div class="col-8">
            <h4>Route</h4>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">№</th>
                    <th scope="col">City</th>
                    <th scope="col">Cargo name</th>
                    <th scope="col">Cargo weight</th>
                    <th scope="col">Type</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${routeDTO.routePoints}" var="point">
                    <tr>
                        <th scope="row" class="counterCell"></th>
                        <td>${point.cityName}</td>
                        <td>${point.cargoDTO.name}</td>
                        <td>${point.cargoDTO.weight}</td>
                        <td>${point.type.toString()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
