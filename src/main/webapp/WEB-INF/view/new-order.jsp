<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div id="order-column" class="col">
            <sf:form action="save-order" method="post" modelAttribute="customerNewOrder">
                <sf:input path="name" value="${customerNewOrder.name}"/>
            </sf:form>

        </div>
        <div id="cargo-column" class="col">
            <button type="button" class="btn btn-warning"
                    data-toggle="modal"
                    name="editableDriver"
                    data-target="#editableDriver"
                    data-id-driver="${driver.id}"
                    data-first-name-driver="${driver.firstName}"
                    data-last-name-driver="${driver.lastName}"
                    data-worked-time-driver="${driver.workedTime}">
                Add cargo
            </button>
        </div>
    </div>
</div>

</body>
</html>
