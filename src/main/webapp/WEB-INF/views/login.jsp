<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title><spring:message code="app.title"/></title>

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <%@include file="/WEB-INF/views/fragments/header.jspf" %>
</header>
<section class="login-page">
    <h2><spring:message code="app.login-form.header"/></h2>
    <form method="post">
        <div class="form-group">
            <input type="text" name="username" placeholder="<spring:message code="app.form.username-input"/>"/>
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="<spring:message code="app.form.password-input"/>"/>
            <a href="#" class="btn btn--small btn--without-border reset-password"><spring:message code="app.login-form.forgot-the-password"/></a>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/register" class="btn btn--without-border"><spring:message code="app.header.anonymous.create-account"/></a>
            <button class="btn" type="submit"><spring:message code="app.login-form.header"/></button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </div>
    </form>
</section>

<%@include file="/WEB-INF/views/fragments/footer.jspf" %>

</body>
</html>