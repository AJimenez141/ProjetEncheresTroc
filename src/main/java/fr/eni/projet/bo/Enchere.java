package fr.eni.projet.bo;

import java.time.LocalDate;

public class Enchere {
	
	int noEnchere;
	LocalDate dateEnchere;
	double montant_enchere;
	Utilisateur encherisseur;
	ArticleVendu articleVendu;
	
	public Enchere(int noEnchere, LocalDate dateEnchere, double montant_enchere, Utilisateur encherisseur, ArticleVendu articleVendu) {
		super();
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
		this.encherisseur = encherisseur;
		this.articleVendu = articleVendu;
	}

	public Enchere(LocalDate dateEnchere, double montant_enchere, Utilisateur encherisseur, ArticleVendu articleVendu) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
		this.encherisseur = encherisseur;
		this.articleVendu = articleVendu;
	}

	public int getNoEnchere() {
		return noEnchere;
	}

	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public double getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(double montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	public Utilisateur getEncherisseur() {
		return encherisseur;
	}

	public void setEncherisseur(Utilisateur encherisseur) {
		this.encherisseur = encherisseur;
	}
	

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	@Override
	public String toString() {
		return encherisseur.getPrenom() + " " + encherisseur.getNom() + " a enchéri " + montant_enchere + "€ pour " + articleVendu.getNomArticle() + ", le " + dateEnchere;
	}
}
