<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources"/>

<html lang="${sessionScope.locale}">
<head>
    <style>
        <%@include file="/WEB-INF/Styles/index.css" %>
        <%@include file="/WEB-INF/Styles/auth.css" %>
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
    <a href="login.jsp"><fmt:message key="navbar.login"/></a>
    <a class="active" href="index.jsp"><fmt:message key="navbar.home"/></a>
    <a href="#contact">Contact</a>
    <a href="#about">About</a>
    </div>
    <div id="locale-changer" class="form-control col-sm-6" style="margin: 0">
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/index.jsp">
                <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale" value="ua">
            </form>
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/index.jsp">
                <input class="btn" style="background: lightgray; width: 50px;" type="submit" name="locale" value="en">
            </form>
    </div>
</div>
<div class="container">
    <h2>Card Group</h2>
    <div class="card-group">
        <%--        <c:forEach var="cruise"  items="cruises">--%>
        <%--            <div class="card">--%>
        <%--                <div class="card-body text-center">--%>
        <%--                    <p class="card-text"><c:out value="${cruise.name}"/></p>--%>
        <%--                    <button><fmt:message key="button.order"/> </button>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </c:forEach>--%>

    </div>
</div>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="multiPartServlet">Hello Servlet</a>
<form method="post" action="multiPartServlet" enctype="multipart/form-data">
    Choose a file: <input type="file" name="file" accept="image/jpeg"/>
    <input type="submit" value="Upload"/>
</form>
<a href="login.jsp"><fmt:message key="login.title"/></a>
<br>
<a href="signUp.jsp">Registration page</a>
</body>
</html>