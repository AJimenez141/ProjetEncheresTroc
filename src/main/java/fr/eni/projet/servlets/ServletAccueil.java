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
import fr.eni.projet.bll.BLLException;
import fr.eni.projet.bll.CategorieManager;
import fr.eni.projet.bll.EnchereManager;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Categorie;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
    	List<Enchere> encheres 			= new ArrayList<>();
    	List<Enchere> enchereCourantes 	= new ArrayList<>();
    	List<Categorie> categories 		= new ArrayList<>();
    	
//    	INSTANCIATION DES MANAGERS
    	EnchereManager mgr = EnchereManager.getInstance();
    	CategorieManager mgrCat = CategorieManager.getInstance();
    	
//    	CREATION D'UNE LISTE D'ERREUR POUR AFFICHAGE
    	List<String> erreurs = new ArrayList<>();
    	
//    	ENCHERES	
		try {
			encheres = mgr.recupererLesEncheres();
			
		} catch (BLLException e) {
			
			e.printStackTrace();
    		erreurs.add(e.toString());
		}
		
		for (Enchere enchere : encheres) {
			if(enchere.getArticleVendu().isEnVente()) {
				
				enchereCourantes.add(enchere);
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
    	this.getServletContext().setAttribute("categories", categories);
    	this.getServletContext().setAttribute("erreurs", erreurs);

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
		List<Enchere> encheresFiltrees = new ArrayList<>();
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
		
//    	ENCHERES COURANTES
		if(categorie != null && recherche != null && enchereOuvertes != null) {
			try {
				encheresFiltrees = mgr.recupererEnchereFiltreeRechercheCategorie(recherche, categorie);
			} catch (BLLException e) {
				e.printStackTrace();
				erreurs.add(e.toString());
			}
		} else if(recherche != null && enchereOuvertes != null) {
			try {
				encheresFiltrees = mgr.recupererEnchereFiltreeRecherche(recherche);
			} catch (BLLException e) {
				e.printStackTrace();
				erreurs.add(e.toString());
			}
		} else if(categorie != null && enchereOuvertes != null) {
			try {
				encheresFiltrees = mgr.recupererEnchereFiltreeCategorie(categorie);
			} catch (BLLException e) {
				e.printStackTrace();
				erreurs.add(e.toString());
			}
		}
		
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
		
//    	--------------- CATEGORIES ----------------	
    	try {
    		categories = mgrCat.recupererLesCategorie();
		} catch (Exception e) {
			e.printStackTrace();
			erreurs.add(e.toString());
		}
		
//    	ACHATS
    	this.getServletContext().setAttribute("enchereOuvertes", enchereOuvertes);
    	this.getServletContext().setAttribute("mesEncheres", mesEncheres);
    	this.getServletContext().setAttribute("mesEncheresRemportees", mesEncheresRemportees);
    	
//    	VENTES
    	this.getServletContext().setAttribute("ventesUtilisateurEnCours", ventesUtilisateurEnCours);
    	this.getServletContext().setAttribute("ventesUtilisateurNonDebutees", ventesUtilisateurNonDebutees);
    	this.getServletContext().setAttribute("ventesUtilisateurTerminees", ventesUtilisateurTerminees);
    	
//    	CATEGORIES
    	this.getServletContext().setAttribute("categories", categories);
    	
//    	ERREURS
		this.getServletContext().setAttribute("erreurs", erreurs);
	}

}
