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
  <title><fmt:message key="delete.staff.title"/></title>
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
    <a class="active" href="../../index.jsp"><fmt:message key="navbar.home"/></a>
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
        <input type="hidden" name="page-path" value="/Admin/Delete/deleteStaff.jsp">
        <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale"
               value="ua">
      </form>
      <form method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="changeLocale">
        <input type="hidden" name="page-path" value="/Admin/Delete/deleteStaff.jsp">
        <input class="btn" style="background: lightgray; width: 50px;" type="submit" name="locale"
               value="en">
      </form>
    </div>
  </div>
</div>
<hr style="background-color: aliceblue">
<form class="container" method="get" action="${pageContext.request.contextPath}/controller">
  <input type="hidden" name="command" value="deleteStaff"/>
  <c:if test="${sessionScope.message != null}">
    <h5 style="color: red; text-align: center"><fmt:message key="${sessionScope.message}"/></h5>
  </c:if>
  <table>
    <tr>
      <td>
        <h5><strong><fmt:message key="label.check"/></strong></h5>
      </td>
      <td>
        <h5><strong><fmt:message key="label.id"/></strong></h5>
      </td>
      <td>
        <h5><strong><fmt:message key="user.label.firstName"/></strong></h5>
      </td>
      <td>
        <h5><strong><fmt:message key="user.label.lastName"/></strong></h5>
      </td>
      <td>
        <h5><strong><fmt:message key="cruise.label.ship.id"/></strong></h5>
      </td>
    </tr>
    <c:forEach var="staff" items="${sessionScope.staff}">
      <tr>
        <td><input type="checkbox" name="id" value="${staff.id}"></td>
        <td><c:out value="${staff.id}"/></td>
        <td><c:out value="${staff.firstName}"/></td>
        <td><c:out value="${staff.lastName}"/></td>
        <td><c:out value="${staff.ship_id}"/></td>
      </tr>
    </c:forEach>
  </table>
  <input type="submit">
</form>
</body>
</html>
