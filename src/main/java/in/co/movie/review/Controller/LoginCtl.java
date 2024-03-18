package in.co.movie.review.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.movie.review.Model.UserModel;
import in.co.movie.review.Utility.DataUtility;
import in.co.movie.review.Utility.DataValidator;
import in.co.movie.review.Utility.PropertyReader;
import in.co.movie.review.Utility.ServletUtility;
import in.co.movie.review.bean.BaseBean;
import in.co.movie.review.bean.UserBean;

@WebServlet(name = "LoginCtl" ,urlPatterns = "/login")
public class LoginCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SINGIN = "SignIn";
	public static final String OP_SING_UP = "SignUp";
	public static final String OP_LOGOUT = "Logout";
	
    public LoginCtl() {
        super();
    }
    
    @Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		String op = request.getParameter("operation");

		if (OP_SING_UP.equalsIgnoreCase(op) || OP_LOGOUT.equalsIgnoreCase(op)) {
			return true;
		}
		if (DataValidator.isNull(request.getParameter("emailId"))) {
			request.setAttribute("emailId", PropertyReader.getvalue("error.require", "emailId Id"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getvalue("error.require", "Password"));
			pass = false;
		}
		return pass;
	}

	protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();
		
		bean.setEmailId(DataUtility.getString(request.getParameter("emailId")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		populateDTO(bean, request);
		return bean;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		HttpSession session = request.getSession(false);
		UserBean bean = (UserBean) populateBean(request);
		if (OP_LOGOUT.equalsIgnoreCase(op)) {
			session = request.getSession(false);
			session.invalidate();
			ServletUtility.setSuccessMessage("Logout Sucessfully !!", request);
			ServletUtility.forward(MRView.LOGIN_VIEW, request, response);
			return;
		}
		ServletUtility.setbean(bean, request);
		ServletUtility.forward(getView(), request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Do Post");
		HttpSession session = request.getSession(true);
		String op = DataUtility.getString(request.getParameter("operation"));
		UserModel model = new UserModel();
		if (OP_SINGIN.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);
			try {
				bean = model.Authenticate(bean.getEmailId(), bean.getPassword());
				if (bean != null) {
						session.setAttribute("user", bean);
						ServletUtility.setbean(bean, request);
						ServletUtility.redirect(MRView.WELCOME_CTL, request, response);
						return;
				} else {
					bean = (UserBean) populateBean(request);
					ServletUtility.setbean(bean, request);
					ServletUtility.setErrorMessage("Invalid Email Id and Password??", request);
					ServletUtility.forward(getView(), request, response);
				}
			} catch (Exception e) {
			}

		}
		ServletUtility.forward(getView(), request, response);
	}


	@Override
	protected String getView() {
		return MRView.LOGIN_VIEW;
	}

}
