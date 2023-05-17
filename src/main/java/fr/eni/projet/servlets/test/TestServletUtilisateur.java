package fr.eni.projet.servlets.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ConnexionException;
import fr.eni.projet.dal.UtilisateurDALException;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServletUtilisateur")
public class TestServletUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServletUtilisateur() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();

//	FIXTURES :
		
//		UTILISATEUR 1
		Adresse adresseEncherisseur1 = new Adresse("95 rue des champs", "44100", "Nantes");
		Utilisateur encherisseur1 = new Utilisateur(1, "jdupont", "dupont", "jean", "jdupont@mail.com","aeroToto000","0612345678", adresseEncherisseur1, 15);
		
//		UTILISATEUR 2
		Adresse adresseEncherisseur2 = new Adresse("200 rue des coquillettes", "44100", "Nantes");
		Utilisateur encherisseur2 = new Utilisateur(1, "llucas", "lucas", "luc", "llucas@mail.com", "aeroTiti000", "0712345678",adresseEncherisseur2, 25);
	
		
//		TEST INSERTION	
		
//		try {
//			utilisateurManager.insererUtilisateur(encherisseur1);
//			response.getWriter().append("Utilisateur " + encherisseur1.getEmail() + " inséré");
//			utilisateurManager.insererUtilisateur(encherisseur2);
//			response.getWriter().append("Utilisateur " + encherisseur2.getEmail() + " inséré");
//		} catch (UtilisateurDALException e) {
//			e.printStackTrace();
//		}
			
			
//		TEST SELECT ALL
		
//		try {		
//			List<Utilisateur> utilisateurs = utilisateurManager.recupererLesUtilisateurs();
//			for (Utilisateur utilisateur : utilisateurs) {
//				response.getWriter().append(utilisateur.getEmail()+"\n");
//			}
//		}catch (UtilisateurDALException e) {
//			e.printStackTrace();
//		}
			
//		TEST SELECT BY ID
		
//		try {
//			Utilisateur encherisseurID = utilisateurManager.recupererUnUtilisateur(7);
//			response.getWriter().append(encherisseur1.getEmail()+"\n");
//		} catch (UtilisateurDALException | SQLException e) {
//			e.printStackTrace();
//		}
			
//		TEST DELETE UTILISATEUR
		
//		try {
//			utilisateurManager.supprimerUtilisateur(6);
//			response.getWriter().append("Utilisateur bien supprimé");			
//		} catch (UtilisateurDALException | SQLException e) {
//			e.printStackTrace();
//		}
			
//		TEST UTILISATEUR CONNEXION AVEC LE MAIL
		
//		try {			
//			Utilisateur utilisateurConnecte = utilisateurManager.seConnecter("llucas@mail.com","aeroTiti000");
//			response.getWriter().append(utilisateurConnecte.getEmail()+"\n");
//		} catch (UtilisateurDALException | SQLException e) {
//			e.printStackTrace();
//		}
			
//		TEST UTILISATEUR CONNEXION AVEC LE PSEUDO
		
//		try {		
//			Utilisateur utilisateurConnecte = utilisateurManager.seConnecter("llucas","aeroTiti000");
//			response.getWriter().append(utilisateurConnecte.getEmail()+"\n");
//		}catch (UtilisateurDALException e) {
//			e.printStackTrace();
//		}
			
//		TEST MODIFIER UN UTILISATEUR
		
//		try {
//			Utilisateur utilisateurAModifie = utilisateurManager.recupererUnUtilisateur(7);
//			utilisateurAModifie.setNom("JEANJACQUES");
//			utilisateurManager.modifierUtilisateur(utilisateurAModifie);
//			response.getWriter().append(utilisateurAModifie.getNom()+"\n");
//		}catch (UtilisateurDALException e) {
//			e.printStackTrace();
//		}

	}
}
