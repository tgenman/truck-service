<%--suppress ALL --%>
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
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Furious Turtle</a>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="/home">Sign out</a>
        </li>
    </ul>
</nav>
<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <br>
                    <br>
                    <br>
                    <sf:form method="get" action="newDriver">
                        <input type="submit" class="btn btn-primary btn-lg btn-block" value="New Driver">
                    </sf:form>
                    <sf:form method="get" action="newTruck">
                        <input type="submit" class="btn btn-primary btn-lg btn-block" value="New Truck">
                    </sf:form>

                    <div class="btn btn-primary btn-lg btn-block">
                        <a class="btn btn-primary"
                           href="new-order">
                            New order
                        </a>
                    </div>
                </ul>


            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

            <div class="container">

                <br>
                <br>
                <h3>Orders</h3>

                <br>
                <br>

                <c:choose>
                    <c:when test="${orders.size()>0}">
                        <div id="accordion">
                            <div class="panel list-group">
                                <!-- panel class must be in -->
                                <c:forEach items="${orders}" var="order">
                                    <a href="#web_dev${order.id}" data-parent="#accordion" data-toggle="collapse"
                                       class="list-group-item">
                                        <h6>Order ID: ${order.id}   </h6>
                                        <h6>Customer: ${order.customer}</h6>
                                        <h6>Status: ${order.status}   </h6>
                                        <h6>Truck: ${order.truckObject}   </h6>
                                        <h6>Drivers: ${order.driversList}   </h6>
                                    </a>
                                    <sf:form method="post" action="delete-order">
                                        <input type="hidden" value="${order.id}" name="orderId">
                                        <input type="submit" class="btn btn-danger" value="Delete"/>
                                    </sf:form>
                                    <br>
                                    <div class="collapse" id="web_dev${order.id}">
                                        <div>
                                            <h4>Route Points:</h4>
                                            <ol class="list-group-item-text">
                                                <c:forEach items="${order.routePoints}" var="point">
                                                    <li>Type: ${point.type.toString()}</li>
                                                    <h6>City: ${point.city}</h6>
                                                    <c:if test="${point.cargoesForDrop.size() != 0}">
                                                        <c:forEach items="${point.cargoesForDrop}" var="cargo">
                                                            <h6>Cargo for drop: ${cargo.name} , ID: ${cargo.id}</h6>
                                                            <c:choose>
                                                                <c:when test="${point.completed == false}">
                                                                    <h5 style="color: #ff2b3c;">INCOMPLETED</h5>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <h5 style="color: #0f9d58">COMPLETED</h5>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${point.cargo != null}">
                                                        <h6>Cargo: ${point.cargo.name}</h6>
                                                        <c:choose>
                                                            <c:when test="${point.completed == false}">
                                                                <h5 style="color: #ff2b3c;">INCOMPLETED</h5>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <h5 style="color: #0f9d58">COMPLETED</h5>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:if>

                                                </c:forEach>
                                            </ol>
                                        </div>
                                        <br>
                                        <div>
                                            <h4>Cargoes:</h4>
                                            <c:forEach items="${order.cargoes}" var="cargo">
                                                <ul class="list-group-item-text">
                                                    <li>Cargo ID: ${cargo.id}</li>
                                                    <h6>Name: ${cargo.name}</h6>
                                                    <h6>Weight: ${cargo.weight}</h6>
                                                    <h6>Status: ${cargo.status.toString()}</h6>
                                                </ul>
                                            </c:forEach>
                                        </div>
                                        <br>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <h6>No orders now</h6>
                    </c:otherwise>
                </c:choose>

                <br>
                <br>

                <h3> Drivers </h3>
                <br>
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">№</th>
                        <th scope="col">First name</th>
                        <th scope="col">Last name</th>
                        <th scope="col">Status</th>
                        <th scope="col">Week time</th>
                        <th scope="col">Month time</th>
                        <th scope="col">City</th>
                        <th scope="col">Truck</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${drivers}" var="drivers">
                        <tr>
                            <th scope="row" class="counterCell"></th>
                            <td>${drivers.firstName}</td>
                            <td>${drivers.lastName}</td>

                            <td>${drivers.status.toString()}</td>

                            <td>${drivers.shift.timeWeeklyElapsed}</td>
                            <td>${drivers.shift.timeMonthlyElapsed}</td>

                            <td>${drivers.city.name}</td>
                            <td>${drivers.truck.licensePlate}</td>
                            <td>
                                <button type="button" class="btn btn-warning"
                                        data-toggle="modal"
                                        name="editableDriver"
                                        data-target="#editableDriver"
                                        data-id-drivers="${drivers.id}"
                                        data-first-name-drivers="${drivers.firstName}"
                                        data-last-name-drivers="${drivers.lastName}"
                                        data-worked-time-drivers="${drivers.shift.timeWeeklyElapsed}">
                                    Edit
                                </button>
                            </td>
                            <td>
                                <sf:form method="post" action="delete-driver">
                                    <input type="hidden" value="${drivers.id}" name="driverIdDelete">
                                    <input type="submit" class="btn btn-danger" value="Delete"/>
                                </sf:form>
                            </td>
                        </tr>
                        <div class="modal fade" id="editableDriver" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Edit drivers</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <sf:form action="update-driver" method="post" modelAttribute="driverDTO"
                                                 cssClass="form-signin">
                                            <sf:hidden name="idDriver" path="id"/>
                                            <sf:input name="firstName" path="firstName" class="form-control"/>
                                            <sf:input name="lastName" path="lastName" class="form-control"/>
                                            <br>
                                            <h6>Driver status</h6>
                                            <%--  <sf:select path="status" cssClass="form-control">
                                                  <sf:options items="${status}"/>
                                              </sf:select>
                                              --%>

                                            <sf:select path="status">
                                                <c:forEach items="${driverStatus}" var="status">
                                                    <sf:option value="${status}">${status.toString()}</sf:option>
                                                </c:forEach>
                                            </sf:select>
                                            <br>
                                            <h6>Current location</h6>
                                            <sf:select path="cityId" cssClass="form-control">
                                                <sf:options items="${cities}"/>
                                            </sf:select>
                                            <br>

                                            <br><br>
                                            <input type="submit" name="save" class="btn btn-lg btn-primary btn-block"
                                                   value="Save">
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
                <table class="table table-hover table-bordered">
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
                                <button type="button" class="btn btn-warning"
                                        data-toggle="modal"
                                        name="editableTruck"
                                        data-target="#editableTruck"
                                        data-id-truck="${truck1.id}"
                                        data-brand-truck="${truck1.brand}"
                                        data-model-truck="${truck1.model}"
                                        data-capacity-truck="${truck1.capacity}"
                                        data-working-session-truck="${truck1.workingSession}"
                                        data-max-drivers-truck="${truck1.maxDrivers}"
                                        data-license-truck="${truck1.licensePlate}">
                                    Edit
                                </button>
                            </td>
                            <td>
                                <sf:form method="post" action="delete-truck">
                                    <input type="hidden" value="${truck1.id}" name="truckIdDelete">
                                    <input type="submit" class="btn btn-danger" value="Delete"/>
                                </sf:form>
                            </td>
                        </tr>
                        <div class="modal fade" id="editableTruck" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Edit drivers</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <sf:form action="update-truck" method="post" modelAttribute="truckDTO"
                                                 cssClass="form-signin">
                                            <sf:hidden name="idDriver" path="id"/>
                                            <sf:input name="brand" path="brand" class="form-control"
                                                      placeholder="Brand"/>
                                            <sf:input name="model" path="model" class="form-control"
                                                      placeholder="Model"/>
                                            <sf:input name="licensePlate" path="licensePlate" class="form-control"
                                                      placeholder="License plate"/>
                                            <sf:input name="workingSession" path="workingSession" class="form-control"
                                                      placeholder="Max working session"/>
                                            <sf:input name="capacity" path="capacity" class="form-control"
                                                      placeholder="Capacity (tons)"/>
                                            <sf:input name="maxDrivers" path="maxDrivers" class="form-control"
                                                      placeholder="Max drivers"/>
                                            <br>
                                            <h5>Current location</h5>
                                            <sf:select path="city" cssClass="form-control">
                                                <sf:options items="${cities}"/>
                                            </sf:select>
                                            <br>
                                            <h5>Operable status</h5>
                                            <sf:select path="status" cssClass="form-control">
                                                <sf:options items="${truckStatus}"/>
                                            </sf:select>
                                            <br><br>
                                            <input type="submit" name="save" class="btn btn-lg btn-primary btn-block"
                                                   value="Save">
                                        </sf:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <script>
                $('#editableDriver').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget) // Button that triggered the modal
                    var idDriver = button.data('id-drivers');
                    var firstName = button.data('first-name-drivers');
                    var lastName = button.data('last-name-drivers');
                    var shiftId = button.data('worked-time-drivers');
                    var modal = $(this)
                    modal.find('.modal-body input').val(idDriver)
                    modal.find('.modal-body input[name="save"]').val("Save")
                    modal.find('.modal-body input[name="firstName"]').val(firstName)
                    modal.find('.modal-body input[name="lastName"]').val(lastName)
                    modal.find('.modal-body input[name="shiftId"]').val(shiftId)
                })

                $('#editableTruck').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget) // Button that triggered the modal
                    var idTruck = button.data('id-truck');
                    var brand = button.data('brand-truck');
                    var model = button.data('model-truck');
                    var capacity = button.data('capacity-truck');
                    var workingSession = button.data('working-session-truck');
                    var maxDrivers = button.data('max-drivers-truck');
                    var licensePlate = button.data('license-truck');

                    var modal = $(this)
                    modal.find('.modal-body input').val(idTruck)
                    modal.find('.modal-body input[name="save"]').val("Save")
                    modal.find('.modal-body input[name="brand"]').val(brand)
                    modal.find('.modal-body input[name="model"]').val(model)
                    modal.find('.modal-body input[name="capacity"]').val(capacity)
                    modal.find('.modal-body input[name="workingSession"]').val(workingSession)
                    modal.find('.modal-body input[name="maxDriver"]').val(maxDrivers)
                    modal.find('.modal-body input[name="licensePlate"]').val(licensePlate)
                })

            </script>
        </main>
    </div>
</div>


</body>
</html>
