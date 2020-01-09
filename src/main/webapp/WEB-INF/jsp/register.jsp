<%-- <%@include file="/resources/header/login_head.jsp"%>  --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<!-- validation -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Bootstrap CSS -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>

<title>Registration</title>


</head>

<body>
	<div class="container">
		<div class="row">
			<div class="panel panel-default">
				<div class="text-success panel-heading">Registration</div>
				<div class="panel-body">
					<div class="col-md-4"></div>
					<div class="col-md-4">

						<form:form id="demoForm" action="registerData" method="post"
							modelAttribute="user" enctype="multipart/form-data">
							<h3>${msg}</h3>
							<div class="form-row">

								<form:hidden path="id" />

								<div class="form-group">
									<form:label path="firstname">First Name</form:label>
									<form:input class="form-control" path="firstname"
										name="firstname" />
									<span id="firstnameerror" class="text-danger"></span>
								</div>

								<div class="form-group">
									<form:label path="lastname">Last Name</form:label>
									<form:input class="form-control" path="lastname"
										name="lastname" />
									<span id="lastnameerror" class="text-danger"></span>
								</div>

								<div class="form-group">
									<form:label path="email">Email</form:label>
									<form:input class="form-control" path="email" name="email" />
									<span id="emailerror" class="text-danger"></span>
								</div>

								<div class="form-group">
									<form:label path="mobile">Mobile</form:label>
									<form:input class="form-control" path="mobile" name="mobile" />
									<span id="mobileerror" class="text-danger"></span>
								</div>

								<div class="form-group">
									<form:label path="password">Password</form:label>
									<form:password class="form-control" path="password"
										name="password" />
									<span id="passworderror" class="text-danger"></span>
								</div>

								<div class="form-group">
									<form:label path="gender">Gender</form:label>
									<form:radiobutton path="gender" value="Male" label="Male"
										name="gender" />
									<form:radiobutton path="gender" value="Female" label="Female"
										name="gender" />
									<span id="gendererror" class="text-danger"></span>
								</div>

								<div class="form-group">
									<form:label path="doj">Date ofJoining</form:label>
									<form:input type="date" class="form-control" path="doj"
										name="doj" />
								</div>

								<div class="form-group">
									<form:label path="">select</form:label>
									<input class="form-control" type="file" path="profile"
										name="profile" id="profile" />
								</div>

								<div class="form-group">
									<input type="submit" class="form-control btn btn-primary"
										value="submit"> <a href="/user/index">Already have
										account? Login here</a>
								</div>
						</form:form>
						<button
							onclick=" window.location.href ='https://accounts.google.com/o/oauth2/v2/auth?redirect_uri=http://localhost:8080/callback&prompt=consent&response_type=code&client_id=732118873335-vcaolepnkn18gu13tlonpo6p1i5beotc.apps.googleusercontent.com&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fplus.login+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fplus.me+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&access_type=offline';"
							id="google">Sign up With Google</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>

<script type="text/javascript">
	$(function() {
		// Initialize form validation on the registration form.
		// It has the name attribute "registration"
		$("#demoForm")
				.validate(
						{
							// Specify validation rules
							rules : {
								firstname : "required",
								lastname : "required",
								email : {
									required : true,
									// Specify that email should be validated
									// by the built-in "email" rule
									email : true
								},
								mobile : "required",
								password : {
									required : true,
									minlength : 5
								},
								gender : "required",
								doj : "required"
							},
							// Specify validation error messages
							messages : {
								firstname : "Please enter your user name",
								lastname : "Please enter your last name",
								email : {
									required : "Please provide a password",
									email : "Please enter valid email address"
								},
								mobile : "Please enter your mobile number",
								password : {
									required : "Please provide a password",
									minlength : "Your password must be at least 5 characters long"
								},
								gender : "Please select your gender",
								doj : "Please enter your joining date"
							},
							// Make sure the form is submitted to the destination defined
							// in the "action" attribute of the form when valid
							submitHandler : function(form) {
								form.submit();
							}
						});
	});
</script>
</html>
