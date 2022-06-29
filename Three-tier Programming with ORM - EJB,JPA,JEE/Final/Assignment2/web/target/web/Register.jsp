<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
    <h1>Register</h1>
    <br>
    <form action="<%=request.getContextPath()%>/Register" method="post">
    <div class="form-group">
        <label for="username">User Name:</label>
            <input type="text" class="form-control" id="username" placeholder="User Name" name="username" required>
    </div>

    <div class="form-group">
        <label for="email">Email:</label>
            <input type="text" class="form-control" id="email" placeholder="Email" name="email" required>
    </div>

    <div class="form-group">
        <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
    </div>

    <div class="form-group">
        <label for="address">Address:</label>
            <input type="text" class="form-control" id="address" placeholder="Address" name="address" required>
    </div>

    <div class="form-group">
        <label for="phone">Phone:</label>
            <input type="text" class="form-control" id="phone" placeholder="Phone" name="phone" required>
    </div>

        <button type="submit" class="btn btn-primary">Submit</button>
        <%
            if(null!=request.getAttribute("errorMessage")) {
        %>
        <% String message = (String)request.getAttribute("errorMessage");%>
        <script type="text/javascript">
            var msg = "<%=message%>";
            alert(msg);
        </script>
        <% } %>
    </form>
</div>
</body>
</html>

