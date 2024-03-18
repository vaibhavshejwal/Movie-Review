package in.co.movie.review.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.movie.review.Utility.ServletUtility;

@WebServlet(name = "ContactCtl" ,urlPatterns = "/contact")
public class ContactCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
    
    public ContactCtl() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	@Override
	protected String getView() {
		return MRView.CONTACT_VIEW;
	}

}
