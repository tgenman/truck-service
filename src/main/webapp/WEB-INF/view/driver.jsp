<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: qarnd
  Date: 11/7/18
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/static/css/driver.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"/>
    <title></title>
</head>
<body>
<div class="container mt-2">
    <div class="row">
        <div class="col-5">
            <br>
            <br>
            <br>
            <h2>Hello, ${driver.firstName} ${driver.lastName} !</h2>
            <h5>Your ID is: ${driver.id}</h5>
            <c:forEach items="${driver.order.drivers}" var="partner">
                <c:if test="${partner.id != driver.id}">
                    <h5>Partner: ${partner.firstName} ${partner.lastName} , ID: ${partner.id}</h5>
                </c:if>
            </c:forEach>
            <br>
            <h5>Truck: ${driver.truck.licensePlate}</h5>
            <br>
            <c:choose>
                <c:when test="${driver.order != null}">
                    <h5>Order ID: ${driver.order.id}</h5>
                    <sf:form method="post" action="finish-order">
                        <input type="hidden" value="${driver.id}" name="driverId">
                        <input type="submit" class="btn btn-success" value="Finish Order">
                    </sf:form>
                </c:when>
                <c:otherwise>
                    <h5>You have no orders</h5>
                </c:otherwise>
            </c:choose>
            <br>
            <sf:form method="post" action="driver-status-update" modelAttribute="driver">
                <sf:select path="status">
                    <c:forEach items="${driverStatus}" var="status">
                        <sf:option value="${status}">${status.toString()}</sf:option>
                    </c:forEach>
                </sf:select>
                <input type="hidden" value="${driver.id}" name="driverId">
                <input type="submit" class="btn btn-primary" value="Update">
            </sf:form>
            <br>

            <c:choose>
                <c:when test="${driver.order != null}">
                    <h5>Route Points:</h5>
                    <div class="list-group">
                        <c:forEach items="${routePoints}" var="point">
                            <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">City: ${point.city}</h5>
                                    <small>Type: ${point.type}</small>
                                </div>
                                <c:if test="${point.cargo != null}">
                                    <p class="mb-1">Cargo ID: ${point.cargo.id}</p>
                                    <p class="mb-1">Cargo name: ${point.cargo.name}</p>
                                    <p class="mb-1">Cargo weight: ${point.cargo.weight}</p>
                                    <p class="mb-1">Cargo status: ${point.cargo.status}</p>
                                    <c:choose>


                                        <c:when test="${point.completed != true}">
                                            <sf:form method="post" action="route-point-update"
                                                     modelAttribute="routePointDTO">
                                                <input type="hidden" value="${driver.id}" name="driverId">
                                                <input type="hidden" value="${point.id}" name="pointId">
                                                <input type="submit" class="btn btn-success" value="Complete">
                                            </sf:form>
                                        </c:when>
                                        <c:otherwise>
                                            <h5 style="color: #0f9d58">COMPLETED</h5>
                                        </c:otherwise>

                                    </c:choose>
                                </c:if>
                                <c:if test="${point.cargoesForDrop.size() != 0}">
                                    <c:forEach items="${point.cargoesForDrop}" var="cargoDrop">
                                        <p class="mb-1">Cargo ID: ${cargoDrop.id}</p>
                                        <p class="mb-1">Cargo name: ${cargoDrop.name}</p>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${point.completed != true}">
                                            <sf:form method="post" action="route-point-update"
                                                     modelAttribute="routePointDTO">
                                                <input type="hidden" value="${driver.id}" name="driverId">
                                                <input type="hidden" value="${point.id}" name="pointId">
                                                <input type="submit" class="btn btn-success" value="Complete">
                                            </sf:form>
                                        </c:when>
                                        <c:otherwise>
                                            <h5 style="color: #0f9d58">COMPLETED</h5>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </a>
                        </c:forEach>
                    </div>
                    <br>
                </c:when>
                <c:otherwise>
                    <h5>No route points.</h5>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</div>
</body>
</html>
