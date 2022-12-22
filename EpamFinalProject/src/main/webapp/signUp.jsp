<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Sign In</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style><%@include file="/WEB-INF/Styles/auth.css"%></style>
</head>
<body style="background-color: #5ca17f">
<div class="container" id="container">
    <div class="container align-content-center">
        <br>
        <h2 class="text-center" style="margin-top: 50px; color: aliceblue">Sign In Form</h2>
        <hr style="background-color: aliceblue">

        <div class="card text-white bg-dark mx-auto">
            <article class="card-body mx-auto" style="max-width: 400px;">
                <h3 class="card-title mt-3 text-center">Fill the fields!</h3>
                <br>
                <form id="registration-form" method="post" action="registration">
                    <div class="form-group">
                        <label for="firstname">First Name</label>
                        <input
                                type="text"
                                name="firstname"
                                id="firstname"
                                placeholder="First name"
                                required
                                class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="lastname">Last Name</label>
                        <input
                                type="text"
                                name="lastname"
                                id="lastname"
                                placeholder="Last name"
                                required
                                class="form-control col">
                    </div>
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input
                                type="text"
                                name="username"
                                id="username"
                                placeholder="User name"
                                required
                                class="form-control col">
                    </div>
                    <div id="usernameError" style="color:red;">${usernameError}</div>
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
                    <div class="form-group">
                        <label for="confirmPassword">Confirm Password:</label>
                        <input
                                type="password"
                                name="confirmPassword"
                                id="confirmPassword"
                                placeholder="Password"
                                required
                                class="form-control col">
                    </div>
                    <div id="passwordError" style="color:red;">${passwordError}</div>
                    <div class=form-group" style="margin-top: 30px">
                        <button type="submit" id="login-submit" class="btn btn-warning col"> Sign in</button>
                    </div>
                    <p class="text-center">Misclicked? <a href="login.jsp" style="color: orange">Come back</a></p>
                </form>
            </article>
        </div>
    </div>

</div>
</body>
</html>