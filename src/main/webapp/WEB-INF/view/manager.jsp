<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>
</head>
<body>
<h1>ЗДЕСЬ БУДЕТ ДАШБОРД</h1>

<div class="btn-group dropright">
    <button type="button" class="btn btn-secondary dropdown-toggle"
            data-toggle="dropdown"
            aria-haspopup="true" aria-expanded="false">
        Drivers
    </button>
    <div class="dropdown-menu">
        <a class="dropdown-item" href="newDriver">Add driver</a>
        <a class="dropdown-item" href="#">Update driver</a>
        <a class="dropdown-item" href="#">Delete driver</a>
    </div>
</div>
<br><br>
<div class="btn-group dropright">
    <button type="button" class="btn btn-secondary dropdown-toggle"
            data-toggle="dropdown"
            aria-haspopup="true" aria-expanded="false">
        Trucks
    </button>
    <div class="dropdown-menu">
        <a class="dropdown-item" href="newTruck">New truck</a>
        <a class="dropdown-item" href="#">Update truck</a>
        <a class="dropdown-item" href="#">Delete truck</a>
    </div>
</div>
<br><br>
<h3> Drivers </h3>
<div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">№</th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col">Status</th>
            <th scope="col">Worked time</th>
            <th scope="col">City</th>
            <th scope="col">Truck</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${drivers}" var="driver">
            <tr>
                <th scope="row">${driver.id}</th>
                <td>${driver.firstName}</td>
                <td>${driver.lastName}</td>

                <td>${driver.status}</td>

                <td>${driver.workedTime}</td>

                <td>${driver.city.name}</td>
                <td>${driver.truck.licensePlate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br><br>
<h3> Trucks </h3>
<div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">№</th>
            <th scope="col">Brand</th>
            <th scope="col">Model</th>
            <th scope="col">Capacity</th>
            <th scope="col">Status</th>
            <th scope="col">Max Drivers</th>
            <th scope="col">Max working session</th>
            <th scope="col">License plate</th>
            <th scope="col">City</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${trucks}" var="truck1">
            <tr>
                <th scope="row">${truck1.id}</th>
                <td>${truck1.brand}</td>
                <td>${truck1.model}</td>

                <td>${truck1.capacity}</td>

                <td>${truck1.status}</td>

                <td>${truck1.maxDrivers}</td>
                <td>${truck1.workingSession}</td>
                <td>${truck1.licensePlate}</td>
                <td>${truck1.city.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
