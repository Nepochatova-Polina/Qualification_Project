<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="multiPartServlet">Hello Servlet</a>
<form method="post" action="multiPartServlet" enctype="multipart/form-data">
    Choose a file: <input type="file" name="file" accept="image/jpeg"/>
    <input type="submit" value="Upload" />
</form>
<a href="login">Login page</a>
<br>
<a href="logout.jsp">Logout page</a>
<br>
<a href="registration.jsp">Registration page</a>
</body>
</html>