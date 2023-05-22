package fr.eni.projet.servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
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
		if(httpRequest.getServletPath().toLowerCase().contains("connexion") || httpRequest.getServletPath().toLowerCase().contains("creation") || httpRequest.getServletPath().toLowerCase().contains("accueilnonconnecte"))
		{
			//Laissons passer la requête vers là où elle doit aller
			System.out.println("vous êtes connecté");
			chain.doFilter(request, response);
		}
		else
		{
			//Renvoyons une 403 à l'utilisateur
//			HttpServletResponse httpResponse = (HttpServletResponse) response;
//			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
			System.out.println("vous n'êtes pas connecté");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
