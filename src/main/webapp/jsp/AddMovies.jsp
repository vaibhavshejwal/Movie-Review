<%@page import="in.co.movie.review.Controller.AddMoviesCtl"%>
<%@page import="in.co.movie.review.Utility.ServletUtility"%>
<%@page import="in.co.movie.review.Utility.HTMLUtility"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Movies</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css"> 
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"> $('.datepicker').datepicker();</script>
</head>
<body>

<%@include file="Header.jsp"%>
 <div class="container mt-5"
		style="position: relative; min-height: 67vh">
		<%
			List categorylist =(List)request.getAttribute("category");
		%>   
		<h2 class="text-center">Add Movies</h2>
		<h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
		<h6 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h6>
		<hr>
		<form action="<%=MRView.ADD_MOVIES_CTL%>" method="post" enctype='multipart/form-data'>

			<div class="row">
				<div class="col-2"></div>
				<div class="col-8">

					<jsp:useBean id="bean" scope="request"
						class="in.co.movie.review.bean.MoviesBean" />

					<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedby()%>"> <input type="hidden"
						name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
					<input type="hidden" name="modifiedDateTime"
						value="<%=bean.getModifieddatetime()%>">
						
						

                 <div class="mb-3">
						<label class="form-label">Movie Category</label> <select name="category" class="form-control">
						<%=HTMLUtility.getList("categoryId", String.valueOf(bean.getCategoryId()), categorylist)%>
						 </select>
					</div>

					<div class="mb-3">
						<label class="form-label">Movie Name</label> <input type="text"
							class="form-control" name="MovieName" placeholder="Enter Movie Name here...">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("MovieName", request)%></div>
					</div>

					<div class="mb-3">
						<label class="form-label">Actor Name</label> <input type="text"
							class="form-control" name="actorName"
							placeholder="Enter Actor Name here..."">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("actorName", request)%></div>
					</div>
					
					<label class="form-label">Release Date</label> <input type="text" class="form-control mb-4 datepicker" autocomplete="off"
				name="Releasedate" data-provide="datepicker" id="datepicker"  placeholder="Enter Release Date" required="required">
								
	<div class="mb-3">
			<label class="form-label">Select Movies Image</label>
               <input type="file" name="image" class="form-control" required="required">
			<%-- <img width="100px" height="100px" src="<%=MRView.APP_CONTEXT%>/image/<%=bean.getImage()%>"> --%>
			</div>		
										
					<%-- <div class="mb-3">
			<label class="form-label">Select Cab Image</label>
               <input type="file" name="image" class="form-control" required >
			<img width="100px" height="100px" src="<%=CRMView.APP_CONTEXT%>/image/<%=bean.getImage()%>">
			</div> --%>

<div class="container text-center">
					<input type="submit" class="btn btn-primary" name="operation"
						value="<%=AddMoviesCtl.OP_SUBMIT%>">
</div>
				</div>
				<div class="col-2"></div>

			</div>
		</form>
	</div>
	<br>
	<%@ include file="Footer.jsp"%>


</body>
<script type="text/javascript">
    $('.datepicker').datepicker({ 
        startDate: new Date()
    });
  
</script>
</html>