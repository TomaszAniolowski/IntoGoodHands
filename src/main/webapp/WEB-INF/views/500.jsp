<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><spring:message code="app.error.title"/> <spring:message code="app.error.500.header"/></title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/resources/admin/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet"
          type="text/css">
    <link href="<c:url value="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"/>"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/resources/admin/css/sb-admin-2.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/admin/css/my-style.css"/>" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Begin Page Content -->
            <div class="container-fluid four-zero-four">

                <!-- 500 Error Text -->
                <div class="text-center">
                    <div class="error mx-auto" data-text="<spring:message code="app.error.500.header"/>"><spring:message code="app.error.500.header"/></div>
                    <p class="lead text-gray-800 mb-5"><spring:message code="app.error.500.subtitle"/></p>
                    <sec:authorize access="hasRole('ADMIN')">
                        <spring:message code="app.error.500.default-message" var="defaultMessage"/>
                        <p class="text-gray-500 mb-0"><c:out value="${actualErrorMessage}" default="${defaultMessage}"/></p>
                    </sec:authorize>
                    <sec:authorize access="hasRole('USER') || isAnonymous()">
                        <p class="text-gray-500 mb-0">${defaultMessage}</p>
                    </sec:authorize>

                    <a href="/">&larr; <spring:message code="app.error.back"/></a>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <%@include file="/WEB-INF/views/admin/fragments/admin-footer.jspf" %>

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Bootstrap core JavaScript-->
<script src="<c:url value="/resources/admin/vendor/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

<!-- Core plugin JavaScript-->
<script src="<c:url value="/resources/admin/vendor/jquery-easing/jquery.easing.min.js"/>"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value="/resources/admin/js/sb-admin-2.js"/>"></script>

</body>

</html>