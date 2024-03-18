package in.co.movie.review.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.movie.review.Exception.ApplicationException;
import in.co.movie.review.Exception.DuplicateRecordException;
import in.co.movie.review.Model.UserModel;
import in.co.movie.review.Utility.DataUtility;
import in.co.movie.review.Utility.DataValidator;
import in.co.movie.review.Utility.PropertyReader;
import in.co.movie.review.Utility.ServletUtility;
import in.co.movie.review.bean.BaseBean;
import in.co.movie.review.bean.UserBean;
@WebServlet(name = "RegistrationCtl" ,urlPatterns = "/register")
public class RegistrationCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
  
    public RegistrationCtl() {
        super();
    }

    @Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("in validation");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("userName"))) {
			request.setAttribute("userName", PropertyReader.getvalue("error.require", "User Name"));
			pass = false;

		} else if (!DataValidator.isName(request.getParameter("userName"))) {
			request.setAttribute("userName", PropertyReader.getvalue("error.name", "User Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("emailId"))) {
			request.setAttribute("emailId", PropertyReader.getvalue("error.require", "Email Id"));
			pass = false;

		} else if (!DataValidator.isEmail(request.getParameter("emailId"))) {
			request.setAttribute("emailId", PropertyReader.getvalue("error.login", "Email Id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getvalue("error.require", "Password"));
			pass = false;
		}
		else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getvalue("error.password", "Password"));
			return false;
		}

		if (DataValidator.isNull(request.getParameter("repeatpassword"))) {
			request.setAttribute("repeatpassword", PropertyReader.getvalue("error.require", "Repeat Password"));
			pass = false;
		}
		else if (!DataValidator.isPassword(request.getParameter("repeatpassword"))) {
			request.setAttribute("repeatpassword", PropertyReader.getvalue("error.password", "Repeat Password"));
			return false;
		}
		
		if(DataValidator.isNull(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", PropertyReader.getvalue("error.require", "Phone No"));
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getvalue("error.require", "Gender"));
			pass = false;
		}
		return pass;
	}
    
    protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setUserName(DataUtility.getString(request.getParameter("userName")));
		bean.setEmailId(DataUtility.getString(request.getParameter("emailId")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setRepeatPassword(DataUtility.getString(request.getParameter("repeatpassword")));
		bean.setPhoneNo(DataUtility.getLong(request.getParameter("phoneNo")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setRoleid(2);
		bean.setRoleName("User");
		populateDTO(bean, request);
		return bean;

	}
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ServletUtility.forward(getView(), request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModel model = new UserModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		UserBean bean = new UserBean();
		bean = (UserBean) populateBean(request);
		if (OP_SUBMIT.equalsIgnoreCase(op)) {
			bean = (UserBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setbean(bean, request);
				ServletUtility.setSuccessMessage("User Successfully Registered !!", request);
				ServletUtility.forward(getView(), request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setbean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.forward(getView(), request, response);
		}
	}


	@Override
	protected String getView() {
		return MRView.USER_REGISTRATION_VIEW;
	}

}
