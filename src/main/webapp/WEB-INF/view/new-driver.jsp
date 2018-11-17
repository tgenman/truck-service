<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>New Driver</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/sign-in.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script type="text/javascript">
        function validateForm() {

            if (document.formSubmit.firstName.value == "" || document.formSubmit.firstName == null) {
                alert("First name field is empty!");
                return false;

            } else if (document.formSubmit.secondName.value == "" || document.formSubmit.secondName == null) {
                alert("Second name field is empty!");
                return false;
            }
            return true;
        }
    </script>
</head>
<body class="text-center">
<sf:form name="formSubmit" action="/management/driver/new/process" method="post" modelAttribute="driverForm"
         cssClass="form-signin" id="formSubmit" onsubmit="return validateForm()">
    <h1 class="h3 mb-3 font-weight-normal">New drivers</h1>
    <sf:input path="firstName" name="firstName" id="firstName" class="form-control" placeholder="First name"/>
    <sf:errors path="firstName" cssClass="text-danger"/>
    <sf:input path="lastName" name="secondName" id="secondName" class="form-control" placeholder="Last name"/>
    <sf:errors path="firstName" cssClass="text-danger"/>
    <sf:select path="city">
        <sf:options items="${cities}"/>
    </sf:select>
    <sf:errors path="city" cssClass="error"/>


    <br><br>
    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Submit">
</sf:form>
</body>
</html>
