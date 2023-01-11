<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources"/>

<html lang="${sessionScope.locale}">
<head>
    <style>
        <%@include file="/WEB-INF/Styles/clientAccount.css" %>
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <title><fmt:message key="profile.title"/></title>
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
                <input type="hidden" name="page-path" value="/clientAccount.jsp">
                <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale"
                       value="ua">
            </form>
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/clientAccount.jsp">
                <input class="btn" style="background: lightgray; width: 50px;" type="submit" name="locale"
                       value="en">
            </form>
        </div>
    </div>
</div>
<div class="container row">
    <div class="user col-sm-4">
        <div class="user_info row">
            <c:set var="user" scope="session" value="${sessionScope.user}"/>
            <div class="col-sm-4">
                <p><h5><strong><fmt:message key="user.label.login"/>:</strong></h5>
                <c:out value="${user.login}"/>
            </div>
            <div class="col-sm-8">
                <p><h5><strong><fmt:message key="user.label.firstName"/>:</strong></h5>
                <c:out value="${user.firstName}"/>
                <p><h5><strong><fmt:message key="user.label.lastName"/>:</strong></h5>
                <c:out value="${user.lastName}"/>
            </div>
        </div>
        <div>
            <form id="logout" class="navbar-form" method="get" action="${pageContext.request.contextPath}/controller">
            <input type="submit" class="btn col logout-btn" value="<fmt:message key="button.logout"/>">
            <input type="hidden" name="command" value="logout"/>
            </form>
        </div>
    </div>
    <div class="orders col-sm-7">
        <c:forEach var="order" items="${sessionScope.orders}">
            <div class="card">
                <div class="card-header">
                    <p><c:out value="${order.cruise.name}"/>
                </div>
                <form class="card-body text-center">
                    <div class="card-text text-center">
                        <p><fmt:message key="cruise.label.ship.name"/>:
                                <c:out value="${order.cruise.ship.name}"/>
                        <div class="row" style="text-align: center">
                            <h5><strong><fmt:message key="cruise.label.departure"/></strong></h5>
                            <p> --> </p>
                            <h5><strong><fmt:message key="cruise.label.destination"/></strong></h5>
                        </div>
                        <div class="row text-center">
                            <h6><c:out value="${order.cruise.route.destination}"/></h6>
                            <p> --> </p>
                            <h6><c:out value="${order.cruise.route.departure}"/></h6>
                        </div>
                        <div class="row" style="text-align: center">
                            <h5><strong><fmt:message key="cruise.label.leaving.date"/></strong></h5>
                            <p> --> </p>
                            <h5><strong><fmt:message key="cruise.label.arriving.date"/></strong></h5>
                        </div>
                        <div class="row text-center">
                            <h6><c:out value="${order.cruise.startOfTheCruise}"/></h6>
                            <p> --> </p>
                            <h6><c:out value="${order.cruise.endOfTheCruise}"/></h6>
                        </div>
                        <p><fmt:message key="cruise.label.price"/>:
                                <c:out value="${order.cruise.price}$"/>
                    </div>
                    <div class="card-footer">
                        <p><fmt:message key="order.label.status"/>:
                                <c:out value="${order.status}"/>
                    </div>
                </form>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>