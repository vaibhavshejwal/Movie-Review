<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact</title>
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">

<script src='https://kit.fontawesome.com/a076d05399.js'
	crossorigin='anonymous'></script>
</head>
<body>
<%@include file="Header.jsp"%>

<div class="container">

		<div class="row">
			<div class="col-2"></div>
			<div class="col-8 mt-5" style="background-color: #e6ffff;">

				<form action="" method="post">


					
					<div class="container mt-5">
						<div class="row justify-content-center">
							<div class="col-md-4 mt-5 bg-light rounded">
								<h1 class="text-center font-weight-bold text-primary">Contact
									Us</h1>
								<hr class="bg-light">
								<h5 class="text-center text-success"></h5>


								<div class="form-group input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i class="fas fa-user"></i></span>
									</div>
									<input type="text" name="name" class="form-control"
										placeholder="Enter your name"
										value="">
									<div class="form-text" style="color: red"></div>
								</div>

								<div class="form-group input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i class="fas fa-user"></i></span>
									</div>
									<input type="email" name="email" class="form-control"
										placeholder="Enter your email"
										value="">
									<div class="form-text" style="color: red"></div>
								</div>
								<div class="form-group input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i class="fas fa-at"></i></span>
									</div>

									<input type="text" name="subject" class="form-control"
										placeholder="Enter subject"
										value="">
									<div class="form-text" style="color: red"></div>
								</div>
								<div class="form-group input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="fas fa-comment-alt"></i></span>
									</div>
									<textarea name="message" id="msg" class="form-control"
										placeholder="Write your message" cols="60" rows="2"
										value=""></textarea>
									<div class="form-text" style="color: red"></div>
								</div>
							</div>
						</div>
					</div>

<br>
					
<button type="submit" class="btn btn-block btn-success">Save</button>
				</form>
			</div>
		</div>

		<div class="col-2"></div>
	</div>



	<br>



	<%@include file="Footer.jsp"%>
</body>
</html>