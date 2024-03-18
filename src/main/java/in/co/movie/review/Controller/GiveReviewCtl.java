package in.co.movie.review.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.movie.review.Exception.ApplicationException;
import in.co.movie.review.Exception.DuplicateRecordException;
import in.co.movie.review.Model.GiveReviewModel;
import in.co.movie.review.Utility.DataUtility;
import in.co.movie.review.Utility.DataValidator;
import in.co.movie.review.Utility.PropertyReader;
import in.co.movie.review.Utility.ServletUtility;
import in.co.movie.review.bean.BaseBean;
import in.co.movie.review.bean.GiveReview;
import in.co.movie.review.bean.UserBean;

@WebServlet(name = "GiveReviewCtl" ,urlPatterns = "/GiveReview")
public class GiveReviewCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
   
    public GiveReviewCtl() {
        super();
    }

    @Override
   	protected boolean validate(HttpServletRequest request) {
   		System.out.println("in validation");
   		boolean pass = true;

   		if (DataValidator.isNull(request.getParameter("Comment"))) {
   			request.setAttribute("Comment", PropertyReader.getvalue("error.require", "Comment"));
   			pass = false;
   		}
   	return pass;
   }

    
    protected BaseBean populateBean(HttpServletRequest request) {

    	GiveReview bean = new GiveReview();
  		bean.setId(DataUtility.getLong(request.getParameter("id")));
  		bean.setMovieName(DataUtility.getString(request.getParameter("MovieId")));
  		bean.setActorName(DataUtility.getString(request.getParameter("actorId")));
  		bean.setCategory(DataUtility.getString(request.getParameter("category")));
  		bean.setComment(DataUtility.getString(request.getParameter("Comment")));
  		bean.setStar(DataUtility.getLong(request.getParameter("rating")));
  		HttpSession session = request.getSession(false);
		UserBean existBean = (UserBean)session.getAttribute("user");
		Long userId = existBean.getId();
		bean.setUserId(userId);
  		populateDTO(bean, request);
  		return bean;
  	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GiveReviewModel model = new GiveReviewModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			GiveReview bean = null;
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
		GiveReviewModel model = new GiveReviewModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		GiveReview bean = new GiveReview();
		bean = (GiveReview) populateBean(request);
		if (OP_SUBMIT.equalsIgnoreCase(op)) {
			bean = (GiveReview) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setbean(bean, request);
				ServletUtility.setSuccessMessage("Give Review  Successfully !!", request);
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
		return MRView.GIVE_REVIEW_VIEW;
	}


}
