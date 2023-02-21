<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
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
<body>

<!-- Ghost kitty found on https://www.istockphoto.com/photo/3d-digital-illustration-of-cute-cartoon-ghost-cat-isolated-on-white-background-gm1198785880-342731464 -->
<!-- Artist by: Alina Makarenko Ig: alina3.art -->

	<div id="loginDiv">
		<section class="section1">
			<h1 class="fw-bold">Code Coachers</h1>
			<div>
				<img draggable="false" class="mainImage"
					src="./uploads/cat-blob-nobg.png" alt="cat" />
			</div>
			<p class="lorem text-white fw-bold">Lorem ipsum dolor sit amet</p>
		</section>

		<section class=" bg-dark section2 text-white">
			<div
				class="border rounded border-dark border-3 bg-dark text-white div1">
				<div class="header">
					<h2>Welcome Back!</h2>
					<h4 class="rainbow" >Hello, is it me you're looking for?</h4>
				</div>
				
				<form:form class="mb-3" action="/login" method="post"
					modelAttribute="newLogin">
					<div class="form-div">
						<form:label class="form-label" path="email">Email:</form:label>
						<form:errors class="text-danger"  path="email" />
						<form:input class="form-control" path="email" />
					</div>
					
					<div class="form-div">
						<form:label class="form-label" path="password">Password:</form:label>
						<form:errors class="text-danger"  path="password" />
						<form:input class="form-control" path="password" type="password" />
					</div>
					
					<button class="btn btn-primary rounded-pill registerButton">Log
						In</button>
				</form:form>
			</div>

			<p class="link">
				Don't have an account? <a class="text-decoration-none" href="/">Register</a>
			</p>
		</section>
	</div>


</body>
</html>