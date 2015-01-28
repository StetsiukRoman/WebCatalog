<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to the Catalog</title>
  <title>Welcome to the Catalog</title>
  <link rel="stylesheet" type="text/css" href="../css/menubar.css"> 
  <link rel="stylesheet" type="text/css" href="../css/sitestyle.css"> 
</head>
<body>
	<c:import url="../header.jsp" />
	<p style="visibility: hidden;">Filtering input:</p>
	<p><input type="text" style="visibility: hidden;" ng-model="search"></p>
	<div class="container">
		<c:import url="../navbar.jsp" />
		<div class="content">
			<form action="${initParam.baseURL}/AddPosition" method="post">
				<table border=1> 
					<tr>
						<td>New Position</td>
						<td>
							<input type="text" name="position" style="width:100%;" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input type="submit" name="addposition" value="Add Position" style="width:235px;"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>			
</body>
</html>

	

	
	
	
				
	
				