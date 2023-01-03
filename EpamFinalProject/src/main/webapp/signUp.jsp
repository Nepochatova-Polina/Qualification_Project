<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="signUp.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style><%@include file="/WEB-INF/Styles/auth.css"%></style>
</head>
<body style="background-color: #5ca17f">
<div class="container" id="container">
    <div class="container align-content-center">
        <br>
        <h2 class="text-center" style="margin-top: 50px; color: aliceblue"><fmt:message key="header.signUp"/></h2>
        <hr style="background-color: aliceblue">

        <div class="card text-white bg-dark mx-auto">
            <article class="card-body mx-auto" style="max-width: 400px;">
                <h3 class="card-title mt-3 text-center"><fmt:message key="form.header"/></h3>
                <br>
                <form id="registration-form" method="post" action="signUp">
                    <c:if test="${sessionScope.message != null}">
                        <h5 style="color: red; text-align: center"><fmt:message key="${sessionScope.message}"/></h5>
                    </c:if>
                    <div class="form-group">
                        <label for="firstname"><fmt:message key="user.label.firstName"/></label>
                        <input
                                type="text"
                                name="firstname"
                                id="firstname"
                                placeholder="<fmt:message key="signUp.placeholder.firstName"/>"
                                required
                                class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="lastname"><fmt:message key="user.label.lastName"/></label>
                        <input
                                type="text"
                                name="lastname"
                                id="lastname"
                                placeholder="<fmt:message key="signUp.placeholder.lastName"/>"
                                required
                                class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="login"><fmt:message key="user.label.login"/></label>
                        <input
                                type="text"
                                name="login"
                                id="login"
                                placeholder="<fmt:message key="signUp.placeholder.login"/>"
                                required
                                class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="password"><fmt:message key="user.label.password"/></label>
                        <input
                                type="password"
                                name="password"
                                id="password"
                                placeholder="<fmt:message key="signUp.placeholder.password"/>"
                                required
                                class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword"><fmt:message key="user.label.confirmPassword"/></label>
                        <input
                                type="password"
                                name="confirmPassword"
                                id="confirmPassword"
                                placeholder="<fmt:message key="signUp.placeholder.confirmPassword"/>"
                                required
                                class="form-control col">
                    </div>
                    <div class=form-group" style="margin-top: 30px">
                        <button type="submit" id="login-submit" class="btn btn-warning col"><fmt:message key="button.signUp"/> </button>
                    </div>
                    <p class="text-center">
                        <fmt:message key="form.label.misclicked"/>
                        <a href="login.jsp" style="color: orange"><fmt:message key="button.login"/></a>
                    </p>
                </form>
            </article>
        </div>
    </div>

</div>
</body>
</html>