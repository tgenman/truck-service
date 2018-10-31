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

                <h6>Customer: ${orderDTO.customer}</h6>
                <br>
                <h6>Add a new location for cargo picking up</h6>
                <button type="button" data-toggle="modal"
                        name="newPoint"
                        data-target="#newPoint" class="btn btn-primary">
                    Add new route point
                </button>
                <br>
                <h6>If the order was formed, click "Verify" for setting up drop off point
                    of the route, start route optimization and checking
                    order final information</h6>
            <button type="submit" data-toggle="modal" data-target="#dropOffPoint"
                    class="btn btn-lg btn-primary btn-block">
                Finish the Route
            </button>
        </div>
        <div class="col-8">
            <h4>Route cities</h4>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">№</th>
                    <th scope="col">City</th>
                    <th scope="col">Cargo name</th>
                    <th scope="col">Cargo weight</th>
                    <th scope="col">Type</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${routeDTO.routePoints}" var="point">
                    <tr>
                        <th scope="row" class="counterCell"></th>
                        <td>${point.cityId}</td>
                        <td>${point.cargoDTO.name}</td>
                        <td>${point.cargoDTO.weight}</td>
                        <td>${point.type.name()}</td>
                        <td>
                            <sf:form method="post" action="delete-truck">
                                <input type="hidden" value="${point.id}" name="truckIdDelete">
                                <input type="submit" class="btn btn-danger" value="Delete"/>
                            </sf:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


    <div class="modal fade" id="newPoint" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">New route point</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <sf:form action="order-main" method="post" modelAttribute="route-point">
                        <h6>Pick up cargo in:</h6>
                        <sf:select path="cityId" cssClass="form-control">
                            <sf:options items="${cities}"/>
                        </sf:select>
                        <br>
                        <h6>Cargo name, product name or smth else</h6>
                        <sf:input path="cargoDTO.name" id="cargoName" class="form-control" placeholder="Name"
                                  required=""/>
                        <h6>It should have any weight, man</h6>
                        <sf:input path="cargoDTO.weight" id="cargoWeight" class="form-control" placeholder="Weight"
                                  required=""/>
                        <input type="submit" value="Submit">
                    </sf:form>

                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="dropOffPoint" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">New route point</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <sf:form action="orderTruck" method="post" modelAttribute="route-point">
                    <h6>Drop off cargoes in:</h6>
                    <sf:select path="cityId" cssClass="form-control">
                        <sf:options items="${cities}"/>
                    </sf:select>
                    <br>
                    <input type="submit" value="Select a Truck">
                </sf:form>
            </div>
        </div>
    </div>
</div>
</div>

<script>
    $('#newPoint').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var modal = $(this)

    })
    $('#dropOffPoint').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var modal = $(this)

    })
</script>
</body>
</html>
