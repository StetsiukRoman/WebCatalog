<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar">
	<!-- method one: in the anchor -->
	<c:url value="http://localhost:8081/CatalogApp/index.jsp" var="indexURL" />
	<c:url value="http://localhost:8081/CatalogApp/Protected/listUsers.jsp" var="usersViewURL" />
	<c:url value="http://localhost:8081/CatalogApp/Protected/addUser.jsp" var="addUserURL" />
	<c:url value="http://localhost:8081/CatalogApp/Protected/listAddresses.jsp" var="addressesViewURL" />
	<c:url value="http://localhost:8081/CatalogApp/Protected/listPositions.jsp" var="positionsViewURL" />
	<c:url value="http://localhost:8081/CatalogApp/Protected/addPosition.jsp" var="addPositionURL" />
	<c:url value="http://localhost:8081/CatalogApp/signOut.jsp" var="signOutURL" />
	
	<ul style="list-style-type:none; padding:0px; margin:0px 0px 0px 0px;">
		<li><a href="${indexURL}">Home</a></li>
		<li><a href="${usersViewURL}">View Users</a></li>
		<c:if test="${sessionScope.authorized_user.authLevel eq 2}">
			<li><a href="${addUserURL}">Add User</a></li>
		</c:if>
		<li><a href="${addressesViewURL}">View Addresses</a></li>
		<li><a href="${positionsViewURL}">View Positions</a></li>
			<c:if test="${sessionScope.authorized_user.authLevel eq 2}">
				<li><a href="${addPositionURL}">Add Position</a></li>
			</c:if>
		<li><a href="${signOutURL}">Sign Out</a></li>
	</ul>
</div>

