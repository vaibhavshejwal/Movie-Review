<%@page import="in.co.movie.review.Controller.CategoryCtl"%>
<%@page import="in.co.movie.review.Utility.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Movies Category</title>
</head>
<body>
<%@include file="Header.jsp"%>
	<div class="container mt-5"
		style="position: relative; min-height: 67vh">
	<h5 align="center" style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h5>
	<h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
	<form action="<%=MRView.ADD_CATEGORY_CTL%>" method="post">
     <h3 class="text-center mt-5">Add Movies Category</h3>
		<jsp:useBean id="bean" scope="request"
			class="in.co.movie.review.bean.MoviesCategoryBean" />

		<input type="hidden" name="id" value="<%=bean.getId()%>"> 
		

<div class="container mt-5">
		  <div class="row h-100 justify-content-center align-items-center">
			<div class="col-6">
				<label for="exampleInputEmail1" class="form-label">Movies Category Name</label> 
				<input type="text" name="CategoryName" class="form-control" placeholder="Enter Movies Category Name">
			</div>
			</div>
			</div>
			
			<div class="form-text text-center" style="color: red"><%=ServletUtility.getErrorMessage("CategoryName", request)%></div>

                              <div class="text-center mt-4">
									<input type="submit" class="btn btn-primary" name="operation"
										value="<%=CategoryCtl.OP_SUBMIT%>">
										</div>
						</form>
</div>
	<br>
	<%@include file="Footer.jsp"%>
</body>
</html>