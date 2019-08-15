<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
<%@include file="/WEB-INF/views/fragments/header.jspf" %>

    <div class="slogan container container--90">
        <div class="slogan--item">

            <h1>
                <spring:message code="app.main-page.slogan.main-part"/><br/>
                <span class="uppercase"><spring:message code="app.main-page.slogan.feature"/></span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title"><spring:message code="app.main-page.slogan.steps.title"/></div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span><spring:message code="app.main-page.slogan.steps.1"/></span></div>
                    </li>
                    <li>
                        <div><em>2</em><span><spring:message code="app.main-page.slogan.steps.2"/></span></div>
                    </li>
                    <li>
                        <div><em>3</em><span><spring:message code="app.main-page.slogan.steps.3"/></span></div>
                    </li>
                    <li>
                        <div><em>4</em><span><spring:message code="app.main-page.slogan.steps.4"/></span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3><spring:message code="app.donation-form.instructions.header"/></h3>
            <p data-step="1" class="active"><spring:message code="app.donation-form.instructions.steps.1-2"/></p>
            <p data-step="2"><spring:message code="app.donation-form.instructions.steps.1-2"/></p>
            <p data-step="3"><spring:message code="app.donation-form.instructions.steps.3"/></p>
            <p data-step="4"><spring:message code="app.donation-form.instructions.steps.4"/></p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter"><spring:message code="app.donation-form.steps-header"/> <span>1</span>/4</div>
        <spring:message code="app.donation-form.tooltips.number-input" var="numberInputTooltip"/>
        <spring:message code="app.donation-form.tooltips.city-input" var="cityInputTooltip"/>
        <spring:message code="app.donation-form.tooltips.zipCode-input" var="zipCodeInputTooltip"/>
        <spring:message code="app.donation-form.tooltips.date-input" var="dateInputTooltip"/>
        <spring:message code="app.donation-form.tooltips.time-input" var="timeInputTooltip"/>

        <form:form method="post" modelAttribute="donation">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3><spring:message code="app.donation-form.step-1.header"/></h3>

                <form:errors path="categories" cssClass="alert alert-danger" element="div" role="alert"/>

                <c:forEach var="category" items="${categories}" begin="1">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <form:checkbox path="categories"
                                           value="${category.id}"/>
                            <span class="checkbox"></span>
                            <span class="description">${category.name}</span>
                        </label>
                    </div>

                </c:forEach>

                <div class="form-group form-group--checkbox">
                    <label>
                        <form:checkbox path="categories"
                                       value="${categories[0].id}"/>
                        <span class="checkbox"></span>
                        <span class="description">${categories[0].name}</span>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step"><spring:message code="app.donation-form.steps.next"/></button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3><spring:message code="app.donation-form.step-2.header"/></h3>

                <form:errors path="quantity" cssClass="alert alert-danger" element="div" role="alert"/>
                <div class="form-group form-group--inline">
                    <label>
                        <spring:message code="app.donation-form.step-2.form-information"/>
                        <form:input path="quantity" cssClass="number" data-toggle="tooltip" data-placement="bottom"
                                    title="${numberInputTooltip}"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step"><spring:message code="app.donation-form.steps.back"/></button>
                    <button type="button" class="btn next-step"><spring:message code="app.donation-form.steps.next"/></button>
                </div>
            </div>


            <!-- STEP 3 -->
            <div data-step="3">
                <h3><spring:message code="app.donation-form.step-3.header"/></h3>

                <form:errors path="institution" cssClass="alert alert-danger" element="div" role="alert"/>
                <c:forEach var="institution" items="${institutions}">

                    <div class="form-group form-group--checkbox">
                        <label>
                            <form:radiobutton path="institution" value="${institution.id}"/>
                            <span class="checkbox radio"></span>
                            <span class="description">
                                <div class="title"><spring:message code="app.main-page.institutions.foundation"/> “<span class="institution-name">${institution.name}</span>”</div>
                                <div class="subtitle"><spring:message code="app.main-page.institutions.mission"/> ${institution.description}</div>
                            </span>
                        </label>
                    </div>

                </c:forEach>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step"><spring:message code="app.donation-form.steps.back"/></button>
                    <button type="button" class="btn next-step"><spring:message code="app.donation-form.steps.next"/></button>
                </div>
            </div>

            <!-- STEP 4 -->
            <div data-step="4">
                <h3><spring:message code="app.donation-form.step-4.header"/></h3>

                <div class="wrong alert alert-danger" role="alert">
                    <div class="wrong error-source text-secondary"><spring:message code="app.donation-form.step-4.inputs.street"/>: </div>
                    <form:errors path="street" cssClass="error" cssStyle="display: block"/>
                    <div class="wrong error-source text-secondary"><spring:message code="app.donation-form.step-4.inputs.streetNumber"/>: </div>
                    <form:errors path="streetNum" cssClass="error" cssStyle="display: block"/>
                    <div class="wrong error-source text-secondary"><spring:message code="app.donation-form.step-4.inputs.city"/>: </div>
                    <form:errors path="city" cssClass="error" cssStyle="display: block"/>
                    <div class="wrong error-source text-secondary"><spring:message code="app.donation-form.step-4.inputs.zipCode"/>: </div>
                    <form:errors path="zipCode" cssClass="error" cssStyle="display: block"/>
                    <div class="wrong error-source text-secondary"><spring:message code="app.donation-form.step-4.inputs.phoneNumber"/>: </div>
                    <form:errors path="phoneNumber" cssClass="error" cssStyle="display: block"/>
                </div>
                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> <spring:message code="app.donation-form.step-4.inputs.street"/>
                                <form:input path="street" cssClass="street"/>
                                <form:input path="streetNum" cssClass="number streetNum" data-toggle="tooltip"
                                            data-placement="top" title="${numberInputTooltip}"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> <spring:message code="app.donation-form.step-4.inputs.city"/>
                                <form:input path="city" cssClass="city" data-toggle="tooltip" data-placement="top"
                                            title="${cityInputTooltip}"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> <spring:message code="app.donation-form.step-4.inputs.zipCode"/>
                                <form:input path="zipCode" cssClass="formatted zipCode" data-toggle="tooltip"
                                            data-placement="top" title="${zipCodeInputTooltip}" value="__-___"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> <spring:message code="app.donation-form.step-4.inputs.phoneNumber"/>
                                <form:input path="phoneNumber" cssClass="number phoneNum" data-toggle="tooltip"
                                            data-placement="top" title="${numberInputTooltip}"/>
                            </label>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4><spring:message code="app.donation-form.step-4.inputs.pickUpDate"/></h4>
                        <div class="form-group form-group--inline">
                            <label> Data
                                <form:input path="pickUpDate" cssClass="formatted date" data-toggle="tooltip"
                                            data-placement="top" title="${dateInputTooltip}"
                                            value="YYYY-MM-DD"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> <spring:message code="app.donation-form.step-4.inputs.pickUpTime"/>
                                <form:input path="pickUpTime" cssClass="formatted time" data-toggle="tooltip"
                                            data-placement="top" title="${timeInputTooltip}"
                                            value="HH:MM"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                <spring:message code="app.donation-form.step-4.inputs.pickUpComment"/>
                                <form:textarea path="pickUpComment" cssClass="comment" rows="5"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step"><spring:message code="app.donation-form.steps.back"/></button>
                    <button type="button" class="btn next-step"><spring:message code="app.donation-form.steps.next"/></button>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="5">
                <h3><spring:message code="app.donation-form.step-5.header"/></h3>

                <div class="summary">
                    <div class="form-section">
                        <h4><spring:message code="app.donation-form.step-5.summary"/></h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span class="summary--text"><span
                                        class="bagsQuantity"></span> <spring:message code="app.donation-form.step-5.bags"/></span>
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text"><spring:message code="app.donation-form.step-5.institution"/> "<span class="institution"></span>"</span>
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4><spring:message code="app.donation-form.step-5.address"/></h4>
                            <ul>
                                <li><span class="street"></span> <span class="streetNum"></span></li>
                                <li><span class="city"></span></li>
                                <li><span class="zipCode"></span></li>
                                <li><span class="phoneNum"></span></li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4><spring:message code="app.donation-form.step-4.inputs.pickUpDate"/>:</h4>
                            <ul>
                                <li><span class="date"></span></li>
                                <li><span class="time"></span></li>
                                <li><span class="comment"></span></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step"><spring:message code="app.donation-form.steps.back"/></button>
                    <button type="submit" class="btn"><spring:message code="app.donation-form.steps.confirm"/></button>
                </div>
            </div>
        </form:form>
    </div>
</section>

<%@include file="/WEB-INF/views/fragments/footer.jspf" %>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>


<script src="<c:url value="/resources/js/app.js"/>"></script>

</body>
</html>