<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to the World Manager</title>
  <title>Welcome to the World Manager</title>
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
			<c:choose>
				<c:when test="${sessionScope.positionsData eq null}">
					<jsp:forward page="../GetPositions" />
				</c:when>
			</c:choose> 
			<form action="${initParam.baseURL}/AddUser" method="post">
				<table border=1> 
					<tr>
						<td>ID</td>
						<td>
							<span>Assigned when added</span>
						</td>
					</tr>
					<tr>
						<td>FIRST NAME</td>
						<td>
							<input type="text" name="firstName" style="width:135px;" />
						</td>
					</tr>
					<tr>
						<td>SECOND NAME</td>
						<td>
							<input type="text" name="secondName" style="width:135px;" />
						</td>
					</tr>
					<tr>
						<td>PATRONYMIC</td>
						<td>
							<input type="text" name="patronymic" style="width:135px;" />
						</td>
					</tr>
					<tr>
						<td>POSITION</td>
						<td>	
							<select name="idPosition" style="width:135px;">
								<option selected>--- Choose One ---</option>
								<c:forEach var="pos" items="${sessionScope.positionsData}">
									<option value="${pos.idPosition}">
										${pos.describePos}
									</option>
								</c:forEach>
							</select>
						</td>	
					</tr>
					<tr>
						<td>COUNTRY</td>
						<td>
							<input type="text" name="country" style="width:135px;"/>
						</td>
					</tr>
					<tr>
						<td>CITY</td>
						<td>
							<input type="text" name="city" style="width:135px;" />
						</td>
					</tr>
					<tr>
						<td>STREET</td>
						<td>
							<input type="text" name="street" style="width:135px;" />
						</td>
					</tr>
					<tr>
						<td>HOUSEME</td>
						<td>
							<input type="text" name="house" style="width:135px;" />
						</td>
					</tr>
					<tr>
						<td>PHONE</td>
						<td>
							<input type="text" name="phone" style="width:135px;" />
						</td>
					</tr>
					<tr>
						<td>NOTE</td>
						<td>
							<input type="text" name="note" style="width:135px;" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input type="submit" name="addUser" value="Add User" style="width:263px;"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>			
</body>
</html>

	
