<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/style.css"/>
</head>
<body class="homeBody">

	<nav class="nav bg-dark d-flex justify-content-around">
		<div class="nav-item">
			<a class="text-decoration-none text-white" href="/home">Code
				Coachers</a>
		</div>
		<div class="nav-item">
			<a class="text-decoration-none text-white" href="/logout">Logout</a>
		</div>
	</nav>
	
	<section class="section3 text-white">
		<h3 class="text-center cfy"> Hey <c:out value="${coaches.user.name}" />, want a change of pace? </h3>
	
	<form:form class="mb-3 bg-dark rounded-pill" action="/profile/edit/${coaches.id}" method="post" modelAttribute="coaches">
	<input type="hidden" name="_method" value="put"/>
	<form:input type="hidden" path="user"/>
	
		<div class="form-div">
			<form:label class="form-label" path="bio">Bio: </form:label>
			<form:errors class="text-danger" path="bio" />
			<form:textarea class="form-control" path="bio" value="${coaches.bio}"/>
		</div>

		<div class="form-div">
			<form:label class="form-label" path="price">Price: </form:label>
			<form:errors class="text-danger" path="price" />
			<form:input class="form-control" type="number" path="price" placeholder="${coaches.price}"/>
		</div>
		
		
		<div class="form-div">
			<form:label class="form-label" path="color">Pick a Color:</form:label>
			<form:input class="form-control form-control-color m-2" path="color"
				type="color" value="${coaches.color}" />
		</div>
	
		<div class="form-div">
			<form:label class="form-label" path="lang">Language?</form:label>
			<form:errors class="text-danger" path="lang" />
			<form:input class="form-control" path="lang"
					placeholder="exp: Java, Chef++, Python" />
		</div>
	
		<button class="btn btn-success rounded-pill registerButton">Update Profile</button>
	</form:form>
	</section>
	
	<div class="m-auto">
		<p class="fw-bold">Account Removal</p>
		<a class="btn btn-danger shadow" href="/profile/delete/${coaches.id}">Delete Coach Account</a>
	</div>
	
</body>
</html>