<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Create Trip</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto; text-align: center" >
    <h1>Create Trip</h1>
    <br>
    <form action="<%=request.getContextPath()%>/admin" method="post">
        <div class="form-group">
            <label for="departure">Departure:</label>
            <input type="text" class="form-control" id="departure" placeholder="Departure" name="departure" required>
        </div>

        <div class="form-group">
            <label for="arrival">Arrival:</label>
            <input type="text" class="form-control" id="arrival" placeholder="Arrival" name="arrival" required>
        </div>

        <div class="form-group">
            <label for="departure_time">Departure Time:</label>
            <input type="datetime-local" id="departure_time" name="departure_time">
        </div>

        <div class="form-group">
            <label for="arrival_time">Arrival Time:</label>
            <input type="datetime-local" id="arrival_time" name="arrival_time">
        </div>

        <div class="form-group">
            <label for="price">Price:</label>
            <input type="text" class="form-control" id="price" placeholder="Price" name="price" required>
        </div>

        <div class="form-group">
            <label for="capacity">Capacity:</label>
            <input type="text" class="form-control" id="capacity" placeholder="Capacity" name="capacity" required>
        </div>

        <button name="token" value="create_trip" type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<div style="position:absolute;bottom:10px;right:10px; z-index:999" >
    <form action="InitialPage" method="get">
        <button type="submit" class="btn btn-outline-danger">Log Out</button>
    </form>
</div>
</body>
</html>


