<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Top 5</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto; text-align: center" >
  <br>
  <h1>Top 5 Clients of the Bus Company</h1>
  <br>
  <br>
  <table class="table">
    <thead class="bg-primary">
    <tr>
      <th>Name</th>
      <th>Tickets Bought</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${list}">
      <tr>
        <td><c:out value="${list}"/></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
<div style="position:absolute;bottom:10px;right:10px; z-index:999" >
  <form action="InitialPage" method="get">
    <button type="submit" class="btn btn-outline-danger">Log Out</button>
  </form>
</div>
</html>
