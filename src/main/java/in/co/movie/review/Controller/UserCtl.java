package in.co.movie.review.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.movie.review.Model.UserModel;
import in.co.movie.review.Utility.DataUtility;
import in.co.movie.review.Utility.ServletUtility;
import in.co.movie.review.bean.BaseBean;
import in.co.movie.review.bean.UserBean;

@WebServlet(name = "UserCtl" ,urlPatterns = "/User")
public class UserCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_UPDATE = "Update";
	
    public UserCtl() {
        super();
    }

	@Override
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
		UserModel model = new UserModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			UserBean bean = null;
			try {
				bean = model.findByPk(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.setbean(bean, request);
		}
		ServletUtility.forward(getView(), request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModel model = new UserModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		UserBean bean = new UserBean();
		bean = (UserBean) populateBean(request);
	
		if (id>0) {
			long i = model.Update(bean);
			ServletUtility.setSuccessMessage("Data Updated Successfully", request);
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return MRView.USER_VIEW;
	}

}
