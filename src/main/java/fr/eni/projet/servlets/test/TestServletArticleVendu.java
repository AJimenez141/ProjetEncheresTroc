package fr.eni.projet.servlets.test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.BLLException;
import fr.eni.projet.bll.CategorieManager;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Utilisateur;

/**
 * Servlet implementation class TestServletArticleVendu
 */
@WebServlet("/TestServletArticleVendu")
public class TestServletArticleVendu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServletArticleVendu() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Instanciantion du manager
		ArticleVenduManager articleManager = ArticleVenduManager.getInstance();
		CategorieManager categorieManager = CategorieManager.getInstance();
		UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
		
//	FIXTURES
//		ARTICLE 1
		
		Adresse adresseJeanLuc = new Adresse("200 rue des coquillettes", "44100", "Nantes");
		Utilisateur jeanLuc = new Utilisateur(1, "jjluc", "Jean-Luc", "Grandfan", "jluclefan@mail.com", "aeroTiti000", "0712345678",adresseJeanLuc, 25);
		LocalDate dateDebutEnchere = LocalDate.of(2023, 04, 15);
		LocalDate dateFinEnchere = LocalDate.of(2023, 10, 15); 
		
//		RECUPERATION UTILISATEUR
		
		Utilisateur utilisateurJJ = null;

		try {
			utilisateurJJ = utilisateurManager.recupererUnUtilisateur(9);
		} catch (BLLException e) {
			e.printStackTrace();
		}

		
//		RECUPERATION CATEGORIE
		
		Categorie art = null;
		try {
			art = CategorieManager.getInstance().recupererUneCategorie(2);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
//		TEST INSERTION
			
		try {
//			ArticleVendu article = new ArticleVendu(1, "Grille-Pain en or", "Grille-Pain en or, très très rare.",dateDebutEnchere, dateFinEnchere, 20, 599, utilisateurJJ, art);
			
			ArticleVendu article = new ArticleVendu(1, "Truc", "truc enorme", LocalDate.of(2023, 04, 15),LocalDate.of(2023, 10, 15), 0, utilisateurJJ, art);
			
			articleManager.ajouterArticleVendu(article);
			response.getWriter().append(article.toString());
			
		} catch (BLLException e) {
			e.printStackTrace();
			response.getWriter().append(e.toString());
		}
		
//		TEST RECUPERER AVEC ID
		
//		try {
//			ArticleVendu articleRecupere = articleManager.recupererUnArticleVendu(2);
//			response.getWriter().append(articleRecupere.toString());
//		} catch (ArticleVenduDALException | SQLException e) {
//			e.printStackTrace();
//		}
		
//		TEST RECUPERER TOUS LES ARTICLES
		
//		try {		
//			List<ArticleVendu> articles = articleManager.recupererLesArticlesVendus();
//			for (ArticleVendu article : articles) {
//				response.getWriter().append(article.toString());
//			}
//		}catch (ArticleVenduDALException | SQLException e) {
//			e.printStackTrace();
//		}
		
//		TEST RECUPERER LES ARTICLES PAR UTILISATEUR
		
//		try {		
//			List<ArticleVendu> articles = articleManager.recupererLesArticlesVendusParUtilisateur(utilisateurJJ.getNoUtilisateur());
//			for (ArticleVendu article : articles) {
//				response.getWriter().append(article.toString());
//			}
//		}catch (ArticleVenduDALException | SQLException e) {
//			e.printStackTrace();
//		}
		
//		TEST SUPPRESSION
		
//		try {
//			articleManager.supprimerArticleVendu(2);
//			response.getWriter().append("Article bien supprimé");			
//		} catch (ArticleVenduDALException | SQLException e) {
//			e.printStackTrace();
//		}
		
		
	}
}
