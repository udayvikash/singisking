package fr.tbr.iam.web.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.tbr.iam.log.IAMLogger;
import fr.tbr.iam.log.impl.IAMLogManager;
import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.exception.DAOInitializationException;
import fr.tbr.iamcore.exception.DAOSaveException;
import fr.tbr.iamcore.service.dao.IdentityFileDAO;

/**
 * Servlet implementation class Login
 */

@WebServlet(name = "IdentityServlet", urlPatterns = "/IdAction")
public class IdentityServlet extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;

	IAMLogger logger = IAMLogManager.getIAMLogger(IdentityServlet.class);

	/**
	 * Default constructor.
	 */
	public IdentityServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String displayName = request.getParameter("displayName");
		String email = request.getParameter("email");
		String birthDate = request.getParameter("birthDate");
		String uid = request.getParameter("uid");

		try {
			IdentityFileDAO dao = new IdentityFileDAO("/test/identities");
			dao.save(new Identity(displayName, email, uid));
		} catch (DAOInitializationException | DAOSaveException e) {
			// TODO Redirect to error page
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());

	}

}
