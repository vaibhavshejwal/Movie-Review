<%@page import="in.co.movie.review.Controller.LoginCtl"%>
<%@page import="in.co.movie.review.Utility.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">
    <title>Sign In</title>
    <style>
        .divider{
            position: relative;
            text-align: center;
            margin: 15px 0px;
        }
    </style>
</head>
<body>
<%@include file="Header.jsp"%>

<div class="container mt-5 pt-5 align-items-cente" style="position: relative; min-height: 67vh">
<div class="card bg-light">
<div class="cardbody mx-auto">
    <h4 class="card-title mt-3 text-center">Sign In Form</h4>
     <p class="text-center">Please Put Your Email Id and Password !!</p>  
     <!-- <a href="#!" class="btn btn-block btn-danger">
        <i class="fab fa-google ml-5"></i>Login With GooGle
     </a> 
     <a href="#!" class="btn btn-block btn-primary">
        <i class="fab fa-facebook-f ml-5"></i>Login With FaceBook
     </a>  -->

     <!-- <p class="divider">
        <span>OR</span>
     </p> -->
<h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
<h6 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h6>
<!-- Form Start -->
<form action="<%=MRView.LOGIN_CTL%>" method="post">
  <!-- Email -->
  <div class="form-group input-group">
    <div class="input-group-prepend">
        <span class="input-group-text">
            <i class="fa fa-envelope"></i>
        </span>
    </div>
    <input type="email" class="form-control"  name="emailId" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Email">
  </div>
  <!-- Email End -->

<!-- Password -->
<div class="form-group input-group">
    <div class="input-group-prepend">
        <span class="input-group-text">
            <i class="fa fa-lock"></i>
        </span>
    </div>
    <input type="password" class="form-control" name="password" placeholder="Enter Password">
  </div>
  <!-- Password End -->

<Input type="submit" class="btn btn-block btn-success" name="operation" value="<%=LoginCtl.OP_SINGIN%>">
<%-- <p class="text-center mt-3">Reset Your Password ??
    <a href="<%=MRView.FORGET_PASSWORD_CTL%>">Forget Your Password</a>
</p> --%>

</form>
  <!-- Form End -->
</div>
</div>
</div>
<div class="mt-5">
<%@include file="Footer.jsp"%>
</div>
</body>
</html>