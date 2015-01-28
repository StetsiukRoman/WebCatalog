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
  <link rel="stylesheet" type="text/css" href="css/table.css"> 
</head>
<body>
	<c:import url="header.jsp" />
	<p style="visibility: hidden;">Filtering input:</p>
	<p><input type="text" style="visibility: hidden;" ng-model="search"></p>
	<div class="container">
		<c:import url="navbar.jsp" />
		<div class="content">
			<form id="login" method="post" action="/CatalogApp/LoginUser">
				<table style="width:450px;">
					<tr>
						<td>
							<span>UserName:</span>
						</td>
						<td>
							<input name="uid" type="text" style="width:250px;" value="${cookie.credentials_uid.value}" />
						</td>
					</tr>
					<tr>
						<td>
							<span>Password:</span>
						</td>
						<td>
							<input name="pwd" type="password" style="width:250px;" value="${cookie.credentials_pwd.value}"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input name="rememberMe" type="checkbox">&nbsp;Remember Me
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input type="submit" value="Sign In" 
							    style="width:250px;" />
						</td>
					</tr>
				</table>
				<input type="hidden" name="dest" value="${param.dest}" />
			</form>
		</div>
	</div>
</body>
</html>