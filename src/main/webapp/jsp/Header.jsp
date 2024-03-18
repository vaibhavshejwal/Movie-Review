<%@page import="in.co.movie.review.Controller.LoginCtl"%>
<%@page import="in.co.movie.review.Controller.MRView"%>
<%@page import="in.co.movie.review.bean.UserBean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/owl.carousel.min.css">
<link rel="stylesheet" href="./css/owl.theme.default.min.css">
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script></head>
<link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Barlow:wght@200&family=Old+Standard+TT:ital@1&family=Orbitron:wght@700&family=Palanquin:wght@300&family=Roboto:ital,wght@0,400;1,100&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.6.1.min.js" 
    integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script> 

       <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" 
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
	crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    
    <script src="./js/owl.carousel.min.js"></script> 
<script src="./js/wow.in.js"></script>
    <script>
        $(document).ready(function(){
  $(".owl-carousel").owlCarousel(
    {
        loop:true,
    margin:10,
        responsive:{
        0:{
            items:1,
            nav:true
        },
        600:{
            items:3,
            nav:false
        },
        1000:{
            items:3,
            nav:true,
            loop:false
        }
    }
    }
  );
});
new WOW().init();
    </script>
    

    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    <script>
      AOS.init();
    </script>



</head>
<body>
<%
		UserBean userBean = (UserBean) session.getAttribute("user");
	%>
	<%
		boolean userLoggedIn = userBean != null;

		String welcomeMsg = "Hello, ";

		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
			welcomeMsg += userBean.getRoleName();
		} else {
			welcomeMsg += "Guest";
		}
	%>

<!-- header start -->
<nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <div class="container">
        <a class="navbar-brand text-white" href="<%=MRView.WELCOME_CTL%>">Movie Review and Rating</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
     <ul class="navbar-nav ml-auto">
        
       
                <%
					if (userBean != null) {
				%>
     
               <%
					if (userBean.getRoleid() == 1) {
				%>
     <li class="nav-item">
          <a class="nav-link text-white" href="<%=MRView.USER_LIST_CTL%>">User List</a>
        </li>
      <li class="nav-item">
          <a class="nav-link text-white" href="<%=MRView.ADD_CATEGORY_CTL%>">Category Add</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="<%=MRView.CATEGORY_LIST_CTL%>">Category List</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="<%=MRView.ADD_MOVIES_CTL%>">Add Movies</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="<%=MRView.MOVIES_LIST_CTL%>">Movies List</a>
        </li>

        <li class="nav-item">
          <a class="nav-link text-white" href="<%=MRView.GIVE_REVIEW_LIST_CTL%>">View Reviews</a>
        </li>
           
      <li class="nav-item">
          <a class="nav-link text-white" href="<%=MRView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOGOUT%>">Logout</a>
        </li>
               <%
					} else if (userBean.getRoleid() == 2) {
				%>

         <li class="nav-item">
          <a class="nav-link text-white" href="<%=MRView.MOVIES_LIST_CTL%>">Movies List</a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link text-white" href="<%=MRView.GIVE_REVIEW_LIST_CTL%>">Show Reviews</a>
        </li>



 <li class="nav-item">
          <a class="nav-link text-white" href="<%=MRView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOGOUT%>">Logout</a>
        </li>

      <%
					}
				%>
				<%
					}else{
				%>
				<li class="nav-item active">
          <a class="nav-link text-white" href="<%=MRView.WELCOME_CTL%>">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-white" href="<%=MRView.ABOUT_CTL%>">About</a>
          </li>
        <%-- <li class="nav-item">
          <a class="nav-link text-white" href="<%=MRView.CONTACT_CTL%>">Contact</a>
        </li> --%>
				 <li class="nav-item">
          <a class="nav-link text-white" href="<%=MRView.LOGIN_CTL%>">Sign In</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="<%=MRView.USER_REGISTRATION_CTL%>">Sign Up</a>
        </li>
				<% }%>
       </ul>
    </div>
    </div>
    
  </nav>
<!-- Header End -->

<!-- Header Section -->

</body>
</html>