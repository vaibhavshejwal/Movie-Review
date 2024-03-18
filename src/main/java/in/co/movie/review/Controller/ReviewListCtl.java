package in.co.movie.review.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.movie.review.Model.GiveReviewModel;
import in.co.movie.review.Utility.DataUtility;
import in.co.movie.review.Utility.ServletUtility;
import in.co.movie.review.bean.BaseBean;
import in.co.movie.review.bean.GiveReview;
import in.co.movie.review.bean.UserBean;

@WebServlet(name = "ReviewListCtl", urlPatterns = "/GiveReviewList")
public class ReviewListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	public ReviewListCtl() {
		super();
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
		UserBean existBean = (UserBean) session.getAttribute("user");
		Long userId = existBean.getId();
		bean.setUserId(userId);
		populateDTO(bean, request);
		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GiveReviewModel model = new GiveReviewModel();
		GiveReview bean = new GiveReview();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			model.delete(id);
			System.out.println("Delete");
			ServletUtility.setSuccessMessage("Review Deleted Successfully", request);
		}
		List list = null;
		HttpSession session = request.getSession(false);
		UserBean bean2 = (UserBean) session.getAttribute("user");
		long roleid = bean2.getRoleid();
		if (roleid == 2) {
			try {
				list = model.Showlist(bean2.getId());
				ServletUtility.setList(list, request);
			} catch (Exception e) {
			}
		} else {
			try {
				list = model.list();
				ServletUtility.setList(list, request);
			} catch (Exception e) {
			}
			
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected String getView() {
		return MRView.GIVE_REVIEW_LIST_VIEW;
	}

}
