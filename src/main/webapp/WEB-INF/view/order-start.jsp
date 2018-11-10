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
            <h6>Where should a cargo be picked up?</h6>
            <button type="button" data-toggle="modal"
                    name="newPoint"
                    data-target="#newPoint" class="btn btn-primary">
                Add Cargo
            </button>

            <br>
            <br>
            <br>
            <sf:form action="abort-order" method="post">
                <input type="submit" value="Abort Order" class="btn btn-danger"/>
            </sf:form>

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
                        <h6>Customer name</h6>
                        <sf:input path="customerName"/>
                        <br>
                        <h6>Pick up cargo in:</h6>
                        <sf:select path="cityId" cssClass="form-control">
                            <sf:options items="${cities}"/>
                        </sf:select>
                        <br>
                        <h6>Cargo name</h6>
                        <sf:input path="cargoDTO.name" id="cargoName" class="form-control" placeholder="Name"
                                  required=""/>
                        <sf:hidden path="cargoDTO.dropLocationSelected" value="false"/>
                        <h6>It should have any weight, man</h6>
                        <sf:input path="cargoDTO.weight" id="cargoWeight" class="form-control" placeholder="Weight"
                                  required=""/>
                        <input type="submit" value="Submit">
                    </sf:form>


                </div>
            </div>
        </div>
    </div>
    <script>
        $('#newPoint').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget) // Button that triggered the modal
            var modal = $(this)
        })
    </script>
</div>


</body>
</html>
