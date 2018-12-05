<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/sign-in.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <title>New Truck</title>
    <script type="text/javascript">
        function validateForm() {

            var licenseRegex = new RegExp("^[A-Za-z]{2}[0-9]{5}$");
            var digitsRegex = new RegExp("\\d+");

            if (document.formSubmit.brand.value == "" || document.formSubmit.brand == null) {
                alert("Brand field is empty!");
                return false;

            } else if (document.formSubmit.model.value == "" || document.formSubmit.model == null) {
                alert("Model field is empty!");
                return false;
            } else if (document.formSubmit.license.value == "" || document.formSubmit.license == null) {
                alert("License field is empty!")
                return false;
            } else if (document.formSubmit.license.value.length > 7) {
                alert("License plate should have 2 letters and 5 numbers!")
                return false;
            } else if (!licenseRegex.test(document.formSubmit.license.value)) {
                alert("License plate should have 2 letters and 5 numbers!")
                return false;
            } else if (document.formSubmit.workingSession.value == "" || document.formSubmit.workingSession == null) {
                alert("Truck shift field is empty!");
                return false;
            } else if (document.formSubmit.capacity.value == "" || document.formSubmit.capacity == null) {
                alert("Capacity field is empty!");
                return false;
            } else if (document.formSubmit.maxDrivers.value == "" || document.formSubmit.maxDrivers == null) {
                alert("Max amount of drivers field is empty!");
                return false;
            } else if (!digitsRegex.test(document.formSubmit.workingSession.value)) {
                alert("Truck shift field consists of digits!");
                return false;
            } else if (!digitsRegex.test(document.formSubmit.capacity.value)) {
                alert("Capacity field consists of digits!");
                return false;
            } else if (!digitsRegex.test(document.formSubmit.maxDrivers.value)) {
                alert("Drivers amount field consists of digits!");
                return false;
            }


            return true;
        }
    </script>
</head>
<body>
<sf:form name="formSubmit" action="${pageContext.request.contextPath}/management/truck/new/process" method="post"
         modelAttribute="truckForm" cssClass="form-signin" onsubmit="return validateForm()">
    <h1 class="h3 mb-3 font-weight-normal">New truck</h1>
    Brand: <sf:input id="brand" name="brand" path="brand" class="form-control" placeholder="Brand"/>
    <sf:errors path="brand" cssClass="text-danger"/>
    Model: <sf:input id="model" name="model" path="model" class="form-control" placeholder="Model"/>
    <sf:errors path="model" cssClass="text-danger"/>

    License plate: <sf:input id="license" name="license" path="licensePlate" class="form-control"
                             placeholder="2 letters and 5 digits"/>
    <sf:errors path="licensePlate" cssClass="text-danger"/>

    Truck shift:<sf:input id="workingSession" name="workingSession" path="workingSession" class="form-control"
                          placeholder="Max working session"/>
    <sf:errors path="workingSession" cssClass="text-danger"/>

    Capacity: <sf:input id="capacity" name="capacity" path="capacity" class="form-control"
                        placeholder="Capacity (tons)"/>
    <sf:errors path="capacity" cssClass="text-danger"/>

    Max drivers: <sf:input id="maxDrivers" name="maxDrivers" path="maxDrivers" class="form-control"
                           placeholder="Max drivers"/>
    <sf:errors path="maxDrivers" cssClass="text-danger"/>

    <br>
    <h5>Current location</h5>
    <sf:select path="city" cssClass="form-control">
        <sf:options items="${cities}"/>
    </sf:select>
    <sf:errors path="city" cssClass="text-danger"/>

    <br>
    <h5>Operable status</h5>
    <sf:select path="status" cssClass="form-control">
        <sf:options items="${truckStatus}"/>
    </sf:select>
    <sf:errors path="status" cssClass="text-danger"/>

    <br><br>
    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Submit">
</sf:form>
</body>
</html>
