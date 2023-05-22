package fr.eni.projet.servlets.test;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.projet.bll.RetraitManager;
import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.BLLException;
import fr.eni.projet.bo.Retrait;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ArticleVenduDALException;
import fr.eni.projet.dal.DALException;
import fr.eni.projet.dal.RetraitDALException;
import fr.eni.projet.bo.ArticleVendu;

@WebServlet("/TestServletRetrait")
public class TestServletRetrait extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestServletRetrait() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//=======================TEST 1 - INSERTION===========================================
		
		RetraitManager retraitManager = new RetraitManager();
		ArticleVenduManager articleVenduManager = ArticleVenduManager.getInstance();
		
		ArticleVendu articleVendu = null;
		
//		RECUPERATION DE L'ARTICLE | OK
		try {
			articleVendu = articleVenduManager.recupererUnArticleVendu(3);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		Utilisateur vendeur = articleVendu.getVendeur();
		
//		CREATION DU RETRAIT
		Retrait unRetrait = new Retrait(vendeur.getAdresse(), articleVendu);
		
		try {
			retraitManager.creerRetrait(unRetrait);
			response.getWriter().append("Served at: ").append("Lieu de retrait ajouté");
		} catch (RetraitDALException | SQLException e) {
			e.printStackTrace();
		}
	}
}
