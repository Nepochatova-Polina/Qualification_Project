<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources"/>

<html lang="${sessionScope.locale}">
<head>
    <style>
        <%@include file="/WEB-INF/Styles/adminAccount.css" %>
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
        <a class="active" href="../index.jsp"><fmt:message key="navbar.home"/></a>
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
                <input type="hidden" name="page-path" value="/Admin/adminAccount.jsp">
                <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale"
                       value="ua">
            </form>
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/Admin/adminAccount.jsp">
                <input class="btn" style="background: lightgray; width: 50px;" type="submit" name="locale"
                       value="en">
            </form>
        </div>
    </div>
</div>
<div class="container-fluid row">
    <div class="user col-sm-2">
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
    <div class="col-sm-8 commands container-fluid">
        <div class="text-center">
            <h2>SERVICES</h2>
            <h3 class="text-left"><strong><fmt:message key="admin.label.create"/></strong></h3>
            <br>
            <div class="row">
                <a class="col-sm-3" href="createCruise.jsp">
                    <h5><strong><fmt:message key="admin.entity.label.cruise"/></strong></h5>
                    <p><fmt:message key="admin.description.label.create.cruise"/></p>
                </a>
                <a class="col-sm-3" href="createRoute.jsp">
                    <h5><strong><fmt:message key="admin.entity.label.route"/></strong></h5>
                    <p><fmt:message key="admin.description.label.create.route"/></p>
                </a>
                <a class="col-sm-3" href="createShip.jsp">
                    <h5><strong><fmt:message key="admin.entity.label.ship"/></strong></h5>
                    <p><fmt:message key="admin.description.label.create.ship"/></p>
                </a>
                <a class="col-sm-3" href="createStaff.jsp">
                    <h5><strong><fmt:message key="admin.entity.label.staff"/></strong></h5>
                    <p><fmt:message key="admin.description.label.create.staff"/></p>
                </a>
            </div>
            <br>
            <h3 class="text-left"><strong><fmt:message key="admin.label.edit"/></strong></h3>
            <br>
            <div class="row">
                <a class="col-sm-3" href="#">
                    <h5><strong><fmt:message key="admin.entity.label.cruise"/></strong></h5>
                    <p><fmt:message key="admin.description.label.edit.cruise"/></p>
                </a>
                <a class="col-sm-3" href="#">
                    <h5><strong><fmt:message key="admin.entity.label.route"/></strong></h5>
                    <p><fmt:message key="admin.description.label.edit.route"/></p>
                </a>
                <a class="col-sm-3" href="#">
                    <h5><strong><fmt:message key="admin.entity.label.ship"/></strong></h5>
                    <p><fmt:message key="admin.description.label.edit.ship"/></p>
                </a>
                <a class="col-sm-3" href="#">
                    <h5><strong><fmt:message key="admin.entity.label.staff"/></strong></h5>
                    <p><fmt:message key="admin.description.label.edit.staff"/></p>
                </a>
            </div>
            <br>
            <br>
            <h3 class="text-left"><strong><fmt:message key="admin.label.delete"/></strong></h3>
            <br>
            <div class="row">
                <a class="col-sm-3" href="#">
                    <input type="hidden" name="command" value="deleteCruise"/>
                    <h5><strong><fmt:message key="admin.entity.label.cruise"/></strong></h5>
                    <p><fmt:message key="admin.description.label.delete.cruise"/></p>
                </a>
                <a class="col-sm-3" href="#">
                    <input type="hidden" name="command" value="deleteRoute"/>
                    <h5><strong><fmt:message key="admin.entity.label.route"/></strong></h5>
                    <p><fmt:message key="admin.description.label.delete.route"/></p>
                </a>
                <a class="col-sm-3" href="#">
                    <input type="hidden" name="command" value="deleteShip"/>
                    <h5><strong><fmt:message key="admin.entity.label.ship"/></strong></h5>
                    <p><fmt:message key="admin.description.label.delete.ship"/></p>
                </a>
                <a class="col-sm-3" href="#">
                    <input type="hidden" name="command" value="deleteStaff"/>
                    <h5><strong><fmt:message key="admin.entity.label.staff"/></strong></h5>
                    <p><fmt:message key="admin.description.label.delete.staff"/></p>
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>