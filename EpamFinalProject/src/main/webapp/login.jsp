<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Log In</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/Styles/auth.css" %>
    </style>
</head>
<body style="background-color: #5ca17f">
<div class="container" id="container">
    <div class="container align-content-center">
        <br>
        <h2 class="text-center" style="margin-top: 50px; color: aliceblue">Log In Form</h2>
        <hr style="background-color: aliceblue">

        <div class="card text-white bg-dark mx-auto">
            <article class="card-body mx-auto" style="max-width: 400px;">
                <h3 class="card-title mt-3 text-center">Fill the fields!</h3>
                <br>
                <form id="login-form" method="post" action="login">
                    <div class="form-group">
                        <label for="username">Nickname</label>
                        <input
                                type="text"
                                name="username"
                                id="username"
                                placeholder="User name"
                                required
                                class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input
                                type="password"
                                name="password"
                                id="password"
                                placeholder="Password"
                                required
                                class="form-control col">
                    </div>
                    <div id="message" style="color:red;"></div>
                    <div class=form-group" style="margin-top: 30px">
                        <button type="submit" id="login-submit" class="btn btn-warning col"> Log in</button>
                    </div>
                    <p class="text-center">Misclicked? <a href="registration.jsp" style="color: orange">Come back</a>
                    </p>
                </form>
            </article>
        </div>
    </div>

</div>
</body>
</html>