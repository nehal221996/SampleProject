<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


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



<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="panel panel-default">
				<div class="text-success panel-heading">Reset Password</div>
				<div class="panel-body">
					<div class="col-md-4"></div>
					<div class="col-md-4">

						<h3>Details</h3>
						<form action="updatePassword" method="POST" id="demoForm">

							<!-- <div class="form-group">
						<label>Old Password</label>
						 <input class="form-control" type="password" name="pass_old" id="password_old">	
						<div id="passwordold_error" style="color: red;"></div>
						</div> -->

							<br>
							<div class="form-group">
								<label>Password</label> <input class="form-control"
									type="password" name="pass" id="password">
								<div id="password_error" style="color: red;"></div>
							</div>

							<div class="form-group">
								<br> <label>Confirm Password</label> <input
									class="form-control" type="password" id="con_password"
									name="con_pass">
							</div>
							<br> <input class="" type="submit" name="submit"
								value="reset" id="submit">
					
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		$(function() {
			// Initialize form validation on the registration form.
			// It has the name attribute "registration"
			$("#demoForm")
					.validate(
							{
								// Specify validation rules
								rules : {

									pass_old : {
										required : true,
										minlength : 3
									},
									pass : {
										required : true,
										minlength : 3
									},
									con_pass : {
										required : true,
										minlength : 3
									}
								},
								// Specify validation error messages
								messages : {
									pass_old : {
										required : "Please provide old password",
										minlength : "Your password must be at least 5 characters long"
									},
									pass : {
										required : "Please provide new password",
										minlength : "Your password must be at least 5 characters long"
									},
									con_pass : {
										required : "Please confirm your password",
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