package in.co.movie.review.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.movie.review.Model.MoviesModel;
import in.co.movie.review.Utility.DataUtility;
import in.co.movie.review.Utility.ServletUtility;
import in.co.movie.review.bean.BaseBean;
import in.co.movie.review.bean.MoviesBean;

@WebServlet(name = "MoviesListCtl" ,urlPatterns = "/MoviesList")
public class MoviesListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SEARCH = "search";
	
    public MoviesListCtl() {
        super();
    }
    
    protected BaseBean populateBean(HttpServletRequest request) {

  		MoviesBean bean = new MoviesBean();
  		bean.setId(DataUtility.getLong(request.getParameter("id")));
  		bean.setCategoryId(DataUtility.getLong(request.getParameter("category")));
  		bean.setMovieName(DataUtility.getString(request.getParameter("MovieName")));
  		bean.setActorName(DataUtility.getString(request.getParameter("actorName")));
  		bean.setRealeasedate(DataUtility.getDate(request.getParameter("Releasedate")));
  		populateDTO(bean, request);
  		return bean;
  	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MoviesModel model = new MoviesModel();
		MoviesBean bean = null;
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			model.delete(id);
			ServletUtility.setSuccessMessage("Movies Deleted Successfully !!", request);
		}
		List list = null;
		try {
			System.out.println("in do get");
			list = model.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list == null && list.size() == 0) {
			ServletUtility.setErrorMessage("No record found", request);
		}
		ServletUtility.setList(list, request);
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		MoviesModel model = new MoviesModel();
		MoviesBean bean = new MoviesBean();
		bean = (MoviesBean) populateBean(request);
		List list = null;

		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(MRView.MOVIES_LIST_CTL, request, response);
			return;

		}
		if (OP_SEARCH.equalsIgnoreCase(op)) {
			try {
				System.out.println("In Post");
				list = model.search(bean);
				ServletUtility.setList(list, request);
				ServletUtility.setbean(bean, request);

			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.forward(getView(), request, response);
		}
	
	
	}

	@Override
	protected String getView() {
		return MRView.MOVIES_LIST_VIEW;
	}

}
