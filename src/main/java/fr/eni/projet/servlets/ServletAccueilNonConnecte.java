package fr.eni.projet.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.BLLException;
import java.util.ArrayList;
import fr.eni.projet.bll.EnchereManager;
import fr.eni.projet.bo.ArticleVendu;

/**
 * Servlet implementation class AccueilNonConnecte
 */
@WebServlet("/AccueilNonConnecte")
public class ServletAccueilNonConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletAccueilNonConnecte() {
    }

    /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Enchere> encheres = new ArrayList<>();
		try {
			encheres = this.recupererDernieresEncheres(this.recupererLesArticlesEnVente());
		} catch (BLLException e) {
			e.printStackTrace();
		}
    	this.getServletContext().setAttribute("listeEncheres", encheres);
    	RequestDispatcher rd = request.getRequestDispatcher("/pages/AccueilNonConnecte.jsp");
    	rd.forward(request, response);
    }

    private List<Enchere> recupererDernieresEncheres(List<ArticleVendu> pArticlesEnVente) throws BLLException {
    	EnchereManager mgr = EnchereManager.getInstance();
    	List<Enchere> encheres = new ArrayList<>();
    	
    	for (ArticleVendu article : pArticlesEnVente) {
			encheres.add(mgr.recupererEnchereLaPlusHaute(article.getNoArticle()));
		}
		return encheres;
    }
    
    private List<ArticleVendu> recupererLesArticlesEnVente() {
    	List<ArticleVendu> articles = new ArrayList<>();
    	List<ArticleVendu> articlesEnVente = new ArrayList<>();
    	ArticleVenduManager mng = ArticleVenduManager.getInstance();
    	
    	try {
    		articles = mng.recupererLesArticlesVendus();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	
    	for (ArticleVendu article : articles) {
			if(article.isEnVente()) {
				articlesEnVente.add(article);
			}
		}
    	return articlesEnVente;
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
