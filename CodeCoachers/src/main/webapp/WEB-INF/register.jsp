<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register!</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/style.css" />
</head>
<body>

	<div id="registerDiv">
		<section class="section1">
			<h1 class="text-white fw-bold">Code Coachers</h1>
			<div>
				<img draggable="false" class="mainImage" src="./uploads/cat-blob-nobg.png" alt="cat" />
				<p class="lorem text-dark fw-bold" >Lorem ipsum dolor sit amet</p>
			</div>
			
		</section>
		<section class="bg-dark section2">
			<h2 class="header text-white">Welcome to Code Coachers</h2>

			<div class="border rounded border-dark border-3 bg-dark text-white div1">
				<form:form class="mb-3" action="/" method="post"
					modelAttribute="newUser">

					<div class="form-div">
						<form:label class="form-label" path="name">UserName</form:label>
						<form:errors class="text-danger" path="name" />
						<form:input class="form-control" path="name"
							placeholder="Please enter a user name" />
					</div>

					<div class="form-div">
						<form:label class="form-label" path="email">Email:</form:label>
						<form:errors class="text-danger"  path="email" />
						<form:input class="form-control" path="email"
							placeholder="Please enter your email" />
					</div>

					<div class="form-div">
						<form:label class="form-label" path="password">Password:</form:label>
						<form:errors class="text-danger"  path="password" />
						<form:input class="form-control" path="password" type="password" />
					</div>

					<div class="form-div">
						<form:label class="form-label" path="birthDate">Date of Birth</form:label>
						<form:errors class="text-danger"  path="birthDate" />
						<form:input class="form-control" type="date" path="birthDate" />
					</div>

					<form:label class="form-check-label" path="agree">
						<form:checkbox class="form-check-input" path="agree" />
						After checking, you have confirmed that you agree to the Terms of Service and Privacy Policy
					</form:label>


					<button class="btn btn-primary rounded-pill registerButton">Register</button>
				</form:form>

				<a class="text-decoration-none link" href="/login">Already have an account?</a>
			</div>

		</section>
	</div>


</body>
</html>