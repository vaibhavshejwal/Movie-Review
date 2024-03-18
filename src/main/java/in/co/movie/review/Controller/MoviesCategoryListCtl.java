package in.co.movie.review.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.movie.review.Model.MoviesCategoryModel;
import in.co.movie.review.Utility.DataUtility;
import in.co.movie.review.Utility.ServletUtility;
import in.co.movie.review.bean.BaseBean;
import in.co.movie.review.bean.MoviesCategoryBean;

@WebServlet(name = "MoviesCategoryListCtl" ,urlPatterns = "/CategoryList")
public class MoviesCategoryListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public MoviesCategoryListCtl() {
        super();
    }

    
    protected BaseBean populateBean(HttpServletRequest request) {

		MoviesCategoryBean bean = new MoviesCategoryBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCategoryName(DataUtility.getString(request.getParameter("CategoryName")));
		bean.setMovieId(DataUtility.getLong(request.getParameter("movieid")));
		populateDTO(bean, request);
		return bean;

	}

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MoviesCategoryModel model = new MoviesCategoryModel();
		MoviesCategoryBean bean = null;
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			model.delete(id);
			ServletUtility.setSuccessMessage("Movies Category Deleted Successfully !!", request);
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
			}

	@Override
	protected String getView() {
		return MRView.CATEGORY_LIST_VIEW;
	}

}
