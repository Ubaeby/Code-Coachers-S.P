<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<!-- This is the Nav -->
	<nav class="nav bg-dark d-flex justify-content-around">
		<div class="nav-item">
			<a class="text-decoration-none text-white" href="/home">Code
				Coachers</a>
		</div>
		<c:if test="${allUsers.coach == null}">
			<div class="nav-item">
				<a class="text-decoration-none text-white" href="/profile/new">Profile</a>
			</div>
		</c:if>
		<div class="nav-item">
			<a class="text-decoration-none text-white" href="/logout">Logout</a>
		</div>
	</nav>

	<div class="text-center cfy fw-bold">
		<h2>
			<c:out value="${coaches.user.name}" />
		</h2>
		<p>
			<c:out value="${coaches.lang}" />
		</p>
	</div>

<!-- This is the image div section -->
	<div class="separate d-flex">
		<div class="imgDiv bg-dark border-0 rounded shadow">
			<img class="img-fluid"
				src="/uploads/${number}.jpg" alt="art" />
			<c:if test="${allUsers.id == coaches.user.id}">
				<a
					class=" editLink text-decoration-none text-light fw-bold btn btn-secondary shadow"
					href="/profile/edit/${coaches.id}">Edit</a>
			</c:if>
		</div>

<!-- This is the middle section -->
		<section class="section4">
			<div class="col-6 m-auto rounded bg-dark text-white shadow" style="border: 1px solid black">
				<p class="text-center card bg-secondary text-dark fw-bold">About me!</p>
				<p class="text-wrap text-break"><c:out  value="${coaches.bio}" /></p>
				
			</div>
			<h3 class="text-center">Reviews</h3>
			<div class=" divScroll col-5 m-auto overflow-auto shadow fw-bold">
				<c:forEach var="r" items="${reviews}">
					<c:if test="${r.coach.id == coaches.id}">
						<div class="reviewHover border card">
							<section class="card-title text-dark">
								<!-- <img src="" alt="" /> -->
								<c:out value="${r.reviewer.name}"></c:out>
								<p class="fw-lighter fst-italic ">
									<fmt:formatDate value="${r.createdAt}" pattern="MMM d" />
								</p>
							</section>

							<p class="card-text">
								<c:out value="${r.message}"></c:out>
							</p>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</section>
		<section class="section5 bg-dark text-light shadow">
			<div style="background-color: ${coaches.color}">
				<p class=" card shadow fw-bold text-dark text-center">
					<c:out value="${coaches.user.name}" />
					charges $<c:out value="${coaches.price}" />
					per hour.
				</p>
				<button class="hireBut btn btn-light">Hire</button>
			</div>

			<form:form class="mb-4 " action="/profile/${coaches.id}"
				method="post" modelAttribute="review">
				<h4 class="text-center">Leave a review?</h4>
				<div class="form-div">
					<form:label class="form-label" path="message"></form:label>
					<form:textarea class="form-control" path="message" placeholder="Share your thoughts!" />
				</div>

				<button class="submitButton btn fw-bold rounded-pill">Submit</button>
				<div>
					<p class="text-danger text-center">
						<form:errors path="message"/>
					</p>
				</div>
			</form:form>
		</section>
	</div>
</body>
</html>