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
                Oddaj rzeczy, których już nie chcesz<br/>
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form:form method="post" modelAttribute="donation">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3>Zaznacz co chcesz oddać:</h3>

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
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <form:errors path="quantity" cssClass="alert alert-danger" element="div" role="alert"/>
                <div class="form-group form-group--inline">
                    <label>
                        Liczba 60l worków:
                        <form:input path="quantity" cssClass="number" data-toggle="tooltip" data-placement="bottom"
                                    title="Wprowadź liczbę"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>


            <!-- STEP 3 -->
            <div data-step="3">
                <h3>Wybierz organizacje, której chcesz pomóc:</h3>

                <form:errors path="institution" cssClass="alert alert-danger" element="div" role="alert"/>
                <c:forEach var="institution" items="${institutions}">

                    <div class="form-group form-group--checkbox">
                        <label>
                            <form:radiobutton path="institution" value="${institution.id}"/>
                            <span class="checkbox radio"></span>
                            <span class="description">
                                <div class="title">Fundacja “<span class="institution-name">${institution.name}</span>”</div>
                                <div class="subtitle">Cel i misja: ${institution.description}</div>
                            </span>
                        </label>
                    </div>

                </c:forEach>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 4 -->
            <div data-step="4">
                <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                <div class="wrong alert alert-danger" role="alert">
                    <div class="wrong error-source text-secondary">Ulica: </div>
                    <form:errors path="street" cssClass="error" cssStyle="display: block"/>
                    <div class="wrong error-source text-secondary">Numer domu/mieszkania: </div>
                    <form:errors path="streetNum" cssClass="error" cssStyle="display: block"/>
                    <div class="wrong error-source text-secondary">Miasto: </div>
                    <form:errors path="city" cssClass="error" cssStyle="display: block"/>
                    <div class="wrong error-source text-secondary">Kod pocztowy: </div>
                    <form:errors path="zipCode" cssClass="error" cssStyle="display: block"/>
                    <div class="wrong error-source text-secondary">Nr telefonu: </div>
                    <form:errors path="phoneNumber" cssClass="error" cssStyle="display: block"/>
                </div>
                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Ulica
                                <form:input path="street" cssClass="street"/>
                                <form:input path="streetNum" cssClass="number streetNum" data-toggle="tooltip"
                                            data-placement="top" title="Wprowadź liczbę"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Miasto
                                <form:input path="city" cssClass="city" data-toggle="tooltip" data-placement="top"
                                            title="Wprowadź poprawną nazwę miasta"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Kod pocztowy
                                <form:input path="zipCode" cssClass="formatted zipCode" data-toggle="tooltip"
                                            data-placement="top" title="Wprowadź kod pocztowy" value="__-___"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Nr telefonu
                                <form:input path="phoneNumber" cssClass="number phoneNum" data-toggle="tooltip"
                                            data-placement="top" title="Wprowadź liczbę"/>
                            </label>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Data
                                <form:input path="pickUpDate" cssClass="formatted date" data-toggle="tooltip"
                                            data-placement="top" title="Wprowadź datę w formacie YYYY-MM-DD"
                                            value="YYYY-MM-DD"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Godzina
                                <form:input path="pickUpTime" cssClass="formatted time" data-toggle="tooltip"
                                            data-placement="top" title="Wprowadź godzinę w formacie HH:MM"
                                            value="HH:MM"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Uwagi dla kuriera
                                <form:textarea path="pickUpComment" cssClass="comment" rows="5"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="5">
                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Oddajesz:</h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span class="summary--text"><span
                                        class="bagsQuantity"></span> ubrań w dobrym stanie</span>
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text">Dla fundacji "<span class="institution"></span>" w Warszawie</span>
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru:</h4>
                            <ul>
                                <li><span class="street"></span> <span class="streetNum"></span></li>
                                <li><span class="city"></span></li>
                                <li><span class="zipCode"></span></li>
                                <li><span class="phoneNum"></span></li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru:</h4>
                            <ul>
                                <li><span class="date"></span></li>
                                <li><span class="time"></span></li>
                                <li><span class="comment"></span></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="submit" class="btn">Potwierdzam</button>
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