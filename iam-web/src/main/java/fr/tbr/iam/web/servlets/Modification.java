package fr.tbr.iam.web.servlets;

import java.io.IOException;
import java.util.Collection;

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
import fr.tbr.iamcore.exception.DAOSearchException;
import fr.tbr.iamcore.service.dao.DAODeleteException;
import fr.tbr.iamcore.service.dao.IdentityDAOInterface;

/**
 * Servlet implementation class Modification
 */

@WebServlet(name = "Modification", urlPatterns = "/IdModify")
public class Modification extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;
		
	@Inject
	IdentityDAOInterface dao;
	
	IAMLogger logger = IAMLogManager.getIAMLogger(IdentityServlet.class);

	/**
	 * Default constructor.
	 */
	public Modification() {
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
	 *      getting user selection parameter
	 *      passing to Update form
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String val= request.getParameter("selection");
		if ( val != null )
		{
			if (request.getParameter("modification") != null) {
			    // Invoke FirstServlet's job here.
		response.sendRedirect("modification.jsp");
		logger.info("received this query :  = displayName" + val);
		try {
			Collection<Identity> idLists = dao.search(new Identity(val, null, null));
			request.getSession().setAttribute("idLists", idLists);
			
		} catch (DAOSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		else if (request.getParameter("delete") != null)
		{
			try {
				dao.delete(new Identity(val, null, null));
				response.sendRedirect("success.jsp");
			} catch (DAODeleteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		else
		{
			response.sendRedirect("search.jsp");
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