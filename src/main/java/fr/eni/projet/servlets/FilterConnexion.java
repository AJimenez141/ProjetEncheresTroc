package fr.eni.projet.servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class FilterConnexion
 */
@WebFilter("/*")
public class FilterConnexion implements Filter {

    /**
     * Default constructor. 
     */
    public FilterConnexion() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = ((HttpServletRequest) request).getSession();
		
		if( !httpRequest.getServletPath().toLowerCase().contains("connexion") && !httpRequest.getServletPath().toLowerCase().contains("inscription") && !httpRequest.getServletPath().toLowerCase().contains("accueilnonconnecte"))
		{		
			
			if(session.getAttribute("pseudo") == null)
			{
				HttpServletResponse httpResponse2 = (HttpServletResponse) response;
				httpResponse2.sendRedirect("/AccueilNonConnecte");
			} else {
				chain.doFilter(httpRequest, response);
			}
		}
		else
		{
			if(session.getAttribute("pseudo") != null)
			{
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.sendRedirect("/Accueil");
			}
			else {
				System.out.println("pouet");
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
