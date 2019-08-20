<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- TODO: messages.properties -->
    <title>SB Admin 2 - Login</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/resources/admin/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet"
          type="text/css">
    <link href="<c:url value="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"/>"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/resources/admin/css/sb-admin-2.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/admin/css/my-style.css"/>" rel="stylesheet">

</head>

<body class="bg-gray-700">

<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row m-height-600">
                        <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                        <div class="col-lg-6 bg-gray-500">
                            <div class="p-5">
                                <div class="text-center">

                                    <!-- TODO: messages.properties -->

                                    <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                </div>
                                <form:form class="user" action="/lAsA" method="post">
                                    <div class="form-group">
                                        <input type="text" name="username"
                                               placeholder="<spring:message code="app.form.username-input"/>"
                                               class="form-control form-control-user"/>
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="password"
                                               placeholder="<spring:message code="app.form.password-input"/>"
                                               class="form-control form-control-user"/>
                                    </div>
                                    <button class="btn btn-primary btn-user btn-block" type="submit">
                                        <spring:message code="app.login-form.header"/></button>
                                </form:form>
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="forgot-password.html"><spring:message
                                            code="app.login-form.forgot-the-password"/></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>

<!-- Bootstrap core JavaScript-->
<script src="<c:url value="/resources/admin/vendor/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

<!-- Core plugin JavaScript-->
<script src="<c:url value="/resources/admin/vendor/jquery-easing/jquery.easing.min.js"/>"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value="/resources/admin/js/sb-admin-2.js"/>"></script>

</body>

</html>