<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fomr" uri="http://www.springframework.org/tags/form" %>
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
    <h2>Załóż konto</h2>
    <form:form action="/create-user" method="post" modelAttribute="user">
        <form:errors path="id" cssClass="alert alert-danger" element="div" role="alert"/>
        <div class="form-group">
            <form:input path="username" placeholder="Nazwa użytkownika"/>
            <form:errors path="username" cssClass="alert alert-danger" element="div" role="alert"/>
        </div>
        <div class="form-group">
            <form:input path="email" placeholder="Email"/>
            <form:errors path="email" cssClass="alert alert-danger" element="div" role="alert"/>
        </div>
        <div class="form-group">
            <fomr:password path="password" placeholder="Hasło"/>
            <form:errors path="password" cssClass="alert alert-danger" element="div" role="alert"/>
        </div>
        <%--        <div class="form-group">--%>
        <%--            <input type="password" name="confirmPassword" placeholder="Powtórz hasło"/>--%>
        <%--        </div>--%>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<%@include file="/WEB-INF/views/fragments/footer.jspf" %>

</body>
</html>