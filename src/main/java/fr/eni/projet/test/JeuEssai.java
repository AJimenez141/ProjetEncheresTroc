package fr.eni.projet.test;

import java.time.LocalDate;

import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Retrait;
import fr.eni.projet.bo.Utilisateur;

public abstract class JeuEssai {

	public static void main(String[] args) {

// INFOS ENCHERISSEURS
		Adresse adresseEncherisseur1 = new Adresse("95 rue des champs", "44100", "Nantes");
		Utilisateur encherisseur1 = new Utilisateur(1, "jdupont", "dupont", "jean", "jdupont@mail.com", "mdp",
				"0612345678", adresseEncherisseur1, 15);
		
		System.out.println(encherisseur1);

		Adresse adresseEncherisseur2 = new Adresse("200 rue des coquillettes", "44100", "Nantes");
		Utilisateur encherisseur2 = new Utilisateur(2, "llucas", "lucas", "luc", "llucas@mail.com", "mdp", "0712345678",
				adresseEncherisseur2, 25);
		
		System.out.println(encherisseur2);
		
		Adresse adresseEncherisseur3 = new Adresse("21 avenue Gontrand Flibour", "7500", "Paris"); //erreur de format code postal
		Utilisateur encherisseur3 = new Utilisateur(3, "hblois", "blois", "henri", "hblois@mail.com", "mdp", "0667484575",
				adresseEncherisseur3, 5); //erreur crédit en décimal
		
		System.out.println(encherisseur3);
		
		Adresse adresseEncherisseur4 = new Adresse("4 boulevard Michelle Tront sur son cheval blanc", "33000", "Bordeaux"); //erreur string trop grand
		Utilisateur encherisseur4 = new Utilisateur(4, "mnefih", "nefih", "marc", "mnefihmail.com", "mdp", "0684913457", //erreur de format mail
				adresseEncherisseur4, 0);
		
		System.out.println(encherisseur4);
		
		Adresse adresseEncherisseur5 = new Adresse("15 rue du grand pont", "34000", ""); //erreur string ville nul
		Utilisateur encherisseur5 = new Utilisateur(5, "alignée", "lignée", "agnès", "alignée@mail.com", "mdp", "113504", //erreur de format telephone
				adresseEncherisseur5, 40);
		
		System.out.println(encherisseur5);
		
		Adresse adresseEncherisseur6 = new Adresse("16 rue de la garde", "59000", "Lille");
		Utilisateur encherisseur6 = new Utilisateur(5, "mchevalier", "chevalier", "marie", "mchevalier@mail.com", "mdp", "0645789461",
				adresseEncherisseur6, 20);
		
		System.out.println(encherisseur6);

		// INFOS VENDEUR
		Adresse adresseVendeur1 = new Adresse("22 rue des prés", "44100", "Nantes");
		Utilisateur vendeur1 = new Utilisateur(6, "pdubois", "dubois", "pierre", "pdubois@mail.com", "mdp", "0787654321",
				adresseVendeur1, 35);
		

		// INFOS ENCHÈRE 1
		LocalDate dateDebutEnchere = LocalDate.of(2023, 05, 15);
		LocalDate dateFinEnchere = LocalDate.of(2023, 04, 15); //erreur date de fin d'enchère antérieure à la date de début

		Categorie categorieArticle = new Categorie(1, "Ustensiles de cuisine");
		ArticleVendu article = new ArticleVendu(1, "Grille-Pain en or", "Grille-Pain en or, très très rare.",
				dateDebutEnchere, dateFinEnchere, 20, 599, vendeur1, categorieArticle);
		
		System.out.println(article);

		Adresse lieuRetrait = new Adresse("Gare de Nantes", "44100", "Nantes");
		Retrait retrait = new Retrait(lieuRetrait, article);
		
		System.out.println(retrait);

		// DÉROULEMENT ENCHÈRE 1
		System.out.println(article.getNomArticle() +  ", en vente: " + article.isEnVente());
		System.out.println("Début des enchères");
		
		Enchere enchere1 = new Enchere(LocalDate.now(), 25, encherisseur1, article);
		System.out.println(enchere1);
		
		Enchere enchere2 = new Enchere(LocalDate.now(), 15, encherisseur2, article); //erreur montant de l'enchère plus petit
		System.out.println(enchere2);
		
		Enchere enchere3 = new Enchere(LocalDate.now(), 65, encherisseur1, article);
		System.out.println(enchere3);
		
		Enchere enchere4 = new Enchere(LocalDate.now(), 1200, encherisseur1, article); //erreur le même enchérisseur enchérit 2 fois de suite
		System.out.println(enchere4);
		
		Enchere enchere5 = new Enchere(LocalDate.now(), 1201, encherisseur2, article);
		System.out.println(enchere5);
		
		article.setEnVente(false);
		System.out.println(article.getNomArticle() +  ", en vente: " + article.isEnVente());
		System.out.println("Fin des enchères");
	}
}