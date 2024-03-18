<%@page import="in.co.movie.review.Controller.UserListCtl"%>
<%@page import="in.co.movie.review.Utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User List</title>
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
	<div class="container mt-2" style="position: relative; min-height: 71vh">
	<section id="testimonials">
<diV class="testimonial-heading">
	<h2>User List</h2>
	</diV>
	</section>
	<form action="<%=MRView.USER_LIST_CTL%>" method="post">
		<table class="table table-striped" width="100%">
			<tr>
				<td align="center"><label >Email Id :</label> <input
					type="text" name="emailId" placeholder="Enter Email Id"
					value="<%=ServletUtility.getParameter("emailId", request)%>">
					&emsp;&emsp; <input type="submit" name="operation" 
					value="<%=UserListCtl.OP_SEARCH%>">&nbsp;&nbsp;&nbsp;<input type="submit"
					name="operation" value="<%=UserListCtl.OP_RESET%>"></td>
			</tr>
		</table>
		<br>
<h5 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h5>
	<h5 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h5>

		<table class="table table-striped table-info">
			<tr >
				<th scope="col" class="bg-dark text-white">Name</th>
				<th scope="col" class="bg-dark text-white">Email</th>
				<th scope="col" class="bg-dark text-white">Phone No</th>
				<th scope="col" class="bg-dark text-white">Gender</th>
				<th scope="col" class="bg-dark text-white">RoleName</th>
				<th scope="col" class="bg-dark text-white">Action</th>
					<th scope="col" class="bg-dark text-white"></th>
			</tr>
			<%
			
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					UserBean bean = (UserBean) it.next();
			%>
			<tr>
				
				<td><%=bean.getUserName()%></td>
				<td><%=bean.getEmailId()%></td>
				<td>+91 <%=bean.getPhoneNo()%></td>
				<td><%=bean.getGender()%></td>
			<%
					if (bean.getRoleName().equalsIgnoreCase("ADMIN")) {
				%>
				<td>-------</td>
				
				<%
					} else {
				%>
				<td><%=bean.getRoleName()%></td>
				
				<td><a class="btn btn-success"
					href="<%=MRView.USER_CTL%>?id=<%=bean.getId()%>"><i class="fa fa-pencil-square-o" style='font-size:20px'></i></a></td>
				<td><a class="btn btn-danger" href="<%=MRView.USER_LIST_CTL%>?id=<%=bean.getId()%>"><i class="fa fa-trash" style='font-size:20px'></i></a></td>
			</tr>
			<%
			}
		%>
		
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