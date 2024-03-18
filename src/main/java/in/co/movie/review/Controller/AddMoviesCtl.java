package in.co.movie.review.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import in.co.movie.review.Exception.ApplicationException;
import in.co.movie.review.Exception.DuplicateRecordException;
import in.co.movie.review.Model.MoviesCategoryModel;
import in.co.movie.review.Model.MoviesModel;
import in.co.movie.review.Utility.DataUtility;
import in.co.movie.review.Utility.DataValidator;
import in.co.movie.review.Utility.PropertyReader;
import in.co.movie.review.Utility.ServletUtility;
import in.co.movie.review.bean.BaseBean;
import in.co.movie.review.bean.MoviesBean;

@MultipartConfig(fileSizeThreshold=1024*1024,maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
@WebServlet(name = "AddMoviesCtl" ,urlPatterns = "/Addmovies")
public class AddMoviesCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public AddMoviesCtl() {
        super();
    }
    @Override
   	protected void preload(HttpServletRequest request) {
       	MoviesCategoryModel model = new MoviesCategoryModel();
   			try {
   					List categorylist = model.list();
   					request.setAttribute("category", categorylist);
   			} catch (Exception e) {
   				e.printStackTrace();
   			}
   	}


    @Override
   	protected boolean validate(HttpServletRequest request) {
   		System.out.println("in validation");
   		boolean pass = true;
   		if (DataValidator.isNull(request.getParameter("MovieName"))) {
   			request.setAttribute("MovieName", PropertyReader.getvalue("error.require", "Movie Name"));
   			pass = false;
   		}
   		if (DataValidator.isNull(request.getParameter("actorName"))) {
   			request.setAttribute("actorName", PropertyReader.getvalue("error.require", "Actor Name"));
   			pass = false;
   		}
      	return pass;
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

    protected String SubImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String savePath = DataUtility.getString(PropertyReader.getvalue("imagePath"));

		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		Part part = request.getPart("image");
		String fileName = extractFileName(part);
		part.write(savePath + File.separator + fileName);
		String filePath = fileName;
		System.out.println("Path----" + savePath + File.separator + fileName);

		return fileName;
	}
	
	private String extractFileName(Part part) {
		try {
			String contentDisp = part.getHeader("content-disposition");
			String[] items = contentDisp.split(";");
			for (String s : items) {
				if (s.trim().startsWith("filename")) {
					return s.substring(s.indexOf("=") + 2, s.length() - 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MoviesModel model = new MoviesModel();
		System.out.println("in do post");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		MoviesBean bean = (MoviesBean) populateBean(request);
		bean.setImage(SubImage(request, response));
		if (bean.getId() > 0) {
			System.out.println("in do post2");
		//	long i = model.Update(bean);
			ServletUtility.setSuccessMessage("Movies details Updated Successfully", request);
		} else {
			try {
				long pk = model.add(bean);
				ServletUtility.setbean(bean, request);
				ServletUtility.setSuccessMessage("Movies details Successfully Added", request);
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
		return MRView.ADD_MOVIES_VIEW;
	}

}
