package fr.eni.projet.bo;

public class Retrait {
	
	Adresse adresse;
	ArticleVendu articleVendu;

	public Retrait(Adresse adresse, ArticleVendu articleVendu) {
		super();
		this.adresse = adresse;
		this.articleVendu = articleVendu;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	@Override
	public String toString() {
		return "Adresse de retrait pour l'article " + articleVendu.getNomArticle() + ": " + adresse.getRue() + " " + adresse.getCodePostal() + ", " + adresse.getVille();
	}
}
