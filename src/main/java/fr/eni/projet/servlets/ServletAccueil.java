package fr.eni.projet.servlets;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.BLLException;
import fr.eni.projet.bll.CategorieManager;
import fr.eni.projet.bll.EnchereManager;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.ArticleVendu;
import java.util.List;
import java.util.ArrayList;

/**
 * Servlet implementation class ServeltAccueil
 */
@WebServlet("/Accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAccueil() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
//    	RECUPERATION DE LA SESSION
    	HttpSession session = request.getSession();
    	
//    	CREATION D'UNE LISTE D'ERREUR POUR AFFICHAGE
    	List<String> erreurs = new ArrayList<>();
    	 
//    	ENCHERES
//    	VERIFICATION SI RECHERCHE FILTREE EXISTE
    	
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
		
		RequestDispatcher rd = request.getRequestDispatcher("/pages/ListeEncheresConnecte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RECUPERATION DES FILTRES
		String categorie = request.getParameter("categorie");
		String recherche = request.getParameter("filtre");
		
//		RECUPERATION DU FILTRE CORRESPONDANT AU CHOIX DE L'UTILISATEUR
//		TODO - A VOIR SI UTILE
		String choixListe = request.getParameter("choixListe");
		
//		RECUPERATION DE L'UTILISATEUR DE LA SESSION
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
//		ID DE L'UTILISATEUR
		int idUtilisateur = utilisateur.getNoUtilisateur();
		
//		COTE ACHATS
		String enchereOuvertes = request.getParameter("enchereOuvertes");
		String mesEncheres = request.getParameter("mesEncheres");
		String mesEncheresRemportees = request.getParameter("mesEncheresRemportees");
		
//		COTE VENTES
		String mesVentesEnCours = request.getParameter("mesVentesEnCours");
		String ventesNonDebutees = request.getParameter("ventesNonDebutees");
		String ventesTerminees = request.getParameter("ventesTerminees");
				
//		RECUPERATION DES LISTES - ACHATS
//		DEFINITION DE LA VARIABLE
		List<ArticleVendu> encheresFiltrees = new ArrayList<>();
    	List<ArticleVendu> articlesVendus = new ArrayList<>();
//    	ENCHERES COURANTES OU A DEFAUT ARTICLES EN VENTES
		List<Enchere> enchereCourantes 	= new ArrayList<>();
		List<ArticleVendu> articlesEnVente = new ArrayList<>();
		List<Enchere> encheresUtilisateur = new ArrayList<>();
		List<Enchere> enchereGagneesUtilisateur = new ArrayList<>();
		
//		RECUPERATION DES LISTES - VENTES
		List<Enchere> ventesUtilisateur = new ArrayList<>();
		
		List<Enchere> ventesUtilisateurEnCours = new ArrayList<>();
		List<Enchere> ventesUtilisateurTerminees = new ArrayList<>();
		List<Enchere> ventesUtilisateurNonDebutees = new ArrayList<>();
		
//		RECUPERATION DES CATEGORIES
    	List<Categorie> categories = new ArrayList<>();
    	
//    	RECUPERATION DES ERREURS
		List<String> erreurs = new ArrayList<>();
		
    	EnchereManager mgr = EnchereManager.getInstance();
    	CategorieManager mgrCat = CategorieManager.getInstance();
		
//    	String test = null;
    	
//		------------------- FILTRE -------------------
    	if(categorie.equals("Toutes") && recherche.isBlank() ) {
    		
//    		RECUPERATION DE TOUS LES ARTICLES
    		try {
    			articlesVendus = ArticleVenduManager.getInstance().recupererLesArticlesVendus();
    		} catch (BLLException e) {
    			e.printStackTrace();
        		erreurs.add(e.toString());
    		}
    		
//    		AJOUT DES ENCHERES DANS ENCHERESCOURANTES
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
    	} else if(!categorie.equals("Toutes") && !recherche.isBlank()) {
			try {
				encheresFiltrees = ArticleVenduManager.getInstance().recupererLesArticlesVendusParRechercheEtCategorie(recherche,categorie);
			} catch (BLLException e) {
				e.printStackTrace();
				erreurs.add(e.toString());
			}
		} else if(!recherche.equals("")) {
			try {
				encheresFiltrees = ArticleVenduManager.getInstance().recuperLesArticlesVendusParRecherche(recherche);
				for (ArticleVendu art : encheresFiltrees) {
					System.out.println(art);
				}
			} catch (BLLException e) {
				e.printStackTrace();
				erreurs.add(e.toString());
			}	
		} else if(!categorie.equals("Toutes")) {	
			try {
				encheresFiltrees = ArticleVenduManager.getInstance().recupererLesArticlesVendusParCategorie(categorie);
			} catch (BLLException e) {
				e.printStackTrace();
				erreurs.add(e.toString());
			}
		}
		
    	if(!encheresFiltrees.isEmpty()) { 		
    		for (ArticleVendu article : encheresFiltrees) {
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
		}
//		------------------ FIN FILTRE -----------------
    	
//    	------------------ ACHAT ------------------   	
    	
//    	ENCHERES UTILISATEURS
    	if(mesEncheres != null || mesEncheresRemportees != null) {
    		try {
    			encheresUtilisateur = mgr.recupererEncheresUtilisateurs(idUtilisateur);
    		} catch (Exception e) {
				e.printStackTrace();
				erreurs.add(e.toString());
			}
    		
    		if(mesEncheres != null) {
    			for (Enchere enchere : encheresUtilisateur) {
    				if(enchere.getArticleVendu().isEnVente()) {
    					encheresUtilisateur.add(enchere);
    				}
    			}
    		}
    		if(mesEncheresRemportees != null) {
    			for (Enchere enchere : encheresUtilisateur) {
    				if(!enchere.getArticleVendu().isEnVente()) {
    					enchereGagneesUtilisateur.add(enchere);
    				}
    			}
    		}
    	}
		
/*
//    	------------------ VENTE ------------------
		
//		VENTES EN COURS
		if(mesVentesEnCours != null) {
			try {
				ventesUtilisateur = mgr.recupererVenteUtilisateurs(idUtilisateur);
			}catch (BLLException e) {
				e.printStackTrace();
				erreurs.add(e.toString());
			}
			
			for (Enchere enchere : ventesUtilisateur) {
				if(Date.from(Instant.now()).after(Date.from(enchere.getArticleVendu().getDateDebutEncheres().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())) && Date.from(Instant.now()).before(Date.from(enchere.getArticleVendu().getDateFinEncheres().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))) {
					ventesUtilisateurEnCours.add(enchere);
				}
			}
		}
		
//		VENTES NON DEBUTEES
		if(ventesNonDebutees != null) {
			try {
				ventesUtilisateur = mgr.recupererVenteUtilisateurs(idUtilisateur);
			}catch (BLLException e) {
				e.printStackTrace();
				erreurs.add(e.toString());
			}
			
			for (Enchere enchere : ventesUtilisateur) {
				if(Date.from(Instant.now()).before(Date.from(enchere.getArticleVendu().getDateDebutEncheres().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))) {
					ventesUtilisateurNonDebutees.add(enchere);
				}
			}
		}
		
//		VENTES TERMINEES
		if(ventesTerminees != null) {
			try {
				ventesUtilisateur = mgr.recupererVenteUtilisateurs(idUtilisateur);
			}catch (BLLException e) {
				e.printStackTrace();
				erreurs.add(e.toString());
			}
			
			for (Enchere enchere : ventesUtilisateur) {
				if(Date.from(Instant.now()).after(Date.from(enchere.getArticleVendu().getDateFinEncheres().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))) {
					ventesUtilisateurTerminees.add(enchere);
				}
			}
		}
		
//    	ACHATS
    	this.getServletContext().setAttribute("enchereOuvertes", enchereOuvertes);
    	this.getServletContext().setAttribute("mesEncheres", mesEncheres);
    	this.getServletContext().setAttribute("mesEncheresRemportees", mesEncheresRemportees);
    	
//    	VENTES
    	this.getServletContext().setAttribute("ventesUtilisateurEnCours", ventesUtilisateurEnCours);
    	this.getServletContext().setAttribute("ventesUtilisateurNonDebutees", ventesUtilisateurNonDebutees);
    	this.getServletContext().setAttribute("ventesUtilisateurTerminees", ventesUtilisateurTerminees);
		
 */
    	
//    	--------------- CATEGORIES ----------------	
    	try {
    		categories = mgrCat.recupererLesCategorie();
		} catch (Exception e) {
			e.printStackTrace();
			erreurs.add(e.toString());
		}
//    	-------------- FIN CATEGORIES --------------	
    	
//    	ERREURS
		this.getServletContext().setAttribute("erreurs", erreurs);
    	this.getServletContext().setAttribute("encheresCourantes", enchereCourantes);
    	this.getServletContext().setAttribute("articlesEnVente", articlesEnVente);
    	this.getServletContext().setAttribute("categories", categories);
		this.getServletContext().setAttribute("erreurs", erreurs);
		
		RequestDispatcher rd = request.getRequestDispatcher("/pages/ListeEncheresConnecte.jsp");
		rd.forward(request, response);
	}

}
