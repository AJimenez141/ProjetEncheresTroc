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


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = ((HttpServletRequest) request).getSession(false); //session test
		
		if( !httpRequest.getServletPath().toLowerCase().contains("connexion") && 
			!httpRequest.getServletPath().toLowerCase().contains("inscription") && 
			!httpRequest.getServletPath().toLowerCase().contains("accueilnonconnecte") && //si on accède à une page où la connexion est nécessaire alors
			!httpRequest.getServletPath().toLowerCase().contains("ressources"))
		{		
			if(session != null && session.getAttribute("utilisateur") != null)//si l'utilisateur possède une session et qu'aucun utilisateur n'es sauvegarder en session, il continue sa navigation
			{
				chain.doFilter(httpRequest, response);
			} else { //sinon on le redirige vers AccueilNonConnecte
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.sendRedirect( ( (HttpServletRequest) request).getContextPath() + "/AccueilNonConnecte");
			}
		}
		else //sinon on accède à une des pages non connecté
		{
			if(session != null && session.getAttribute("utilisateur") != null)//si l'utilisateur possède une session et qu'aucun utilisateur n'est sauvegarder en session, alors il est redirigé vers Accueil
			{
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.sendRedirect( ( (HttpServletRequest) request).getContextPath() + "/Accueil");
			}
			else { //sinon on créer la session et il continue sa navigation
				chain.doFilter(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
