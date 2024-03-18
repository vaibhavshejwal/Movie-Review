<%@page import="in.co.movie.review.Controller.GiveReviewCtl"%>
<%@page import="in.co.movie.review.bean.MoviesBean"%>
<%@page import="in.co.movie.review.Utility.ServletUtility"%>
<%@page import="in.co.movie.review.Utility.DataUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.movie.review.Controller.MRView"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name = "viewport" content="width=device-width, initial-scale=1.0"> 
<title>Give Review</title>
    <script src = "https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.js"> </script>  
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>  
<style>
.testimonial-heading {
	letter-spacing: 1px;
	margin: 30px opx;
	padding: 10px 20px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}  
.testimonial-heading h2 {
	font-size: 2.2rem;
	font-weight: 500;
	background-color: #202020;
	color: #ffffff;
	padding: 10px 20px;
}
</style>
</head>
<body>
	<%@include file="Header.jsp"%>
	<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
	<div class="container mt-5"
		style="position: relative; min-height: 71vh">
		<section id="testimonials">
			<diV class="testimonial-heading">
				<h2>Give Review</h2>
				<h5 style="color: red;" class="Text-center"><%=ServletUtility.getErrorMessage(request)%></h5>
				<h5 style="color: green;" class="Text-center"><%=ServletUtility.getSuccessMessage(request)%></h5>
				<form action="<%=MRView.GIVE_REVIEW_CTL%>" method="post">
					<jsp:useBean id="bean" scope="request"
						class="in.co.movie.review.bean.GiveReview" />
					<input type="hidden" name="id" value="<%=bean.getId()%>">
					<input type="hidden" name="MovieId" value="<%=DataUtility.getStringData(bean.getMovieName())%>">
					<input type="hidden" name="actorId" value="<%=DataUtility.getStringData(bean.getActorName())%>">
					<input type="hidden" name="category" value="<%=DataUtility.getStringData(bean.getCategory())%>">
					<div class="container text-center mt-2">
						<div>
							<div class="card">
								<div class="card-body text-center">
									<%-- <img src="<%=MRView.APP_CONTEXT%>/image/<%=bean.getImage()%>"
										alt="RRR" class="img-fluid rounded-circle mx-auto"
										style="max-width: 150px; height: 150px;"> --%>
										
									<h4>
										Movie Name :- <%=bean.getMovieName()%></h4>
									<h5>
										Actor Name :- <%=bean.getActorName()%></h5>
									<h6>
										Category :- <%=bean.getCategory()%></h6>
									<br>
									<div class="mb-3">
										<label class="form-label">Give Review </label>
										<textarea rows="3" cols="3" name="Comment"
											placeholder="Enter Comment" class="form-control"></textarea>
										<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("Comment", request)%></div>
									</div>

									<!-- Review -->
    <div class="mb-3">
    <label class="form-label">Give Rating</label>
            <div class="ratings">
            <input type="radio" name="rating" value="1" class = "fa fa-star" aria-hidden = "true" id = "st1" required="required">
            <input type="radio" name="rating" value="2" class = "fa fa-star" aria-hidden = "true" id = "st2" required="required">
            <input type="radio" name="rating" value="3" class = "fa fa-star" aria-hidden = "true" id = "st3" required="required">
            <input type="radio" name="rating" value="4" class = "fa fa-star" aria-hidden = "true" id = "st4" required="required">
            <input type="radio" name="rating" value="5" class = "fa fa-star" aria-hidden = "true" id = "st5" required="required">
            </div>
</div>



									<div class="container">
										<Input type="submit" class="btn btn-block btn-success" name="operation" value="<%=GiveReviewCtl.OP_SUBMIT%>">
									</div>
								</div>
							</div>
						</div>
						<div></div>
					</div>
				</form>
			</diV>
		</section>
	</div>
	<br>
	<%@include file="Footer.jsp"%>
</body>
<script>
$(document).ready(function() {  
    $("#st1").click(function() {  
        $(".fa-star").css("color", "black");  
        $("#st1").css("color", "yellow");  

    });  
    $("#st2").click(function() {  
        $(".fa-star").css("color", "black");  
        $("#st1, #st2").css("color", "yellow");  

    });  
    $("#st3").click(function() {  
        $(".fa-star").css("color", "black")  
        $("#st1, #st2, #st3").css("color", "yellow");  

    });  
    $("#st4").click(function() {  
        $(".fa-star").css("color", "black");  
        $("#st1, #st2, #st3, #st4").css("color", "yellow");  

    });  
    $("#st5").click(function() {  
        $(".fa-star").css("color", "black");  
        $("#st1, #st2, #st3, #st4, #st5").css("color", "yellow");  

    });  
  }); 
</script>
</html>