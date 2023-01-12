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
  <title><fmt:message key="create.route.title"/></title>
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
        <input type="hidden" name="page-path" value="Admin/createShip.jsp">
        <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale"
               value="ua">
      </form>
      <form method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="changeLocale">
        <input type="hidden" name="page-path" value="Admin/createShip.jsp">
        <input class="btn" style="background: lightgray; width: 50px;" type="submit" name="locale"
               value="en">
      </form>
    </div>
  </div>
</div>
<hr style="background-color: aliceblue">
<div class="card  mx-auto">
  <article class="card-body mx-auto" style="max-width: 400px;">
    <h3 class="card-title mt-3 text-center"><fmt:message key="form.header"/></h3>
    <br>
    <form id="login-form" method="post" action="${pageContext.request.contextPath}/controller">
      <input type="hidden" name="command" value="createShip"/>
      <c:if test="${sessionScope.message != null}">
        <h5 style="color: red; text-align: center"><fmt:message key="${sessionScope.message}"/></h5>
      </c:if>
      <div class="form-group">
        <label for="departure"><fmt:message key="route.label.departure"/></label>
        <input
                type="text"
                name="departure"
                id="departure"
                placeholder="<fmt:message key="route.placeholder.departure"/>"
                required
                class="form-control col">
      </div>
      <div class="form-group">
        <label for="destination"><fmt:message key="route.label.destination"/></label>
        <input
                type="text"
                name="destination"
                id="destination"
                placeholder="<fmt:message key="route.placeholder.destination"/>"
                required
                class="form-control col">
      </div>
      <div class="form-group">
        <label for="number_of_ports"><fmt:message key="route.label.numOfPorts"/></label>
        <input
                type="number"
                name="number_of_ports"
                id="number_of_ports"
                min="0"
                max="50"
                placeholder="<fmt:message key="route.placeholder.numOfPorts"/>"
                required
                class="form-control col">
      </div>
      <div class="form-group">
        <label for="transit_time"><fmt:message key="route.label.transitTime"/></label>
        <input
                type="number"
                name="transit_time"
                id="transit_time"
                min="1"
                max="40"
                placeholder="<fmt:message key="route.placeholder.transitTime"/>"
                required
                class="form-control col">
      </div>
      <div class=form-group" style="margin-top: 30px">
        <button type="submit" id="login-submit" class="btn final-btn col"><fmt:message key="admin.button.create"/> </button>
      </div>
      <p class="text-center">
        <fmt:message key="form.label.back"/>
        <a href="adminAccount.jsp" style="color: green"><fmt:message key="button.back"/></a>
      </p>
    </form>
  </article>
</div>
</div>
</body>
</html>