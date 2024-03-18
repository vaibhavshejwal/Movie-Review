<%@page import="in.co.movie.review.bean.GiveReview"%>
<%@page import="in.co.movie.review.bean.MoviesCategoryBean"%>
<%@page import="in.co.movie.review.Utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review List</title>
<style>
.testimonial-heading{
letter-spacing: 1px;
margin: 30px opx;
padding: 10px 20px;
display: flex;
flex-direction: column;
justify-content: center;
align-items: center;
}
.testimonial-heading h2{
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
	<br>
	<div class="container mt-5" style="position: relative; min-height: 71vh">
	<section id="testimonials">
<diV class="testimonial-heading">
	<h2>Review List</h2>
	</diV>
	</section>
	<br>
	<h5 style="color: red;" class="Text-center"><%=ServletUtility.getErrorMessage(request)%></h5>
	<h5 style="color: green;" class="Text-center"><%=ServletUtility.getSuccessMessage(request)%></h5>
	<form action="<%=MRView.GIVE_REVIEW_LIST_CTL%>" method="post">
		<table class="table table-striped table-info">
			<tr >
				<th scope="col" class="bg-dark text-white">Movie Name</th>
				<th scope="col" class="bg-dark text-white">Actor Name</th>
				<th scope="col" class="bg-dark text-white">Category</th>
				<th scope="col" class="bg-dark text-white">Comment</th>
				<th scope="col" class="bg-dark text-white">Rating</th>
				<%if(user.getRoleid()==1){
				%>
				<th scope="col" class="bg-dark text-white">EmailId</th>
				<th scope="col" class="bg-dark text-white">Action</th>
			<%}else{ %>
			<%
			}
		%>
			</tr>
			</tbody>
			<%
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					GiveReview bean = (GiveReview) it.next();
			%>
			<tr>
				<td><%=bean.getMovieName()%></td>
				<td><%=bean.getActorName()%></td>
				<td><%=bean.getCategory()%></td>
				<td><%=bean.getComment()%></td>
				<td><%=bean.getStar()%><i class="fa fa-star-half-o" style="color: orange; font-size: 20px;"></i></td>
				
				
				<%if(user.getRoleid()==1){
				%>
					<td><%=bean.getUserName()%></td>
				<td><a class="btn btn-danger" href="<%=MRView.GIVE_REVIEW_LIST_CTL%>?id=<%=bean.getId()%>"><i class="fa fa-trash" style='font-size:20px'></i></a></td>
			<%}else{ %>
			<%
			}
		%>
			</tr>
			<%
			}
		%>
			</tbody>
		</table>
	</form>
	</div>
<%@include file="Footer.jsp"%>
</body>
</html>