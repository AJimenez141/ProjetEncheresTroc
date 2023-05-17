package fr.eni.projet.bll;

import fr.eni.projet.dal.ConnexionException;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.UtilisateurDALException;
import fr.eni.projet.dal.UtilisateurDAO;

import fr.eni.projet.bo.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;
	
	/**
	 * Recuperer utilisateurDAO via la Factory
	 */
	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.recupererUtilisateurDAO();
	}
	
	/**
	 * Recuperation de tous les utilisateurs
	 * 
	 * @return List<Utilisateur>
	 * @throws UtilisateurDALException
	 */
	public List<Utilisateur> recupererLesUtilisateurs() throws UtilisateurDALException {
		return this.utilisateurDAO.selectAll();
	}
	
	/**
	 * Recuperation d'un utilisateur via son ID
	 * 
	 * @param pIdUtilisateur
	 * @return Utilisateur
	 * @throws UtilisateurDALException
	 * @throws SQLException
	 */
	public Utilisateur recupererUnUtilisateur(int pIdUtilisateur) throws UtilisateurDALException, SQLException {
		return this.utilisateurDAO.selectById(pIdUtilisateur);
	}
	
	/**
	 * Insertion d'un utilisateur dans la BDD
	 * 
	 * TODO - effectuer des controles avant mis en bdd
	 * 
	 * @param pUtilisateur
	 * @throws UtilisateurDALException
	 */
	public void insererUtilisateur(Utilisateur pUtilisateur) throws UtilisateurDALException {
		this.utilisateurDAO.insererUtilisateur(pUtilisateur);
	}
	
	/**
	 * Modification d'un utilisateur
	 * 
	 * TODO - effectuer des controles avant modification dans la bdd
	 * 
	 * @param pUtilisateur
	 * @throws UtilisateurDALException
	 * @throws SQLException
	 */
	public void modifierUtilisateur(Utilisateur pUtilisateur) throws UtilisateurDALException, SQLException {
		this.utilisateurDAO.modifierUtilisateur(pUtilisateur);
	}
	
	/**
	 * Suppression d'un utilisateur
	 * 
	 * TODO - NE PAS OUBLIER DE DEMANDER UNE CONFIRMATION
	 * AU PREALABLE
	 * 
	 * @param pUtilisateurId
	 * @throws UtilisateurDALException
	 * @throws SQLException
	 */
	public void supprimerUtilisateur(int pUtilisateurId) throws UtilisateurDALException, SQLException {
		this.utilisateurDAO.supprimerUtilisateur(pUtilisateurId);
	}
	
	/**
	 * Recuperer l'utilisateur au moment de la connexion
	 * 
	 * TODO - faire les controles au moment de la connexion
	 * TODO - methode dans le DAOJdbcImpl possiblement à revoir,
	 * ajout d'un token en front ?
	 * 
	 * @param pEmailOrPseudo
	 * @param pMotDePasse
	 * @return Utilisateur
	 * @throws UtilisateurDALException
	 * @throws SQLException
	 * @throws ConnexionException
	 */
	public Utilisateur seConnecter(String pEmailOrPseudo, String pMotDePasse) throws UtilisateurDALException, SQLException {
		Utilisateur utilisateurConnecte;
		
		if(this.utilisateurDAO.seConnecterEmail(pEmailOrPseudo, pMotDePasse)  != null) {
			utilisateurConnecte = this.utilisateurDAO.seConnecterEmail(pEmailOrPseudo, pMotDePasse);
		} else {
			utilisateurConnecte = this.utilisateurDAO.seConnecterPseudo(pEmailOrPseudo, pMotDePasse);
		}
		
		return utilisateurConnecte;
	}

}
