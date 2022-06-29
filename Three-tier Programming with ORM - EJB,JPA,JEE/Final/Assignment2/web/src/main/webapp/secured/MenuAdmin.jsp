<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Menu</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<br>
<br>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto; text-align: center" >
    <h1>Menu</h1>
    <br>
    <br>
    <form action="trips" method="post">
        <button type="submit" class="btn btn-primary">Trip</button>
    </form>
    <br>
    <br>
    <form action="secured/CreateTrip.jsp">
        <button type="submit" class="btn btn-primary">Create Trip</button>
    </form>
    <br>
    <br>
    <form action="secured/GetExactDate.jsp">
        <button type="submit" class="btn btn-primary">Exact Trip Search</button>
    </form>
    <br>
    <br>
    <form action="admin" method="post">
        <button name="token" value="delete_trip" type="submit" class="btn btn-primary">Remove Trip</button>
    </form>
    <br>
    <br>
    <form action="admin" method="post">
        <button name="token" value="top5" type="submit" class="btn btn-primary">Top 5</button>
    </form>
    <form action="admin" method="post">
        <button name="token" value="revenue" type="submit" class="btn btn-primary">Revenue</button>
    </form>
</div>
</body>
</html>
