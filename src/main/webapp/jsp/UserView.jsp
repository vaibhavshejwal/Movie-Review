<%@page import="in.co.movie.review.Controller.UserCtl"%>
<%@page import="in.co.movie.review.Utility.DataUtility"%>
<%@page import="in.co.movie.review.Controller.RegistrationCtl"%>
<%@page import="in.co.movie.review.Utility.ServletUtility"%>
<%@page import="in.co.movie.review.Utility.HTMLUtility"%>
<%@page import="java.util.HashMap"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update</title>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
 
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
<div class="container mt-5">
<div class="card bg-light">
<div class="cardbody mx-auto">
    <h4 class="card-title mt-3 text-center">Update Your Data</h4>
     <p class="text-center">Update Your Data and Complete Your Changes !!</p>  
     <!-- <a href="#!" class="btn btn-block btn-danger">
        <i class="fab fa-google ml-5"></i>Update With GooGle
     </a> 
     <a href="#!" class="btn btn-block btn-primary">
        <i class="fab fa-facebook-f ml-5"></i>Update With FaceBook
     </a> 

     <p class="divider">
        <span>OR</span>
     </p> -->
<h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
<h6 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h6>
<!-- Form Start -->

						
<form action="<%=MRView.USER_CTL%>" method="post">
<jsp:useBean id="bean" scope="request"
						class="in.co.movie.review.bean.UserBean" />
					<input type="hidden" name="id" value="<%=bean.getId()%>">
    <!-- UserName -->
<div class="form-group input-group">
    <div class="input-group-prepend">
        <span class="input-group-text">
            <i class="fa fa-user"></i>
        </span>
    </div>
    <input type="text" class="form-control" name="userName" placeholder="Enter UserName" value="<%=DataUtility.getStringData(bean.getUserName())%>">
  </div>
  <!-- UserName End-->

  <!-- Email -->
  <div class="form-group input-group">
    <div class="input-group-prepend">
        <span class="input-group-text">
            <i class="fa fa-envelope"></i>
        </span>
    </div>
    <input type="email" class="form-control"  name="emailId"  id="exampleInputEmail1" aria-describedby="emailHelp"
    value="<%=DataUtility.getStringData(bean.getEmailId())%>"  placeholder="Enter Email">
  </div>
  <!-- Email End -->

  <!-- Phone -->
  <div class="form-group input-group">
    <div class="input-group-prepend">
        <span class="input-group-text">
            <i class="fa fa-phone"></i>
        </span>
    </div>
    <select class="custom-select" style="max-width: 80px;">
        <option>+91</option>
        <option>+92</option>
        <option>+93</option>
    </select>
    <input type="number" class="form-control"  name="phoneNo" placeholder="Enter Phone No" value="<%=DataUtility.getStringData(bean.getPhoneNo())%>">
  </div>
  <!-- Phone End -->

<!-- Gender Type Feild -->
<div class="form-group input-group">
    <div class="input-group-prepend">
        <span class="input-group-text">
            <i class="fa fa-building"></i>
        </span>
    </div>
    <%
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("Male", "Male");
						map.put("Female", "Female");
					%>

						<%=HTMLUtility.getList("gender", String.valueOf(bean.getGender()), map)%>
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("gender", request)%></div>
  </div>
<!-- Gender Type Feild End -->

<!-- Password -->
<div class="form-group input-group">
    <div class="input-group-prepend">
        <span class="input-group-text">
            <i class="fa fa-lock"></i>
        </span>
    </div>
    <input type="password" class="form-control" name="password" placeholder="Enter Password" value="<%=DataUtility.getStringData(bean.getPassword())%>">
  </div>
  <!-- Password End -->

  <!-- Repeat Password -->
<div class="form-group input-group">
    <div class="input-group-prepend">
        <span class="input-group-text">
            <i class="fa fa-unlock"></i>
        </span>
    </div>
    <input type="password" class="form-control" name="repeatpassword" placeholder="Enter Repeat Password" value="<%=DataUtility.getStringData(bean.getRepeatPassword())%>">
  </div>
  <!--Repeat Password End -->

<Input type="submit" class="btn btn-block btn-success" name="operation" value="<%=UserCtl.OP_UPDATE%>">

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