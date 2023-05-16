package fr.eni.projet.test;

import java.time.LocalDate;

import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Retrait;
import fr.eni.projet.bo.Utilisateur;

public abstract class TestBO {

	public static void main(String[] args) {

		// INFOS ENCHERISSEURS
		Adresse adresseEncherisseur1 = new Adresse("95 rue des champs", "44100", "Nantes");
		Utilisateur encherisseur1 = new Utilisateur(1, "jdupont", "dupont", "jean", "jdupont@mail.com", "0612345678", adresseEncherisseur1, 15);
		
		System.out.println(encherisseur1);

		Adresse adresseEncherisseur2 = new Adresse("200 rue des coquillettes", "44100", "Nantes");
		Utilisateur encherisseur2 = new Utilisateur(1, "llucas", "lucas", "luc", "llucas@mail.com", "0712345678",
				adresseEncherisseur2, 25);
		
		System.out.println(encherisseur2);

		// INFOS VENDEUR
		Adresse adresseVendeur = new Adresse("22 rue des prés", "44100", "Nantes");
		Utilisateur vendeur = new Utilisateur(2, "pdubois", "dubois", "pierre", "pdubois@mail.com", "0787654321",
				adresseVendeur, 35);

		// INFOS ENCHÈRE
		LocalDate dateDebutEnchere = LocalDate.of(2023, 05, 15);
		LocalDate dateFinEnchere = LocalDate.of(2023, 06, 15);

		Categorie categorieArticle = new Categorie(1, "Ustensiles de cuisine");
		ArticleVendu article = new ArticleVendu(1, "Grille-Pain en or", "Grille-Pain en or, très très rare.", dateDebutEnchere, dateFinEnchere, 20, 599, true, vendeur, categorieArticle);
		
		System.out.println(article);

		Adresse lieuRetrait = new Adresse("Gare de Nantes", "44100", "Nantes");
		Retrait retrait = new Retrait(lieuRetrait, article);
		
		System.out.println(retrait);

		// DÉROULEMENT ENCHÈRE
		System.out.println(article.getNomArticle() +  ", en vente: " + article.isEnVente());
		System.out.println("Début des enchères");
		
		Enchere enchere1 = new Enchere(LocalDate.now(), 25.00, encherisseur1, article);
		System.out.println(enchere1);
		
		Enchere enchere2 = new Enchere(LocalDate.now(), 45.00, encherisseur2, article);
		System.out.println(enchere2);
		
		Enchere enchere3 = new Enchere(LocalDate.now(), 65.00, encherisseur1, article);
		System.out.println(enchere3);
		
		Enchere enchere4 = new Enchere(LocalDate.now(), 1200.00, encherisseur2, article);
		System.out.println(enchere4);
		
		Enchere enchere5 = new Enchere(LocalDate.now(), 1201.00, encherisseur1, article);
		System.out.println(enchere5);
		
		article.setEnVente(false);
		System.out.println(article.getNomArticle() +  ", en vente: " + article.isEnVente());
		System.out.println("Fin des enchères");
	}

}
