<%@page import="in.co.movie.review.Controller.MoviesListCtl"%>
<%@page import="in.co.movie.review.bean.MoviesBean"%>
<%@page import="in.co.movie.review.Utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.movie.review.Controller.MRView"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movies List</title>
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
<div class="container mt-5" style="position: relative; min-height: 71vh">
	<section id="testimonials">
<diV class="testimonial-heading">
	<h2>Movies List</h2>
	</diV>
	</section>
	
	<br>
	<h5 style="color: red;" class="Text-center"><%=ServletUtility.getErrorMessage(request)%></h5>
	<h5 style="color: green;" class="Text-center"><%=ServletUtility.getSuccessMessage(request)%></h5>
	<form action="<%=MRView.MOVIES_LIST_CTL%>" method="post">
	<table class="table table-striped" width="100%">
			<tr>
				<td align="center"><label >Movie :</label> <input
					type="text" name="MovieName" placeholder="Enter Movie Name"
					value="<%=ServletUtility.getParameter("MovieName", request)%>">
					&emsp;&emsp; <input type="submit" name="operation" 
					value="<%=MoviesListCtl.OP_SEARCH%>">&nbsp;&nbsp;&nbsp;<input type="submit"
					name="operation" value="<%=MoviesListCtl.OP_RESET%>"></td>
			</tr>
		</table>
		
		<table class="table table-striped table-info">
			<tr >
				<th scope="col" class="bg-dark text-white">Category Name</th>
				<th scope="col" class="bg-dark text-white">Movies Name</th>
				<th scope="col" class="bg-dark text-white">Actor Name</th>
				<th scope="col" class="bg-dark text-white">Release Date</th>
				<th scope="col" class="bg-dark text-white">Image</th>
				
				<%if(user.getRoleid()==1){
				%>
				<th scope="col" class="bg-dark text-white">Action</th>
			<%}else{ %>
			<th scope="col" class="bg-dark text-white">Give Review</th>
			<%
			}
		%>
				
			</tr>
			<%
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					MoviesBean bean = (MoviesBean) it.next();
			%>
			<tr>
				<td><%=bean.getCategory()%></td>
				<td><%=bean.getMovieName()%></td>
				<td><%=bean.getActorName()%></td>
				<td><%=bean.getRealeasedate()%></td>
				<td><img width="100px" height="100px" src="<%=MRView.APP_CONTEXT%>/image/<%=bean.getImage()%>"></td>
				<%if(user.getRoleid()==1){
				%>
				<!-- <td><a class="btn btn-success"
					href=""><i class="fa fa-pencil-square-o" style='font-size:20px'></i></a></td> -->
				<td><a class="btn btn-danger" href="<%=MRView.MOVIES_LIST_CTL%>?id=<%=bean.getId()%>"><i class="fa fa-trash" style='font-size:20px'></i></a></td>
			<%}else{ %>
	<td><a class="btn btn-success"
					href="<%=MRView.GIVE_REVIEW_CTL%>?id=<%=bean.getId()%>"><i class="fa fa-star-half-o" style='font-size:20px'></i></a></td>
			<%} %>
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