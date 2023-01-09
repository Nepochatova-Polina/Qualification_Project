<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources"/>

<html lang="${sessionScope.locale}">
<head>
    <style>
        <%@include file="/WEB-INF/Styles/catalog.css" %>
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>JSP - Hello World</title>
</head>
<body>
<div class="navbar row">
    <div class="navElements col-sm-6">
        <a>
            <form id="account" class="navbar-form" method="get" action="${pageContext.request.contextPath}/controller">
                <input type="submit" class="btn col" value="<fmt:message key="button.profile"/>">
                <input type="hidden" name="command" value="Profile"/>
            </form>
        </a>
        <a class="active" href="index.jsp"><fmt:message key="navbar.home"/></a>
        <a>
            <form id="catalog" class="navbar-form" method="get" action="${pageContext.request.contextPath}/controller">
                <input type="submit" class="btn col" value="<fmt:message key="button.cruises.catalog"/>">
                <input type="hidden" name="command" value="Catalog"/>
            </form>
        </a>
        <a href="#about">About</a>
    </div>
    <div class="navElements col-sm-6">
        <div id="locale-changer" class="form-control">
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/catalog.jsp">
                <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale"
                       value="ua">
            </form>
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/catalog.jsp">
                <input class="btn" style="background: lightgray; width: 50px;" type="submit" name="locale"
                       value="en">
            </form>
        </div>
    </div>
</div>
<form  method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="FilterCruises"/>>
    <div class="sortbar row">
        <div class="inpt startInput">
            <label for="start"><fmt:message key="cruise.label.leaving.date"/></label>
            <input type="date"
                   id="start"
                   name="start-date"
                   value="0">
        </div>
        <div class="inpt endInput">
            <label for="end"><fmt:message key="cruise.label.arriving.date"/></label>
            <input type="date" id="end" name="end-date" value="0">
        </div>
        <div class="inpt durInput">
            <label for="duration"><fmt:message key="cruise.label.duration"/></label>
            <input type="number" value="0" id="duration" name="duration" placeholder="<fmt:message key="cruise.label.duration"/>">
        </div>
        <input type="submit" class="submit-btn" value="<fmt:message key="button.submit"/>">
    </div>
</form>
<h1 class="text-center"><fmt:message key="button.cruises.catalog"/></h1>
<div class="card-group">
    <c:forEach var="cruise" items="${sessionScope.cruises}">
        <div class="card">
            <div class="card-header">
                <h4><c:out value="${cruise.name}"/></h4>
            </div>
            <form class="card-body text-center" method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="DisplayOrderForm"/>
                <input type="hidden" name="id" value="${cruise.id}">
                <div class="card-text">
                    <h4><fmt:message key="cruise.label.ship.name"/></h4><h5><c:out value="${cruise.ship.name}"/></h5>
                    <div class="row" style="text-align: center">
                        <h4><fmt:message key="cruise.label.departure"/></h4>
                        <p> --> </p>
                        <h4><fmt:message key="cruise.label.destination"/></h4>
                    </div>
                    <div class="row text-center">
                        <h5><c:out value="${cruise.route.destination}"/></h5>
                        <p> --> </p>
                        <h5><c:out value="${cruise.route.departure}"/></h5>
                    </div>
                    <button class="create-button" type="submit"><fmt:message key="button.order"/></button>
                </div>
            </form>
        </div>
    </c:forEach>
</div>
</body>
</html>