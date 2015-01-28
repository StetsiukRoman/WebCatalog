<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to the Catalog</title>
  <link rel="stylesheet" type="text/css" href="../css/menubar.css"> 
  <link rel="stylesheet" type="text/css" href="../css/sitestyle.css"> 
  <link rel="stylesheet" type="text/css" href="../css/table.css"> 
  <script src= "http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
</head>
<body>
<div ng-app ng-controller="addressesController">
	<c:choose> 
		<c:when test="${sessionScope.addressesData eq null}">
			<c:redirect url="/ShowAddresses" />
		</c:when>
	</c:choose> 
	<c:import url="../header.jsp" />
	<p>Filtering input:</p>
	<p><input type="text" ng-model="search"></p>
	  <div class="container">
		<c:import url="../navbar.jsp" />
			<div class="content">
				<table border=1> 
					<tr>
						<th>N</th>
						<th>COUNTRY</th>
						<th>CITY</th>
						<th>STREET</th>
						<th>HOUSE</th> 
					</tr>
				  	<tr ng-repeat="x in jsonAddress | filter:search ">
				  		<td>{{$index+1}}</td>
				    	<td>{{ x.country }}</td>
				    	<td>{{ x.city }}</td>
				    	<td>{{ x.street }}</td>
				    	<td>{{ x.house }}</td>
				 	</tr>
					<!-- <c:forEach var="addressesData" items="${sessionScope.addressesData}" varStatus="iterationCount">
						<tr>
							<td>${iterationCount.count}</td>
							<td>${addressesData.country}</td>
							<td>${addressesData.city}</td>
							<td>${addressesData.street}</td>
							<td>${addressesData.house}</td>
						</tr>
					</c:forEach>-->
				</table>
			</div>
		</div>
	</div>
	<script>
		function addressesController($scope,$http) {
		  $http.get("http://localhost:8081/CatalogApp/JsonAddresses")
		  .success(function(response) {$scope.jsonAddress = response;});
		}
	</script>
</body>
</html>