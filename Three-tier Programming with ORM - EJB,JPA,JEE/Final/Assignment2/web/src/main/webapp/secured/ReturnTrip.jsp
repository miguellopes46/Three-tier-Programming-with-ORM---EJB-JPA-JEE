<%@ page import="data.Ticket" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Return Tickets</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto; text-align: center" >
    <br>
    <h1>Select ticket to delete</h1>
    <br>
    <form action="<%=request.getContextPath()%>/return_trip" method="get">
        <br>
        <table class="table">
            <thead class="bg-primary">
            <tr>
                <th>ID</th>
                <th>Seat</th>
                <th>Departure</th>
                <th>Arrival</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="t" items="${Tickets}">
                <tr>
                    <td><c:out value="${t.id}" /></td>
                    <td><c:out value="${t.seat}" /></td>
                    <td><c:out value="${t.getTrip().departure}" /></td>
                    <td><c:out value="${t.getTrip().arrival}" /></td>
                    <td><button name="token" value="${t.getTrip().id}" type="submit" class="btn btn-outline-danger">Return Ticket</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
</body>
<div style="position:absolute;bottom:10px;right:10px; z-index:999" >
    <form action="InitialPage" method="get">
        <button type="submit" class="btn btn-outline-danger">Log Out</button>
    </form>
</div>
</html>
