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
<div ng-app ng-controller="usersController">
	<c:choose> 
		<c:when test="${sessionScope.usersData eq null}">
			<c:redirect url="/ShowUsers" />
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
						<th>FIRST NAME</th>
						<th>SECOND NAME</th>
						<th>PATRONYMIC</th>
						<th>POSITION</th>
						<th>COUNTRY</th>
						<th>CITY</th>
						<th>STREET</th>
						<th>HOUSE</th> 
						<th>PHONE</th>
						<th>NOTE</th>
					</tr>
					<tr ng-repeat="x in jsonUsers | filter:search ">
				  		<td>{{$index+1}}</td>
				    	<td>{{ x.firstName }}</td>
				    	<td>{{ x.secondName }}</td>
				    	<td>{{ x.patronymic }}</td>
				    	<td>{{ x.position.describePos }}</td>
				    	<td>{{ x.address.country }}</td>
				    	<td>{{ x.address.city }}</td>
				    	<td>{{ x.address.street }}</td>
				    	<td>{{ x.address.house }}</td>
				    	<td>{{ x.phone }}</td>
				    	<td>{{ x.note }}</td>
				 	</tr>
					<!-- <c:forEach var="usersData" items="${sessionScope.usersData}" varStatus="iterationCount">
						<tr>
							<td>${iterationCount.count}</td>
							<td>${usersData.firstName}</td>
							<td>${usersData.secondName}</td>
							<td>${usersData.patronymic}</td>
							<td>${usersData.position.describePos}</td>
							<td>${usersData.address.country}</td>
							<td>${usersData.address.city}</td>
							<td>${usersData.address.street}</td>
							<td>${usersData.address.house}</td>
							<td>${usersData.phone}</td>
							<td>${usersData.note}</td> 
						</tr>
					</c:forEach>-->
				</table>
			</div>
		</div>
	</div>
	<script>
		function usersController($scope,$http) {
		  $http.get("http://localhost:8081/CatalogApp/JsonUsers")
		  .success(function(response) {$scope.jsonUsers = response;});
		}
	</script>
</body>
</html>