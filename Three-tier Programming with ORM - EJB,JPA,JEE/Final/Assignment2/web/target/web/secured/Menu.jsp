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
    <form action="tickets" method="post">
        <%
            if(null!=request.getAttribute("ErrorMessage")) {
        %>
        <% String message = (String)request.getAttribute("ErrorMessage");%>
        <script type="text/javascript">
            var msg = "<%=message%>";
            alert(msg);
        </script>
        <% } %>
        <button type="submit" class="btn btn-primary">Ticket</button>
    </form>
    <br>
    <br>
    <form action="secured/MyWallet.jsp">
        <button type="submit" class="btn btn-primary">My Wallet</button>
    </form>

    <br>
    <br>
    <form action="secured/GetIntervalDate.jsp">
        <button type="submit" class="btn btn-primary">Interval Trip Search</button>
    </form>
    <br>
    <br>
    <form action="admin" method="post">
        <button name="token" value="my_trips" type="submit" class="btn btn-primary">My Trips</button>
    </form>
    <br>
    <br>
    <form action="admin" method="post">
        <button name="token" value="return_trip" type="submit" class="btn btn-primary">Return Trip</button>
    </form>
</div>

<div style="position:absolute;top:10px;right:100px; z-index:999" >
    <strong>Good to see you, ${auth}</strong>
</div>
<div style="position:absolute;top:10px;left:10px; z-index:999" >
    <form action="admin" method="post">
        <button name="token" value="delete_account" type="submit" class="btn btn-outline-danger">Delete Account</button>
    </form>
</div>
<div style="position:absolute;top:10px;left:160px; z-index:999" >
    <form action="secured/EditProfile.jsp" method="post">
    <button type="submit" class="btn btn-info">Edit Account</button>
</form>
</div>
<div style="position:absolute;bottom:10px;right:10px; z-index:999" >
    <form action="InitialPage" method="get">
        <button type="submit" class="btn btn-outline-danger">Log Out</button>
    </form>
</div>
<div style="position:absolute;top:40px;right:100px; z-index:999" >
    <strong>My Wallet: ${wallet} €</strong>
</div>
</body>
</html>


