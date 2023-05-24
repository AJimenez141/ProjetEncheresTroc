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
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.BLLException;
import fr.eni.projet.bll.CategorieManager;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
//		PREPARATION DES VARIABLES
    	List<Enchere> encheres = new ArrayList<>();
    	List<ArticleVendu> articlesVendus = new ArrayList<>();
//    	ENCHERES COURANTES OU A DEFAUT ARTICLES EN VENTES
    	List<Enchere> enchereCourantes 	= new ArrayList<>();
    	List<ArticleVendu> articlesEnVente = new ArrayList<>();
//    	LISTE CATEGORIES
    	List<Categorie> categories = new ArrayList<>();	
    	
//    	INSTANCIATION DES MANAGERS
    	EnchereManager mgr = EnchereManager.getInstance();
    	CategorieManager mgrCat = CategorieManager.getInstance();
    	ArticleVenduManager mgrArt = ArticleVenduManager.getInstance();
    	
//    	CREATION D'UNE LISTE D'ERREUR POUR AFFICHAGE
    	List<String> erreurs = new ArrayList<>();
    	 
//    	ENCHERES	
//    	RECUPERATION DE TOUTES LES ENCHERES
		try {
			encheres = mgr.recupererLesEncheres();
		} catch (BLLException e) {
			e.printStackTrace();
    		erreurs.add(e.toString());
		}
//		RECUPERATION DE TOUS LES ARTICLES
		try {
			articlesVendus = mgrArt.recupererLesArticlesVendus();
		} catch (BLLException e) {
			e.printStackTrace();
    		erreurs.add(e.toString());
		}
//		AJOUT DES ENCHERES DANS ENCHERESCOURANTES
		for (ArticleVendu article : articlesVendus) {
			Enchere plusHauteEnchere = null;
			int numeroArticle = article.getNoArticle();
			
			try {
				plusHauteEnchere = EnchereManager.getInstance().recupererEnchereLaPlusHaute(numeroArticle);
			} catch (BLLException e) {
				e.printStackTrace();
				erreurs.add(e.toString());
			}
			
			if(plusHauteEnchere != null) {
				enchereCourantes.add(plusHauteEnchere);
			} else {
				articlesEnVente.add(article);
			}	
		}

//    	CATEGORIES
    	try {
    		categories = mgrCat.recupererLesCategorie();
		} catch (Exception e) {
			e.printStackTrace();
    		erreurs.add(e.toString());
		}
		
    	this.getServletContext().setAttribute("encheresCourantes", enchereCourantes);
    	this.getServletContext().setAttribute("articlesEnVente", articlesEnVente);
    	this.getServletContext().setAttribute("categories", categories);
    	this.getServletContext().setAttribute("erreurs", erreurs);
    	
    	for (String string : erreurs) {
			System.out.println(string);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/pages/AccueilNonConnecte.jsp");
		rd.forward(request, response);
    }
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
//		String categorie = request.getParameter("categorie");
//		String recherche = request.getParameter("filtre");
//		
//		List<Enchere> encheresFiltrees = new ArrayList<>();
//    	List<Categorie> categories = new ArrayList<>();
//		List<String> erreurs = new ArrayList<>();
		
//    	EnchereManager mgr = EnchereManager.getInstance();
//    	CategorieManager mgrCat = CategorieManager.getInstance();
		
//    	String test = null;
//		
//		if(categorie != null && recherche != null) {
//			try {
//				encheresFiltrees = mgr.recupererEnchereFiltreeRechercheCategorie(recherche, categorie);
//			} catch (BLLException e) {
//				e.printStackTrace();
//				erreurs.add(e.toString());
//			}
//		} else if(recherche != null) {
//			try {
//				encheresFiltrees = mgr.recupererEnchereFiltreeRecherche(recherche);
//			} catch (BLLException e) {
//				e.printStackTrace();
//				erreurs.add(e.toString());
//			}
//		} else if(categorie != null) {
//			try {
//				encheresFiltrees = mgr.recupererEnchereFiltreeCategorie(categorie);
//			} catch (BLLException e) {
//				e.printStackTrace();
//				erreurs.add(e.toString());
//			}
//		}
		
//    	CATEGORIES
    	
//    	try {
//    		categories = mgrCat.recupererLesCategorie();
//		} catch (Exception e) {
//			e.printStackTrace();
//			erreurs.add(e.toString());
//		}
		
//		this.getServletContext().setAttribute("encheresFiltrees", encheresFiltrees);
//    	this.getServletContext().setAttribute("categories", categories);
//		this.getServletContext().setAttribute("erreurs", erreurs);
		
		RequestDispatcher rd = request.getRequestDispatcher("/pages/AccueilNonConnecte.jsp");
    	rd.forward(request, response);
	}

}
