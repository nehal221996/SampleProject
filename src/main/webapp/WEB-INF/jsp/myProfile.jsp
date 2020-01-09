<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

<title>Profile</title>

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="panel panel-default">
				<div class="text-success panel-heading">User Profile</div>
				<div class="panel-body">
					<div class="col-md-4"></div>
					<div class="col-md-4"></div>
					<table class="table">

						<ul>
							<li style="float: right"><button class="btn default"
									onclick="myFunction()">Download File</button></li>
						</ul>
						<h3>${msg}</h3>
						<tr>
							<td rowspan="8"><img style="height: 100px; width: 100px"
								src="data:image/png;base64,${profileImge}" /></td>
						</tr>

						<tr>
							<td>First Name:</td>
							<td>${user.firstname }</td>
						</tr>
						<tr>
							<td>Last Name:</td>
							<td>${user.lastname }</td>
						</tr>
						<tr>
							<td>Email:</td>
							<td>${user.email }</td>
						</tr>
						<tr>
							<td>Mobile:</td>
							<td>${user.mobile }</td>
						</tr>
						<tr>
							<td>Password:</td>
							<td>${user.password }</td>
						</tr>
						<tr>
							<td>Date Of Joining:</td>
							<td>${user.doj }</td>
						</tr>
						<tr>
							<td>Gender:</td>
							<td>${user.gender }</td>
						</tr>



						<tr>
							<td></td>
							<td><a href="update?uid=${user.id }">Update My Profile</a></td>
							<td><a href="resetpassword">Reset Password</a></td>

						</tr>

						<tr>
							<td></td>
							<td><button class=" btn btn-light""><a href="logout">LOGOUT</a></button></td>
						</tr>

					</table>
				</div>
			</div>
		</div>
	</div>



	<%-- 	<table align="center" >
	<h1 align="center"  class="text-success">User Detail</h1>
	
		<tr>
		<td rowspan="7"><img style="height:90px;width:70px" src="<c:url value="/images/${user.profile }" />" /></td>
		</tr>
		
		<tr>			
			<td>First Name: </td><td>${user.firstname }</td>
		</tr>
		<tr>
			<td>Last Name: </td><td>${user.lastname }</td>
		</tr>
		<tr>
			<td>Email: </td><td>${user.email }</td>
		</tr>
		<tr>
			<td>Mobile: </td><td>${user.mobile }</td>
		</tr>
		<tr>
			<td>Password: </td><td>${user.password }</td>
		</tr>
		<tr>
			<td>Gender: </td><td>${user.gender }</td>
		</tr>
		
		<tr>
			<td></td>
			<td><a href="update?uid=${user.id }">Update my profile...</a></td>
		</tr>
	</table>
	<h3 align="center"><a href="update?uid=${user.id }">Update my profile...</a></h3> --%>


	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script src="/resources/js/empdownload.js"></script>
</body>
</html>