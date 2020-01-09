
<%-- <%@include file="/resources/header/login_head.jsp"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- Bootstrap CSS -->
<!-- Latest compiled and minified CSS -->
<!-- validation -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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




<title>Admin Login</title>


</head>
<body>

	<div class="container">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">Login</div>
				<div class="panel-body">
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<h3>${msg}</h3>
						<form:form id="demoForm" action="login" method="POST"
							modelAttribute="admin">
							<div class="form-row">
								<%-- <span>${msg}</span> --%>

								<div class=" form-group">
									<form:label path="uname">Enter UserName</form:label>
									<form:input class="form-control" path="uname" name="uname"
										value="" />
									<span id="unameerror" class="text-danger"></span>
								</div>

								<div class="form-group">
									<form:label path="pass">Enter Password</form:label>
									<form:input class="form-control" path="pass" name="pass" />
									<span id="passerror" class="text-danger"></span>
								</div>

								<div class="form-group">
									<input type="submit" class="form-control btn btn-primary"
										value="login">
								</div>

								<div class="form-group">
									<a href="register">Don't have account? Create an account</a>
								</div>

							</div>
						</form:form>
						<p id="" class="message">-----Or-----</p>
						<button
							onclick=" window.location.href ='https://accounts.google.com/o/oauth2/v2/auth?redirect_uri=http://localhost:8080/callback&prompt=consent&response_type=code&client_id=732118873335-vcaolepnkn18gu13tlonpo6p1i5beotc.apps.googleusercontent.com&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fplus.login+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fplus.me+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&access_type=offline';"
							id="google">Login With Google</button>

					</div>

				</div>
			</div>
		</div>
	</div>




	<!-- jQuery first, then Popper.js, then Bootstrap JS -->


	<script>
		$(function() {
			// Initialize form validation on the registration form.
			// It has the name attribute "registration"
			$("#demoForm")
					.validate(
							{
								// Specify validation rules
								rules : {

									uname : "required",
									pass : {
										required : true,
										minlength : 3
									}
								},
								// Specify validation error messages
								messages : {
									uname : "Please enter your user name",

									pass : {
										required : "Please provide a password",
										minlength : "Your password must be at least 5 characters long"
									}
								},
								// Make sure the form is submitted to the destination defined
								// in the "action" attribute of the form when valid
								submitHandler : function(form) {
									form.submit();
								}
							});
		});
	</script>


</body>
</html>








<%-- 
	<div class="container" style="background-image: url('images/bg-01.jpg');">
	<div class="row">
	<div class="col-md-6 m-auto">
	<!-- <p align="center" class="text-success">GridScape</p>   -->
	<h1 class="text-center text-success">GridScape</h1>
	
	
	
	<form:form action="adminLogin" method="POST" modelAttribute="admin">
	
		<div class="form-group">
		<form:label path="uname" >Username</form:label>
		<form:input path="uname" class="form-control" cssClass="error"/>
		</div>
		
	<table align="center">	
	
	
		<tr>	
			<td><form:label path="uname" >Username</form:label></td>
			<td><form:input path="uname" cssClass="error"/>
		</tr>
		
		<tr>
			<td><form:label path="pass">Password</form:label></td>
			<td><form:password path="pass" cssClass="error"/>
		</tr>
		
		<tr>
            <td></td>
            <td><input type="submit" class="btn btn-primary" value="login" ><a href="register">Click here for Registration</a></td>
        </tr> 
        
	</table>
	</form:form>
	<br>
	</div>
	</div>
	</div>
	<!-- <h5 align="center"><a href="register">Click here for Registration</a></h5> -->
	
	
	
	<div id="dropDownSelect1"></div>
 --%>

