package fr.eni.projet.servlets.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.projet.bll.EnchereManager;
import fr.eni.projet.bll.BLLException;
import fr.eni.projet.bo.Enchere;

@WebServlet("/TestServletEnchere")
public class TestServletEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TestServletEnchere() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//=======================TEST 1 - INSERTION===========================================

//		Utilisateur encherisseur = null;
//		ArticleVendu articleVendu = null;
//		
//		try {
//			encherisseur = utilisateurManager.recupererUnUtilisateur(8);
//			articleVendu = articleVenduManager.recupererUnArticleVendu(3);
//		} catch (UtilisateurDALException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ArticleVenduDALException e) {
//			e.printStackTrace();
//		}
//		
//		Enchere uneEnchere = new Enchere(LocalDate.now(), 45, encherisseur, articleVendu);
//		
//		try {
//			enchereManager.creerEnchere(uneEnchere);
//			response.getWriter().append("Enchere - " + uneEnchere.getMontant_enchere());
//		} catch (EnchereDALException e) {
//			response.getWriter().append("Served at: ").append(e.toString());
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ArticleVenduDALException e) {
//			e.printStackTrace();
//		}
		
		//=======================TEST 2 - SELECT BY ID=======================================================
		
//		try {
//			Enchere enchere = enchereManager.recupererUneEnchere(1);
//			response.getWriter().append("Enchere - " + enchere.getMontant_enchere());
//		} catch (EnchereDALException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ArticleVenduDALException e) {
//			e.printStackTrace();
//		}		
		
//		//=======================TEST 3 - SELECT ALL=========================================================
		
		try {
			List<Enchere> lesEncheres = EnchereManager.getInstance().recupererLesEncheres();
			for(Enchere enchere1 : lesEncheres) {
				response.getWriter().append(enchere1.toString());
			}
		} catch (BLLException e) {
			response.getWriter().append("Served at: ").append(e.toString());
			e.printStackTrace();
		} 
		
//		//=======================TEST 4 - SELECT ALL BY UTILISATEUR==========================================
	
//		try {
//			List<Enchere> lesEncheresDunUtilisateur = enchereManager.recupererLesEncheresUtilisateur(8);
//			
//			for(Enchere enchere : lesEncheresDunUtilisateur) {
//				response.getWriter().append(enchere.toString()+"\n");
//			}
//			
//		} catch (EnchereDALException e) {
//			response.getWriter().append("Served at: ").append(e.toString());
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ArticleVenduDALException e) {
//			e.printStackTrace();
//		}
		
//		//=======================TEST 4 - selectMaxEnchereByArticle ==========================================
		
//			Enchere enchere;
//			try {
//				enchere = EnchereManager.getInstance().recupererEnchereLaPlusHaute(3);
//				response.getWriter().append(enchere.toString()+"\n");
//			} catch (BLLException e) {
//				response.getWriter().append(e.toString());
//				e.printStackTrace();
//			}
	}
}
