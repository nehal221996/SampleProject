<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SignUpPAge</title>
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

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="panel panel-default">
				<div class="text-success panel-heading">Register Here</div>
				<div class="panel-body">
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<form action="/user/Form" method="post" id="" class="">

							<div class="form-group">
								<input type="hidden" name="google_id" id="" value="${google_id}" />
							</div>

							<div class="form-group">
								<label>FirstName</label> <input class="form-control"
									name="firstname" value="${firstname}" />
							</div>
							<div class="form-group">
								<label>LastName</label> <input class="form-control"
									name="lastname" value="${lastname}" />
							</div>
							<div class="form-group">
								<label>email</label> <input class="form-control" name="email"
									value="${email}" />
							</div>
							<div class="form-group">
								<label>mobile</label> <input class="form-control" name="mobile"
									value="${mobile}" />
							</div>
							<div class="form-group">
								<label>password</label> <input class="form-control"
									name="password" value="${password}" />
							</div>
							<div class="form-group">
								<label>Gender</label> <input type="radio" name="gender"
									value="male"> Male <input type="radio" name="gender"
									value="female"> Female<br>
							</div>
							<div class="form-group">
								<label>Date Of Joining</label> <input type="date"
									class="form-control" name="doj" />
							</div>
							<div class="form-group">
								<label>select</label> <input class="form-control" type="file"
									path="profile" name="profile" id="profile" />
							</div>


							<div class="form-group">
								<input type="submit" class="form-control btn btn-primary"
									value="submit">
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>