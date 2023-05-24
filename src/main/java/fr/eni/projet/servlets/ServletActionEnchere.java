package fr.eni.projet.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.BLLException;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Utilisateur;

/**
 * Servlet implementation class ActionEnchere
 */
@WebServlet("/ActionEnchere")
public class ServletActionEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActionEnchere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ArticleVendu article = null;
			article = ArticleVenduManager.getInstance().recupererUnArticleVendu(Integer.parseInt(request.getParameter("idArticle")));
			
			Utilisateur utilisateurPage = article.getVendeur();
			
			HttpSession session = request.getSession();
			Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateur");
			
			if( utilisateurPage.getNoUtilisateur() == utilisateurSession.getNoUtilisateur())
			{
				//vendeur
				
				request.setAttribute("article", article);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/EnchereNonCommencee.jsp");
				rd.forward(request, response);
			} else {
				//acheteur
				
				request.setAttribute("article", article);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Encherir.jsp");
				rd.forward(request, response);
			}
		} catch (NumberFormatException e) {
			System.out.println("l'article n'existe pas");
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect( ( (HttpServletRequest) request).getContextPath() + "/Accueil");
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
