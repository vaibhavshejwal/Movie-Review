package in.co.movie.review.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.movie.review.Exception.ApplicationException;
import in.co.movie.review.Exception.DuplicateRecordException;
import in.co.movie.review.Model.MoviesCategoryModel;
import in.co.movie.review.Utility.DataUtility;
import in.co.movie.review.Utility.DataValidator;
import in.co.movie.review.Utility.PropertyReader;
import in.co.movie.review.Utility.ServletUtility;
import in.co.movie.review.bean.BaseBean;
import in.co.movie.review.bean.MoviesCategoryBean;

@WebServlet(name = "CategoryCtl" ,urlPatterns = "/Addcategory")
public class CategoryCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public CategoryCtl() {
        super();
    }
    
  
    @Override
   	protected boolean validate(HttpServletRequest request) {
   		System.out.println("in validation");
   		boolean pass = true;

   		if (DataValidator.isNull(request.getParameter("CategoryName"))) {
   			request.setAttribute("CategoryName", PropertyReader.getvalue("error.require", "Category Name"));
   			pass = false;
   		}
   	return pass;
   }

    protected BaseBean populateBean(HttpServletRequest request) {

		MoviesCategoryBean bean = new MoviesCategoryBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCategoryName(DataUtility.getString(request.getParameter("CategoryName")));
		//bean.setMovieId(DataUtility.getLong(request.getParameter("movieid")));
		populateDTO(bean, request);
		return bean;

	}

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MoviesCategoryModel model = new MoviesCategoryModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		MoviesCategoryBean bean = new MoviesCategoryBean();
		if (OP_SUBMIT.equalsIgnoreCase(op)) {
			bean = (MoviesCategoryBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setbean(bean, request);
				ServletUtility.setSuccessMessage("Movies Category Successfully Add !!", request);
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
		return MRView.ADD_CATEGORY_VIEW;
	}

}
