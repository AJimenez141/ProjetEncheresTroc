package fr.eni.projet.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.BLLException;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Utilisateur;

/**
 * Servlet implementation class ServletAutreProfil
 */
@WebServlet("/AutreProfil")
public class ServletAutreProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAutreProfil() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur util = null;
		try {
			ArticleVendu article = null;
			article = ArticleVenduManager.getInstance().recupererUnArticleVendu(Integer.parseInt(request.getParameter("idArticle")));
			Utilisateur utilisateurPage = article.getVendeur();
			util = UtilisateurManager.getInstance().recupererUnUtilisateur(utilisateurPage.getNoUtilisateur());
			request.setAttribute("util", util);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/pages/Profil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
