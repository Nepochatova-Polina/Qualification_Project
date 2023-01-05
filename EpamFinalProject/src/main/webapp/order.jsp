<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html <fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources"/>

<html lang="${sessionScope.locale}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="login.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/Styles/order.css" %>
    </style>
</head>
<body style="background-color: #5ca17f">
<div class="container" id="container">
    <div class="container align-content-center">
        <br>
        <h2 class="text-center" style="margin-top: 50px; color: #000000"><fmt:message key="header.login"/></h2>
        <div id="locale-changer" class="form-control">
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/login.jsp">
                <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale" value="ua">
            </form>
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/login.jsp">
                <input class="btn" style="background: lightgray; width: 50px;" type="submit" name="locale" value="en">
            </form>
        </div>
        <hr style="background-color: aliceblue">

        <div class="card text-white bg-dark mx-auto">
            <article class="card-body mx-auto" style="max-width: 400px;">
                <h3 class="card-title mt-3 text-center"><fmt:message key="form.header"/></h3>
                <br>
                <form id="order-form" method="post" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="createOrder"/>
                    <c:if test="${sessionScope.message != null}">
                        <h5 style="color: red; text-align: center"><fmt:message key="${sessionScope.message}"/></h5>
                    </c:if>
                    <div id="user-info">
                        <c:set var="user" scope="session" value="${sessionScope.user}"/>
                        <p><fmt:message key="user.label.firstName"/>: <c:out value="${user.firstName}"/>
                        <p><fmt:message key="user.label.lastName"/>: <c:out value="${user.lastName}"/>
                    </div>
                    <div id="cruise-info">
                        <c:set var="cruise" scope="session" value="${sessionScope.cruise}"/>
                        <p><fmt:message key="cr"/>: <c:out value="${user.firstName}"/>
                        <p><fmt:message key="user.label.lastName"/>: <c:out value="${user.lastName}"/>
                    </div>
                </form>
            </article>
        </div>
    </div>

</div>
</body>
</html>