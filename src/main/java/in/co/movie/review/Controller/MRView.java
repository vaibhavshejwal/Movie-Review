package in.co.movie.review.Controller;

public interface MRView {
	
	public String APP_CONTEXT = "/MovieReview";
	public String PAGE_FOLDER = "/jsp";
	
	
	//Controller------------------------------
	public String WELCOME_CTL = APP_CONTEXT + "/welcome";
	public String LOGIN_CTL = APP_CONTEXT + "/login";
	public String USER_REGISTRATION_CTL = APP_CONTEXT +  "/register";
	public String USER_LIST_CTL = APP_CONTEXT +  "/userList";
	public String USER_CTL = APP_CONTEXT +  "/User";
	public String ABOUT_CTL = APP_CONTEXT + "/about";
	public String CONTACT_CTL = APP_CONTEXT + "/contact";
	public String ADD_CATEGORY_CTL = APP_CONTEXT + "/Addcategory";
	public String CATEGORY_LIST_CTL = APP_CONTEXT + "/CategoryList";
	public String ADD_MOVIES_CTL = APP_CONTEXT + "/Addmovies";
	public String MOVIES_LIST_CTL = APP_CONTEXT + "/MoviesList";
	public String GIVE_REVIEW_CTL = APP_CONTEXT + "/GiveReview";
	public String GIVE_REVIEW_LIST_CTL = APP_CONTEXT + "/GiveReviewList";


	//View-------------------------------------
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/RegistrationView.jsp";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
	public String ABOUT_VIEW = PAGE_FOLDER + "/About.jsp";
	public String CONTACT_VIEW = PAGE_FOLDER + "/Contact.jsp";
	public String ADD_CATEGORY_VIEW = PAGE_FOLDER + "/AddMoviesCategory.jsp";
	public String CATEGORY_LIST_VIEW = PAGE_FOLDER + "/MoviesCategoryList.jsp";
	public String ADD_MOVIES_VIEW = PAGE_FOLDER + "/AddMovies.jsp";
	public String MOVIES_LIST_VIEW = PAGE_FOLDER + "/MoviesList.jsp";
	public String GIVE_REVIEW_VIEW = PAGE_FOLDER + "/GiveReview.jsp";
	public String GIVE_REVIEW_LIST_VIEW = PAGE_FOLDER + "/GiveReviewList.jsp";

}
