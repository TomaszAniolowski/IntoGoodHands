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

    <title><spring:message code="app.admin.title.user-form"/></title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/resources/admin/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet"
          type="text/css">
    <link href="<c:url value="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"/>"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/resources/admin/css/sb-admin-2.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/admin/css/my-style.css"/>" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <%@include file="/WEB-INF/views/admin/fragments/admin-sidebar.jspf" %>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <%@include file="/WEB-INF/views/admin/fragments/admin-topbar.jspf" %>


            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4"
                    <h1 class="h3 mb-0 text-gray-800">Edytuj użytkownika</h1>
<%--                    <h1 class="h3 mb-0 text-gray-800"><spring:message code="app.admin.sidebar.main.into-good-hands"/></h1>--%>
                </div>

                <!-- Content Row -->
                <div class="row second-row justify-content-center">

                    <div class="card shadow mb-4 entity-form">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Użytkownik</h6>
                        </div>
                        <div class="card-body">
                            <form:form method="post" modelAttribute="user">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">Username</span>
                                    </div>
                                    <form:input path="username" class="form-control" id="basic-url" aria-describedby="basic-addon3"/>
                                    <form:errors path="username" cssClass="alert alert-danger" element="div"/>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">Email</span>
                                    </div>
                                    <form:input path="email" class="form-control" id="basic-url" aria-describedby="basic-addon3"/>
                                    <form:errors path="email" cssClass="alert alert-danger" element="div"/>
                                </div>
                                <div class="input-group mb-2">
                                <button type="submit" class="btn btn-warning btn-lg btn-block"><spring:message code="app.admin.form.save"/></button>
                                </div>
                            </form:form>
                            <div class="text-sm-center">
                                <span class="back-button text-primary"><spring:message code="app.admin.form.back"/></span>
                            </div>
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

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
        <%@include file="/WEB-INF/views/admin/fragments/admin-logout-modal.jspf"%>

    <!-- Bootstrap core JavaScript-->
    <script src="<c:url value="/resources/admin/vendor/jquery/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

    <!-- Core plugin JavaScript-->
    <script src="<c:url value="/resources/admin/vendor/jquery-easing/jquery.easing.min.js"/>"></script>

    <!-- Custom scripts for all pages-->
    <script src="<c:url value="/resources/admin/js/sb-admin-2.js"/>"></script>

    <!-- Page level plugins -->
    <script src="<c:url value="/resources/admin/vendor/chart.js/Chart.min.js"/>"></script>

    <!-- Page level custom scripts -->
    <script src="<c:url value="/resources/admin/js/demo/chart-area-demo.js"/>"></script>
    <script src="<c:url value="/resources/admin/js/demo/chart-pie-demo.js"/>"></script>

</body>

</html>
