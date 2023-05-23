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
    	List<Enchere> enchereCourantes = new ArrayList<>();
    	
    	EnchereManager mgr = EnchereManager.getInstance();
    	
		try {
			encheres = mgr.recupererLesEncheres();
			
		} catch (BLLException e) {
			
			e.printStackTrace();
		}
		
		for (Enchere enchere : encheres) {
			if(enchere.getArticleVendu().isEnVente()) {
				
				enchereCourantes.add(enchere);
			}
		}	
		
    	this.getServletContext().setAttribute("encheresCourantes", enchereCourantes);
    	
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/pages/AccueilNonConnecte.jsp");
    	rd.forward(request, response);
    }
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String categorie = request.getParameter("categorie");
		String recherche = request.getParameter("filtre");
		
		List<Enchere> encheresFiltrees = new ArrayList<>();
    	EnchereManager mgr = EnchereManager.getInstance();
		
		if(categorie != null && recherche != null) {
			try {
				encheresFiltrees = mgr.recupererEnchereFiltreeRechercheCategorie(recherche, categorie);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		} else if(recherche != null) {
			try {
				encheresFiltrees = mgr.recupererEnchereFiltreeRecherche(recherche);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		} else if(categorie != null) {
			try {
				encheresFiltrees = mgr.recupererEnchereFiltreeCategorie(categorie);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		
		this.getServletContext().setAttribute("encheresFiltrees", encheresFiltrees);
		
		RequestDispatcher rd = request.getRequestDispatcher("/pages/AccueilNonConnecte.jsp");
    	rd.forward(request, response);
	}

}
