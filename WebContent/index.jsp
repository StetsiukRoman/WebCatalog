<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Welcome to the Catalog</title>
  <link rel="stylesheet" type="text/css" href="css/menubar.css"> 
  <link rel="stylesheet" type="text/css" href="css/sitestyle.css"> 
</head>
<body>
	<c:import url="header.jsp" />
	<p style="visibility: hidden;">Filtering input:</p>
	<p><input type="text" style="visibility: hidden;" ng-model="search"></p>
	<div class="container">
		<c:import url="navbar.jsp" />
		<div class="content">
			<h1 style="margin: 0;"><c:out value="    Welcome to the Catalog" /></h1>
			
			<c:if test="${sessionScope.authorized_user ne null}">
				<h2><c:out value="User: ${sessionScope.authorized_user.userId}" /></h2>
			</c:if>
			<c:if test="${sessionScope.authorized_user eq null}">
				<h2><c:out value="Returning users login" /></h2>
						<a href="login.jsp">here</a>
			</c:if>
		</div>
	</div>			
</body>
</html>