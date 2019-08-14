<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title><spring:message code="app.title"/></title>

    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <%@include file="/WEB-INF/views/fragments/header.jspf" %>

    <div class="slogan container container--90">
        <sec:authorize access="hasAnyRole('USER', 'ADMIN')">
            <a href="/donations/form" class="btn btn--large"><spring:message code="app.main-page.send-the-package"/></a>
        </sec:authorize>
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


<section class="stats">
    <div class="container container--85">
        <div class="stats--item">
            <em><c:out value="${bagsQuantity}" default="<ERROR: no data>"/></em>

            <h3><spring:message code="app.main-page.stats.bags-title"/></h3>
            <p><spring:message code="app.main-page.stats.bags-quote"/></p>
        </div>

        <div class="stats--item">
            <em><c:out value="${institutionsQuantity}" default="<ERROR: no data>"/></em>
            <h3><spring:message code="app.main-page.stats.institutions-title"/></h3>
            <p><spring:message code="app.main-page.stats.institutions-quote"/></p>
        </div>

    </div>
</section>

<section class="steps" id="steps">
    <h2><spring:message code="app.main-page.slogan.steps.title"/></h2>

    <div class="steps--container">
        <div class="steps--item">
            <span class="icon icon--hands"></span>
            <h3><spring:message code="app.main-page.slogan.steps.1"/></h3>
            <p><spring:message code="app.main-page.slogan.steps.1.feature"/></p>
        </div>
        <div class="steps--item">
            <span class="icon icon--arrow"></span>
            <h3><spring:message code="app.main-page.slogan.steps.2"/></h3>
            <p><spring:message code="app.main-page.slogan.steps.2.feature"/></p>
        </div>
        <div class="steps--item">
            <span class="icon icon--glasses"></span>
            <h3><spring:message code="app.main-page.slogan.steps.3"/></h3>
            <p><spring:message code="app.main-page.slogan.steps.3.feature"/></p>
        </div>
        <div class="steps--item">
            <span class="icon icon--courier"></span>
            <h3><spring:message code="app.main-page.slogan.steps.4"/></h3>
            <p><spring:message code="app.main-page.slogan.steps.4.feature"/></p>
        </div>
    </div>
    <sec:authorize access="isAnonymous()">
        <a href="/register" class="btn btn--large"><spring:message code="app.header.anonymous.create-account"/></a>
    </sec:authorize>
    <sec:authorize access="hasAnyRole('USER', 'ADMIN')">
        <a href="/donations/form" class="btn btn--large"><spring:message code="app.main-page.send-the-package"/></a>
    </sec:authorize>
</section>

<section class="about-us" id="about-us">
    <div class="about-us--text">
        <h2><spring:message code="app.header.navbar.about-us"/></h2>
        <p><spring:message code="app.main-page.about-us.quote"/></p>
        <img src="<c:url value="resources/images/signature.svg"/>" class="about-us--text-signature" alt="Signature"/>
    </div>
    <div class="about-us--image"><img src="<c:url value="resources/images/about-us.jpg"/>" alt="People in circle"/>
    </div>
</section>

<c:if test="${not empty institutions}">

    <section class="help" id="help">
        <h2><spring:message code="app.main-page.institutions.header"/></h2>

        <div class="help--slides active" data-id="1">
            <p><spring:message code="app.main-page.institutions.feature"/></p>

            <ul class="help--slides-items">
                <c:choose>
                    <c:when test="${fn:length(institutions) % 2 == 0}">

                        <c:forEach begin="0" end="${fn:length(institutions)/2}" step="2" varStatus="var">
                            <li>
                                <div class="col">
                                    <div class="title"><spring:message code="app.main-page.institutions.foundation"/> "${institutions[var.index].name}"</div>
                                    <div class="subtitle"><spring:message code="app.main-page.institutions.mission"/> ${institutions[var.index].description}.</div>
                                </div>
                                <div class="col">
                                    <div class="title"><spring:message code="app.main-page.institutions.foundation"/> "${institutions[var.index+1].name}"</div>
                                    <div class="subtitle"><spring:message code="app.main-page.institutions.mission"/> ${institutions[var.index+1].description}.</div>
                                </div>
                            </li>
                        </c:forEach>

                    </c:when>
                    <c:otherwise>

                        <c:forEach begin="0" end="${fn:length(institutions)/2 + 2}" step="2" varStatus="var">
                            <li>
                                <c:choose>
                                    <c:when test="${!var.last}">
                                        <div class="col">
                                            <div class="title"><spring:message code="app.main-page.institutions.foundation"/> "${institutions[var.index].name}"</div>
                                            <div class="subtitle"><spring:message code="app.main-page.institutions.mission"/> ${institutions[var.index].description}.
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="title"><spring:message code="app.main-page.institutions.foundation"/> "${institutions[var.index+1].name}"</div>
                                            <div class="subtitle"><spring:message code="app.main-page.institutions.mission"/> ${institutions[var.index+1].description}.
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col last-of-inst">
                                            <div class="title"><spring:message code="app.main-page.institutions.foundation"/> "${institutions[var.index].name}"</div>
                                            <div class="subtitle"><spring:message code="app.main-page.institutions.mission"/> ${institutions[var.index].description}.
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>

                            </li>
                        </c:forEach>

                    </c:otherwise>
                </c:choose>

            </ul>
        </div>

    </section>

</c:if>


<%@include file="/WEB-INF/views/fragments/footer.jspf" %>

<script src="<c:url value="resources/js/app.js"/>"></script>
</body>
</html>