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
import fr.eni.projet.bll.EnchereManager;
import fr.eni.projet.bll.RetraitManager;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Enchere;

/**
 * Servlet implementation class ServletArticle
 */
@WebServlet("/Article")
public class ServletArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/pages/Encherir.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String credits = request.getParameter("proposition");
		ArticleVendu article; 
		try {
			article = ArticleVenduManager.getInstance().recupererUnArticleVendu(Integer.parseInt(request.getParameter("idArticle")));
			
			Enchere enchere = EnchereManager.getInstance().recupererEnchereLaPlusHaute(article.getNoArticle());
			
			enchere.setMontant_enchere(Integer.parseInt(credits));
			EnchereManager.getInstance().creerEnchere(enchere);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect( ( (HttpServletRequest) request).getContextPath() + "/Accueil");
	}
}
