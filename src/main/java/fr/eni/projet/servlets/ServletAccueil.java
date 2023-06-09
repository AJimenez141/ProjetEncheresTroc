package fr.eni.projet.servlets;

import java.io.IOException;
import java.time.LocalDate;

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
    	@SuppressWarnings("unused")
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
			if(article.isEnVente()) {				
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
		
		String enchereAchat = request.getParameter("enchereAchat");
		String enchereVente = request.getParameter("enchereVente");
		
//		RECUPERATION DU FILTRE CORRESPONDANT AU CHOIX DE L'UTILISATEUR
		String choixListe = request.getParameter("choixListe");
		
//		RECUPERATION DE L'UTILISATEUR DE LA SESSION
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
//		ID DE L'UTILISATEUR
		int idUtilisateur = utilisateur.getNoUtilisateur();

//		DEFINITION DE LA VARIABLE
		List<ArticleVendu> encheresFiltrees = new ArrayList<>();
    	List<ArticleVendu> articlesVendus = new ArrayList<>();
    	
//    	ENCHERES COURANTES OU A DEFAUT ARTICLES EN VENTES
		List<Enchere> enchereCourantes 	= new ArrayList<>();
		List<ArticleVendu> articlesEnVente = new ArrayList<>();
		List<Enchere> encheresUtilisateur = new ArrayList<>();
		
//		RECUPERATION DES CATEGORIES
    	List<Categorie> categories = new ArrayList<>();
    	
//    	RECUPERATION DES ERREURS
		List<String> erreurs = new ArrayList<>();
		
    	CategorieManager mgrCat = CategorieManager.getInstance();
    	
    	
//    	------------------ ACHAT ------------------  
//    	-------------------------------------------
    	if(choixListe != null && choixListe.equals("achats")) {
    		
    		session.setAttribute("venteChecked", false);
    		
    		
//			------------------- FILTRE -------------------
//    		SI RIEN DE MODIFIE
    		if(categorie.equals("Toutes") && recherche.isBlank() && enchereAchat.equals("encheresOuvertes") ) {
    			
//    			RECUPERATION DE TOUS LES ARTICLES
    			try {
    				articlesVendus = ArticleVenduManager.getInstance().recupererLesArticlesVendus();
    			} catch (BLLException e) {
    				e.printStackTrace();
    				erreurs.add(e.toString());
    			}
    			
//    			AJOUT DES ENCHERES DANS ENCHERESCOURANTES
    			for (ArticleVendu article : articlesVendus) {    				
    				if(article.isEnVente()) {
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
    			
//    		SI BARRE RECHERCHE + CATEGORIE MODIFIES
    		} else if(!categorie.equals("Toutes") && !recherche.isBlank()) {
    			try {
    				encheresFiltrees = ArticleVenduManager.getInstance().recupererLesArticlesVendusParRechercheEtCategorie(recherche,categorie);
    			} catch (BLLException e) {
    				e.printStackTrace();
    				erreurs.add(e.toString());
    			}
    			
//    		SI RECHERCHE UNIQUEMENT EST MODIFIEE
    		} else if(!recherche.equals("")) {
    			try {
    				encheresFiltrees = ArticleVenduManager.getInstance().recuperLesArticlesVendusParRecherche(recherche);
    			} catch (BLLException e) {
    				e.printStackTrace();
    				erreurs.add(e.toString());
    			}
    			
//    		SI CATEGORIE UNIQUEMENT EST MODIFIEE
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
    		
//			------------------ FIN FILTRE -----------------
    		
//    		----------------- MES ENCHERES ----------------
    		else if(enchereAchat.equals("mesEncheres")) {  
    			List<Integer> filtreArticle = new ArrayList<>();
    			
    			try {
    				encheresUtilisateur = EnchereManager.getInstance().recupererEncheresUtilisateurs(idUtilisateur);
				} catch (BLLException e) {
					e.printStackTrace();
					erreurs.add(e.toString());
				}
    			
    			for (Enchere enchere : encheresUtilisateur) {
					if(!filtreArticle.contains(enchere.getArticleVendu().getNoArticle()) && enchere.getArticleVendu().isEnVente()) {
						filtreArticle.add(enchere.getArticleVendu().getNoArticle());
						Enchere enchereLaPlusHaute = null;
						
						try {
							enchereLaPlusHaute = EnchereManager.getInstance().recupererEnchereLaPlusHaute(enchere.getArticleVendu().getNoArticle());
						} catch (BLLException e) {
							e.printStackTrace();
							erreurs.add(e.toString());
						}
						
						enchereCourantes.add(enchereLaPlusHaute);
					}
				}
    			
    		}
//      	--------------- FIN MES ENCHERES --------------
    		
//    		------------ MES ENCHERES REMPORTEES ----------
    		else if(enchereAchat.equals("mesEncheresRemportees")) {
    			List<Integer> filtreArticle = new ArrayList<>();
    			
    			try {
    				encheresUtilisateur = EnchereManager.getInstance().recupererEncheresUtilisateurs(idUtilisateur);
				} catch (BLLException e) {
					e.printStackTrace();
					erreurs.add(e.toString());
				}
    			
    			for (Enchere enchere : encheresUtilisateur) {
					if(!filtreArticle.contains(enchere.getArticleVendu().getNoArticle()) && !enchere.getArticleVendu().isEnVente()) {
						filtreArticle.add(enchere.getArticleVendu().getNoArticle());					
						enchereCourantes.add(enchere);
					}
					
				}
    		}
//      	---------- FIN MES ENCHERES REMPORTEES --------
    		
//      -------------------------------------------
//      ---------------  FIN ACHAT ----------------
    		
//      --------------  DEBUT VENTE --------------- 
//      -------------------------------------------
    	} else if (choixListe != null && choixListe.equals("mesVentes")) {
    		session.setAttribute("venteChecked", true);
    		
//    		------------- MES VENTES EN COURS ------------
    		if(enchereVente.equals("mesVentesEnCours")) {
    			List<ArticleVendu> articlesEnVenteUtilisateur = new ArrayList<>();
    			
    			try {
    				articlesEnVenteUtilisateur = ArticleVenduManager.getInstance().recupererLesArticlesVendusParUtilisateur(idUtilisateur);
				} catch (BLLException e) {
					e.printStackTrace();
					erreurs.add(e.toString());
				}
    			
//    			AJOUT DES ENCHERES DANS ENCHERESCOURANTES
    			for (ArticleVendu article : articlesEnVenteUtilisateur) {
    				if(article.isEnVente()) {	
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
    		}
//    		----------- FIN MES VENTES EN COURS ----------
    		
    		
//    		----------- MES VENTES NON DEBUTEES ----------
    		else if(enchereVente.equals("ventesNonDebutees")) {
    			List<ArticleVendu> articlesEnVenteUtilisateur = new ArrayList<>();
    			
    			try {
    				articlesEnVenteUtilisateur = ArticleVenduManager.getInstance().recupererLesArticlesVendusParUtilisateur(idUtilisateur);
				} catch (BLLException e) {
					e.printStackTrace();
					erreurs.add(e.toString());
				}
    			
    			for (ArticleVendu article : articlesEnVenteUtilisateur) {    				
					if(!article.isEnVente() && LocalDate.now().isBefore(article.getDateDebutEncheres())
					) {
						articlesEnVente.add(article);
					}
				}
    			
    		}
    		
//    		--------- FIN MES VENTES NON DEBUTEES --------
    		
    		
//    		------------ MES VENTES TERMINEES ------------
    		else if(enchereVente.equals("ventesTerminees")) {
 			List<ArticleVendu> articlesEnVenteUtilisateur = new ArrayList<>();

    			try {
    				articlesEnVenteUtilisateur = ArticleVenduManager.getInstance().recupererLesArticlesVendusParUtilisateur(idUtilisateur);
				} catch (BLLException e) {
					e.printStackTrace();
					erreurs.add(e.toString());
				}
    			
//    			AJOUT DES ENCHERES DANS ENCHERESCOURANTES
    			for (ArticleVendu article : articlesEnVenteUtilisateur) {
    				if(!article.isEnVente() && LocalDate.now().isAfter(article.getDateFinEncheres())) {	
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
    		}
    		
//    		----------- FIN MES VENTES TERMINEES ---------	
//      -------------------------------------------
//      ---------------  FIN VENTE ----------------
    		
//      --------------- DEFAUT ----------------	
    	} else {
			try {
				articlesVendus = ArticleVenduManager.getInstance().recupererLesArticlesVendus();
			} catch (BLLException e) {
				e.printStackTrace();
				erreurs.add(e.toString());
			}
			
//			AJOUT DES ENCHERES DANS ENCHERESCOURANTES
			for (ArticleVendu article : articlesVendus) {    				
				if(article.isEnVente()) {
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
    	}
    	
    	
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
