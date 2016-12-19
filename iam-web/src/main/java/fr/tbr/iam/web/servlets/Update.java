package fr.tbr.iam.web.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
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
import fr.tbr.iamcore.exception.DAOUpdateException;
import fr.tbr.iamcore.service.dao.IdentityDAOInterface;

/**
 * Servlet implementation class Update
 */

@WebServlet(name = "Update", urlPatterns = "/IdUpdate")
public class Update extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;

	
	@Inject
	IdentityDAOInterface dao;
	
	IAMLogger logger = IAMLogManager.getIAMLogger(IdentityServlet.class);

	/**
	 * Default constructor.
	 */
	public Update() {
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
	 *      Updating identities
	 *      By providing displayName, email, uid, birthDate
	 *      Parsing birthDate
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Identity identity = new Identity();
		
		String displayName = request.getParameter("displayName");
		String email = request.getParameter("email");
		String uid = request.getParameter("uid");
		String birthDate = request.getParameter("birthDate");
		
		String str_date=birthDate;
		DateFormat formatter ; 
		Date date ; 
		formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		
		try {
			date = formatter.parse(str_date);
			
			identity.setDisplayName(displayName);
			identity.setEmail(email);
			identity.setUid(uid);
			identity.setBirthDate(date);
			dao.update(identity);
			response.sendRedirect("success.jsp");
		} catch (ParseException | DAOUpdateException e) {
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