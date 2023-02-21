<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/style.css" />
</head>
<body class="homeBody">

	<nav class="nav bg-dark d-flex justify-content-around">
		<div class="nav-item">
			<a class="text-decoration-none text-white" href="/home">Code
				Coachers</a>
		</div>
		<c:if test="${allUsers.coach == null}">
			<div class="nav-item">
				<a class="text-decoration-none text-white" href="profile/new">Profile</a>
			</div>
		</c:if>
		<div class="nav-item">
			<a class="text-decoration-none text-white" href="/logout">Logout</a>
		</div>
	</nav>

	<h3 class="text-white cfy">
		Coaches for you
		<c:out value="${allUsers.name}" />
	</h3>

	<div class="d-flex flex-wrap bg-dark fw-bold ">
		<c:forEach var="c" items="${coaches}">
			<a class="text-decoration-none m-3" href="/profile/${c.id}">
				<div class="rounded card shadow"
					style="background-color: ${c.color}; width: 225px; height: 150px ">

					<div class="bg-dark text-white card-title ">
						<img class="rounded-circle p-2" src="./uploads/${number}.jpg"
							alt="pic" style="width: 60px; height: 60px" />
							<c:if test="${allUsers.coach.id == c.id}">
								You (<c:out value="${c.user.name}"/>)
							</c:if>
							<c:if test="${allUsers.coach.id != c.id}">
								<c:out value="${c.user.name}"></c:out>
							</c:if>
							
						
					</div>

					<p class="m-auto fs-6 text-uppercase text-wrap text-white shadow-sm">
						<c:out value="${c.lang}"></c:out>
					</p>

				</div>
			</a>
		</c:forEach>
	</div>


</body>
</html>