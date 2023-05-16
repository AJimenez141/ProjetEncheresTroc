package fr.eni.projet.bo;

public class Utilisateur {
	
	int noUtilisateur;
	String pseudo;
	String nom;
	String prenom;
	String email;
	String motDePasse;
	String telephone;
	Adresse adresse;
	double credit;
	boolean administrateur;
	boolean actif;
	
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email,String telephone, Adresse adresse, double credit) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
		this.credit = credit;
		this.administrateur = false;
		this.actif = true;
	}
	
	public Utilisateur(String pseudo, String nom, String prenom, String email, String motDePasse, String telephone, Adresse adresse, double credit) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.telephone = telephone;
		this.adresse = adresse;
		this.credit = credit;
		this.administrateur = false;
		this.actif = true;
	}
	
	public Utilisateur(String pseudo, String nom, String prenom, String email,String telephone, Adresse adresse, double credit) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
		this.credit = credit;
		this.administrateur = false;
		this.actif = true;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public boolean isAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}
	
	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", motDePasse=" + motDePasse + ", telephone=" + telephone + ", adresse="
				+ adresse + ", credit=" + credit + ", administrateur=" + administrateur + ", actif=" + actif + "]";
	}

}
