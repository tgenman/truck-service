<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/manager.css">
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

<div class="container">
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
</div>
</div>
<br><br>
<div class="container">
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
</div>

<br><br>
<div class="container">
    <h3> Drivers </h3>
    <br>
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
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${drivers}" var="driver">
            <tr>
                <th scope="row" class="counterCell"></th>
                <td>${driver.firstName}</td>
                <td>${driver.lastName}</td>

                <td>${driver.status}</td>

                <td>${driver.workedTime}</td>

                <td>${driver.city.name}</td>
                <td>${driver.truck.licensePlate}</td>
                <td>
                    <button type="button" class="btn btn-warning"
                            data-toggle="modal"
                            name="editableDriver"
                            data-target="#editableDriver"
                            data-id-driver="${driver.id}"
                            data-first-name-driver="${driver.firstName}"
                            data-last-name-driver="${driver.lastName}"
                            data-worked-time-driver="${driver.workedTime}">
                        Edit
                    </button>
                </td>
                <td>
                    <sf:form method="post" action="delete-driver">
                        <input type="hidden" value="${driver.id}" name="driverIdDelete">
                        <input type="submit" class="btn btn-danger" value="Delete"/>
                    </sf:form>
                </td>
            </tr>
            <div class="modal fade" id="editableDriver" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Edit driver</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <sf:form action="update-driver" method="post" modelAttribute="driverDTO"
                                     cssClass="form-signin">
                                <sf:hidden name="idDriver" path="id"/>
                                <sf:input name= "firstName" path="firstName" class="form-control" />
                                <sf:input name="lastName" path="lastName" class="form-control" />
                                <sf:input name="workedTime" path="workedTime" class="form-control" />
                                <br>
                                <h6>Driver status</h6>
                                <sf:select path="status" cssClass="form-control">
                                    <sf:options items="${status}"/>
                                </sf:select>
                                <br>
                                <h6>Current location</h6>
                                <sf:select path="city" cssClass="form-control">
                                    <sf:options items="${cities.keySet()}"/>
                                </sf:select>
                                <br>
                                <h6>Truck</h6>
                                <sf:select path="truck" cssClass="form-control">
                                    <sf:options items="${truckDetails}"/>
                                </sf:select>

                                <br><br>
                                <input type="submit" name="save" class="btn btn-lg btn-primary btn-block" value="Save">
                            </sf:form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        </tbody>
    </table>
</div>
<br><br>
<div class="container">
    <h3> Trucks </h3>
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
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${trucks}" var="truck1">
            <tr>
                <th scope="row" class="counterCell"></th>
                <td>${truck1.brand}</td>
                <td>${truck1.model}</td>

                <td>${truck1.capacity}</td>

                <td>${truck1.status}</td>

                <td>${truck1.maxDrivers}</td>
                <td>${truck1.workingSession}</td>
                <td>${truck1.licensePlate}</td>
                <td>${truck1.city.name}</td>
                <td>
                    <button type="button" class="btn btn-warning">Edit</button>
                </td>
                <td>
                    <button type="button" class="btn btn-danger">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    $('#editableDriver').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var idDriver = button.data('id-driver');
        var firstName = button.data('first-name-driver');
        var lastName = button.data('last-name-driver');
        var workedTime = button.data('worked-time-driver');
        var modal = $(this)
        modal.find('.modal-body input').val(idDriver)
        modal.find('.modal-body input[name="save"]').val("Save")
        modal.find('.modal-body input[name="firstName"]').val(firstName)
        modal.find('.modal-body input[name="lastName"]').val(lastName)
        modal.find('.modal-body input[name="workedTime"]').val(workedTime)
    })
</script>




</body>
</html>
