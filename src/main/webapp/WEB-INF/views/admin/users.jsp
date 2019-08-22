<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="pl">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><spring:message code="app.admin.title.users"/></title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/resources/admin/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet"
          type="text/css">
    <link href="<c:url value="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"/>"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/resources/admin/css/sb-admin-2.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/admin/css/my-style.css"/>" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="<c:url value="/resources/admin/vendor/datatables/dataTables.bootstrap4.min.css"/>" rel="stylesheet">

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

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary text-lg"><spring:message
                                code="app.admin.users.header"/></h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th><spring:message code="app.admin.table.id"/></th>
                                    <th><spring:message code="app.admin.users.username"/></th>
                                    <th><spring:message code="app.admin.users.email"/></th>
                                    <th><spring:message code="app.admin.users.enabled"/></th>
                                    <th><spring:message code="app.admin.table.actions"/></th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th><spring:message code="app.admin.table.id"/></th>
                                    <th><spring:message code="app.admin.users.username"/></th>
                                    <th><spring:message code="app.admin.users.email"/></th>
                                    <th><spring:message code="app.admin.users.enabled"/></th>
                                    <th><spring:message code="app.admin.table.actions"/></th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach var="user" items="${users}">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.username}</td>
                                        <td>${user.email}</td>
                                        <td class="text-center">
                                            <c:choose>
                                                <c:when test="${user.enabled == 1}">
                                                    <div class="btn btn-success btn-sm btn-circle">
                                                        <i class="fas fa-user-check"></i>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="btn btn-danger btn-sm btn-circle">
                                                        <i class="fas fa-user-slash"></i>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="text-center">
                                            <a href="/admin/user/form?id=${user.id}" class="btn btn-info btn-circle">
                                                <i class="fas fa-user-edit"></i>
                                            </a>
                                            <a href="/admin/user/rmv?id=${user.id}" class="btn btn-danger btn-circle"
                                               data-toggle="modal"
                                               data-target="#deleteModal"
                                               data-item-id="${user.id}"
                                               data-item-type="user"
                                               data-item-name="${user.username}"
                                               title="Remove user">

                                                <i class="fas fa-user-minus"></i>

                                            </a>
                                            <!-- TODO: give or take admin rights in controller -->
                                            <c:choose>
                                                <c:when test="${role == 'adm'}">
                                                    <a href="#" class="btn btn-outline-primary btn-circle">
                                                        <i class="fas fa-user-times"></i>
                                                    </a>
                                                </c:when>
                                                <c:when test="${role == 'ord'}">
                                                    <a href="#" class="btn btn-outline-primary btn-circle">
                                                        <i class="fas fa-user-tie"></i>
                                                    </a>
                                                </c:when>
                                            </c:choose>

                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
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
<%@include file="/WEB-INF/views/admin/fragments/admin-logout-modal.jspf"%>

<!-- Delete Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Removing confirmation</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to remove user <span id="itemName"></span>?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button id="deleteId" type="button" class="btn btn-danger">Remove</button>
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

<!-- Page level plugins -->
<script src="<c:url value="/resources/admin/vendor/datatables/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="/resources/admin/vendor/datatables/dataTables.bootstrap4.min.js"/>"></script>

<!-- Page level custom scripts -->
<script src="<c:url value="/resources/admin/js/demo/datatables-demo.js"/>"></script>

</body>

</html>