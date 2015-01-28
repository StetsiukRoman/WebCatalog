<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.google.gson.Gson"
		 import="java.io.FileWriter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to the Catalog</title>
  <link rel="stylesheet" type="text/css" href="../css/menubar.css"> 
  <link rel="stylesheet" type="text/css" href="../css/sitestyle.css"> 
  <link rel="stylesheet" type="text/css" href="../css/table.css"> 
<script src= "http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
</head>
<body >
<div ng-app ng-controller="positionsController">

	<c:choose> 
		<c:when test="${sessionScope.positionsData eq null}">
			<c:redirect url="/ShowPositions" />
		</c:when>
	</c:choose> 
 
	<c:import url="../header.jsp" />
	<p>Filtering input:</p>
	<p><input type="text" ng-model="search"></p>
	<div class="container">
		<c:import url="../navbar.jsp" />
		<table  border=1>
			<tr>
				<th>N</th>
				<th>POSIONS</th>
			</tr>
		  	<tr ng-repeat="x in jsonPos | filter:search ">
		  		<td>{{$index+1}}</td>
		    	<td>{{ x.describePos }}</td>
		 	 </tr>
		</table>
<!-- 	<div class="content">
			<table border=1> 
				<tr>
					<th>N</th>
					<th>POSIONS</th>
				</tr>
				<c:forEach var="positionsData" items="${sessionScope.positionsData}" varStatus="iterationCount">
					<tr>
						<td>${iterationCount.count}</td>
						<td>${positionsData.describePos}</td>
					</tr>
				</c:forEach>
			</table>
		</div> -->
	</div>
	</div>
	<script>
		function positionsController($scope,$http) {
		  $http.get("http://localhost:8081/CatalogApp/JsonPositions")
		  .success(function(response) {$scope.jsonPos = response;});
		}
	</script>
</body>
</html>