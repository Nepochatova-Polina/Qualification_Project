<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body style="background-color: #5ca17f">
<div class="container" id="container">
    <div class="container align-content-center">
        <br>
        <h2 class="text-center" style="margin-top: 50px; color: aliceblue">Login Form</h2>
        <hr style="background-color: aliceblue">

        <div class="card text-white bg-dark mx-auto" style="max-width: 600px;margin-top: 130px">
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
                    <div class= form-group" style="margin-top: 30px">
                        <button type="submit" id="login-submit" class="btn btn-warning col" > Log in </button>
                    </div>
                    <p class="text-center" >Misclicked? <a href="index.jsp" style="color: orange">Come back</a></p>
                </form>
            </article>
        </div>
    </div>

    <div class="modal fade" id="failureModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-danger">Login Failure</h5>
                </div>
                <div class="modal-body">
                    <p id="failureModalText"></p>
                </div>
                <div class="modal-footer" id="failureModalFooter">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>