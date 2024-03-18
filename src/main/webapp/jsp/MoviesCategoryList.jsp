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
<title>Movies Category List</title>
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
	<br>
	<div class="container mt-5" style="position: relative; min-height: 71vh">
	<section id="testimonials">
<diV class="testimonial-heading">
	<h2>Movies Category List</h2>
	</diV>
	</section>
	<br>
	<h5 style="color: red;" class="Text-center"><%=ServletUtility.getErrorMessage(request)%></h5>
	<h5 style="color: green;" class="Text-center"><%=ServletUtility.getSuccessMessage(request)%></h5>
	<form action="<%=MRView.CATEGORY_LIST_CTL%>" method="post">
		<table class="table table-striped table-info">
			<tr >
				<th scope="col" class="bg-dark text-white">Category Name</th>
				<th scope="col" class="bg-dark text-white">Action</th>
			</tr>
			</tbody>
			<%
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					MoviesCategoryBean bean = (MoviesCategoryBean) it.next();
			%>
			<tr>
				<td><%=bean.getCategoryName()%></td>
				<td><a class="btn btn-danger" href="<%=MRView.CATEGORY_LIST_CTL%>?id=<%=bean.getId()%>"><i class="fa fa-trash" style='font-size:20px'></i></a></td>
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