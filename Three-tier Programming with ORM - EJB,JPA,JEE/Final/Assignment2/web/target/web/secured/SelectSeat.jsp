<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Select Seat</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto; text-align: center" >
    <br>
    <h1>Select Seat</h1>
    <br>
    <form action="<%=request.getContextPath()%>/tickets" method="get">
        <br>
        <%
            if(null!=request.getAttribute("ErrorMessage")) {
        %>
        <% String message = (String)request.getAttribute("ErrorMessage");%>
        <script type="text/javascript">
            var msg = "<%=message%>";
            alert(msg);
        </script>
        <% }
            else {
        %>
        <select name="seat_list" class="form-select" aria-label="Default select example" required="required">
            <p>Choose your seat</p>
            <c:forEach var="t" items="${seats}">
                <option value="${t}">${t}</option>
            </c:forEach>
        </select>
        <br>
        <br>
        <button name="token" value="seat_selected" type="submit" class="btn btn-outline-dark">Buy Ticket</button>
        <% } %>
    </form>
</div>
</body>
<div style="position:absolute;bottom:10px;right:10px; z-index:999" >
    <form action="InitialPage" method="get">
        <button type="submit" class="btn btn-outline-danger">Log Out</button>
    </form>
</div>
</html>
