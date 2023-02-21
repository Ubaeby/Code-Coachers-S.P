<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
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
				<a class="text-decoration-none text-white" href="new">Profile</a>
			</div>
		</c:if>
		<div class="nav-item">
			<a class="text-decoration-none text-white" href="/logout">Logout</a>
		</div>
	</nav>

	<section class="section3 text-white">
		<h3 class="text-center cfy">
			Hey
			<c:out value="${users.name}" />
			, let's set up your profile!
		</h3>
		<form:form class="mb-3 bg-dark rounded-pill" action="/profile/new" method="post"
			modelAttribute="newCoach">
			<%-- 		<form:label path="image">Profile Pic: </form:label> --%>
			<%-- 		<form:input type="file" path="image" name="image"/> --%>

			<div class="form-div">
				<form:label class="form-label" path="bio">Bio: </form:label>
				<form:errors class="text-danger" path="bio" />
				<form:textarea class="form-control" path="bio" height="30px"/>
			</div>

			
			<div class="form-div">
				<form:label class="form-label" path="price">Price: </form:label>
				<form:errors class="text-danger" path="price" />
				<form:input class="form-control" type="number" path="price" />
			</div>

			<div class="form-div">
				<form:label class="form-label" path="color">Pick a Color:</form:label>
				<form:input class="form-control form-control-color m-2" path="color"
					type="color" value="ff0000" />
			</div>

			<div class="form-div">
				<form:label class="form-label" path="lang">Language?</form:label>
				<form:errors class="text-danger" path="lang" />
				<form:input class="form-control" path="lang"
					placeholder="exp: Java, Chef++, Python" />
			</div>


			<button class="btn btn-success rounded-pill registerButton">Create Profile</button>
		</form:form>
	</section>


</body>
</html>