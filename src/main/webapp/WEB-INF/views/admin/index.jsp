<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title><spring:message code="app.admin.title"/></title>

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
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800"><spring:message
                            code="app.admin.sidebar.main.into-good-hands"/></h1>
                </div>

                <!-- Content Row -->
                <div class="row first-row">

                    <!-- Donations of this month Card -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            <spring:message
                                                    code="app.admin.content-row-first.this-month-donations-quantity"/>
                                        </div>
                                        <div class="row no-gutters align-items-center">
                                            <div class="col-auto">
                                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${monthDonationsQuantity}</div>
                                            </div>
                                            <div class="col">
                                                <div class="progress progress-sm mr-2 h-auto">
                                                    <div class="progress-bar bg-gradient-primary" role="progressbar"
                                                         aria-valuenow="${monthDonationsQuantity}" aria-valuemin="0"
                                                         aria-valuemax="${allDonationsQuantity}"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-calendar-check fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- All donations Card -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                            <spring:message code="app.admin.content-row-first.all-donations-quantity"/>
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">${allDonationsQuantity}</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-calendar-alt fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- All Bags Card -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                            <spring:message code="app.admin.content-row-first.all-bags-quantity"/>
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">${allBagsQuantity}</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-shopping-bag fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- All institutions Card -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-warning shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                            <spring:message
                                                    code="app.admin.content-row-first.all-institutions-quantity"/>
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">${allInstitutionsQuantity}</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-handshake fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- Content Row -->
                <div class="row second-row">

                    <!-- Content Column -->
                    <div class="col-lg-6 mb-4">

                        <!-- Categories Card -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary"><spring:message
                                        code="app.admin.content-row-second.categories-donations-header"/></h6>
                            </div>
                            <div class="card-body">
                                <c:forEach var="category" items="${categories}" begin="1">
                                    <h4 class="small font-weight-bold">${category.name} <span
                                            class="float-right"><c:out
                                            value="${categoriesAndDonationsMap[category.name]}"/></span>
                                    </h4>
                                    <div class="progress mb-4 bg-gradient-danger">
                                        <div class="progress-bar" role="progressbar"
                                             aria-valuenow="${categoriesAndDonationsMap[category.name]}"
                                             aria-valuemin="0" aria-valuemax="${allDonationsQuantity}"></div>
                                    </div>
                                </c:forEach>
                                <h4 class="small font-weight-bold">${categories[0].name} <span
                                        class="float-right"><c:out
                                        value="${categoriesAndDonationsMap[categories[0].name]}"/></span>
                                </h4>
                                <div class="progress mb-4 bg-gradient-danger">
                                    <div class="progress-bar" role="progressbar"
                                         aria-valuenow="${categoriesAndDonationsMap[categories[0].name]}"
                                         aria-valuemin="0"
                                         aria-valuemax="${allDonationsQuantity}"></div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <!-- Illustration -->
                    <div class="col-lg-6 mb-4">

                        <div class="card shadow mb-4 illustration">
                            <div class="card-body">
                                <div class="text-center">
                                    <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;"
                                         src="<c:url value="/resources/admin/img/undraw_all_the_data_h4ki.svg"/>"
                                         alt="">
                                </div>
                                <div class="small text-right">
                                    Illustration from <a target="_blank"
                                                         rel="nofollow"
                                                         href="https://undraw.co/">unDraw</a>,
                                </div>
                            </div>
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
    <%@include file="/WEB-INF/views/admin/fragments/admin-logout-modal.jspf" %>


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
