package fr.eni.projet.test;

import fr.eni.projet.dal.UtilisateurDAO;
import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ArticleVenduDAO;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.EnchereDAO;
import fr.eni.projet.dal.UtilisateurDALException;

public class TestDAL {

	public static void main(String[] args) {
		
		//Utilisateur DAO
		UtilisateurDAO utilisateurDAO = DAOFactory.recupererUtilisateurDAO();
		
		// Encherisseur 1
		Adresse adresseEncherisseur1 = new Adresse("95 rue des champs", "44100", "Nantes");
		Utilisateur encherisseur1 = new Utilisateur(1, "jdupont", "dupont", "jean", "jdupont@mail.com", "0612345678", adresseEncherisseur1, 15);
		
		// Encherisseur 2
		Adresse adresseEncherisseur2 = new Adresse("200 rue des coquillettes", "44100", "Nantes");
		Utilisateur encherisseur2 = new Utilisateur(1, "llucas", "lucas", "luc", "llucas@mail.com", "0712345678", adresseEncherisseur2, 25);

		// Vendeur 1
		Adresse adresseVendeur = new Adresse("22 rue des prés", "44100", "Nantes");
		Utilisateur vendeur = new Utilisateur(2, "pdubois", "dubois", "pierre", "pdubois@mail.com", "0787654321", adresseVendeur, 35);
		
		try {
			
			utilisateurDAO.insererUtilisateur(encherisseur1);
			utilisateurDAO.insererUtilisateur(encherisseur2);
			utilisateurDAO.insererUtilisateur(vendeur);
			
		} catch (UtilisateurDALException e) {
			e.printStackTrace();
		}
	}
}
