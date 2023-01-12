<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources"/>

<html lang="${sessionScope.locale}">
<head>
    <style>
        <%@include file="/WEB-INF/Styles/adminForm.css" %>
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <title><fmt:message key="create.cruise.title"/></title>
</head>
<body>
<div class="navbar row">
    <div class="navElements col-sm-6">
        <a>
            <form id="account" class="navbar-form" method="get" action="${pageContext.request.contextPath}/controller">
                <input type="submit" class="btn col" value="<fmt:message key="button.profile"/>">
                <input type="hidden" name="command" value="profile"/>
            </form>
        </a>
        <a class="active" href="index.jsp"><fmt:message key="navbar.home"/></a>
        <a>
            <form id="catalog" class="navbar-form" method="get" action="${pageContext.request.contextPath}/controller">
                <input type="submit" class="btn col" value="<fmt:message key="button.cruises.catalogue"/>">
                <input type="hidden" name="command" value="catalogue"/>
            </form>
        </a>
    </div>
    <div class="navElements col-sm-6">
        <div id="locale-changer" class="form-control">
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/index.jsp">
                <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale"
                       value="ua">
            </form>
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/index.jsp">
                <input class="btn" style="background: lightgray; width: 50px;" type="submit" name="locale"
                       value="en">
            </form>
        </div>
    </div>
</div>
<div class="row main">
    <div class="container col-sm-3">
        <h3><strong><fmt:message key="admin.entity.label.route"/> </strong></h3>
        <table>
            <tr>
                <td>
                    <h5><strong><fmt:message key="label.id"/></strong></h5>
                </td>
                <td>
                    <h5><strong><fmt:message key="route.label.departure"/></strong></h5>
                </td>
                <td>
                    <h5><strong><fmt:message key="route.label.destination"/></strong></h5>
                </td>
            </tr>
            <c:forEach var="route" items="${sessionScope.routes}">
                <tr>
                    <td><c:out value="${route.id}"/></td>
                    <td><c:out value="${route.departure}"/></td>
                    <td><c:out value="${route.destination}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="col-sm-5">
        <form id="dates-from" class="row" method="post" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="filterShips"/>
            <div class="form-group col-sm-3">
                <label for="start_date"><fmt:message key="cruise.label.leaving.date"/></label>
                <input
                        type="date"
                        name="start_date"
                        id="start_date"
                        required
                        class="form-control col">
            </div>
            <div class="form-group col-sm-3">
                <label for="end_date"><fmt:message key="cruise.label.arriving.date"/></label>
                <input
                        type="date"
                        name="end_date"
                        id="end_date"
                        required
                        class="form-control col">
            </div>
            <input type="submit" id="date-submit" value="<fmt:message key="button.search"/>"
                   style="height: 40px; margin-top: 18px; border-radius: 20px">
        </form>

        <div class="card  mx-auto">
            <article class="card-body mx-auto" style="max-width: 400px;">
                <h3 class="card-title mt-3 text-center"><fmt:message key="form.header"/></h3>
                <br>
                <form id="cruise-form" method="post" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="createCruise"/>
                    <c:if test="${sessionScope.message != null}">
                        <h5 style="color: red; text-align: center"><fmt:message key="${sessionScope.message}"/></h5>
                    </c:if>
                    <div class="form-group">
                        <label for="cruise_name"><fmt:message key="cruise.label.name"/></label>
                        <input
                                type="text"
                                name="cruise_name"
                                id="cruise_name"
                                placeholder="<fmt:message key="cruise.placeholder.name"/>"
                                required
                                class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="ship_id"><fmt:message key="cruise.label.ship.id"/></label>
                        <input
                                type="number"
                                name="ship_id"
                                id="ship_id"
                                placeholder="<fmt:message key="cruise.placeholder.ship.id"/>"
                                required
                                class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="route_id"><fmt:message key="cruise.label.route.id"/></label>
                        <input
                                type="number"
                                name="route_id"
                                id="route_id"
                                placeholder="<fmt:message key="cruise.placeholder.route.id"/>"
                                required
                                class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="start_date"><fmt:message key="cruise.label.leaving.date"/></label>
                        <input
                                type="date"
                                name="start_date"
                                id="start_date"
                                required
                                class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="end_date"><fmt:message key="cruise.label.arriving.date"/></label>
                        <input
                                type="date"
                                name="end_date"
                                id="end_date"
                                required
                                class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="price"><fmt:message key="cruise.label.price"/>$</label>
                        <input
                                type="number"
                                name="price"
                                id="price"
                                placeholder="<fmt:message key="cruise.label.price"/>$"
                                required
                                class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="duration"><fmt:message key="cruise.label.duration"/></label>
                        <input
                                type="number"
                                name="duration"
                                id="duration"
                                placeholder="<fmt:message key="cruise.label.duration"/>"
                                required
                                class="form-control col">
                    </div>
                    <div class=form-group" style="margin-top: 30px">
                        <button type="submit" id="login-submit" class="btn final-btn col"><fmt:message
                                key="admin.button.create"/></button>
                    </div>
                    <p class="text-center">
                        <fmt:message key="form.label.back"/>
                        <a href="adminAccount.jsp" style="color: green"><fmt:message key="button.back"/></a>
                    </p>
                </form>
            </article>
        </div>
    </div>
    <div class="container col-sm-3">
        <h3><strong><fmt:message key="admin.entity.label.ship"/> </strong></h3>
        <c:if test="${sessionScope.shipMmessage != null}">
            <h5 style="color: red; text-align: center"><fmt:message key="${sessionScope.shipMessage}"/></h5>
        </c:if>
        <table>
            <tr>
                <td>
                    <h4><strong><fmt:message key="label.id"/></strong></h4>
                </td>
                <td>
                    <h4><strong><fmt:message key="ship.label.name"/></strong></h4>
                </td>
                <td>
                    <h4><strong><fmt:message key="ship.label.capacity"/></strong></h4>
                </td>
            </tr>
            <c:forEach var="ship" items="${sessionScope.ships}">
                <tr>
                    <td><h5><c:out value="${ship.id}"/></h5></td>
                    <td><h5><c:out value="${ship.name}"/></h5></td>
                    <td><h5><c:out value="${ship.passengerCapacity}"/></h5></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>