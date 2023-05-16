package fr.eni.projet.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.Utilisateur;

public interface UtilisateurDAO {
	/**
	 * selectionner un utilisateur par son id
	 * 
	 * @param pUtilisateurId
	 * @return
	 * @throws UtilisateurDALException
	 * @throws SQLException 
	 */
	public Utilisateur selectById(int pUtilisateurId) throws UtilisateurDALException, SQLException;
	
	/**
	 * selectionner tous les utilisateurs
	 * 
	 * @return
	 * @throws UtilisateurDALException
	 */
	public List<Utilisateur> selectAll() throws UtilisateurDALException;
	
	/**
	 * inserer un nouvel utilisateur
	 * 
	 * @param pUtilisateur
	 * @throws UtilisateurDALException
	 */
	public void insererUtilisateur(Utilisateur pUtilisateur) throws UtilisateurDALException;
	
	/**
	 * supprimer un utilisateur
	 * 
	 * @param pUtilisateurId
	 * @throws UtilisateurDALException
	 * @throws SQLException 
	 */
	public void supprimerUtilisateur(int pUtilisateurId) throws UtilisateurDALException, SQLException;
	
	/**
	 * desactiver un utilisateur
	 * 
	 * @param pUtilisateurId
	 * @throws UtilisateurDALException
	 */
//	public void desactiverUtilisateur(int pUtilisateurId) throws UtilisateurDALException;
	
	/**
	 * modifier un utilisateur
	 * 
	 * @param pUtilisateur
	 * @throws UtilisateurDALException
	 * @throws SQLException 
	 */
	public void modifierUtilisateur(Utilisateur pUtilisateur) throws UtilisateurDALException, SQLException;
	
	/**
	 * connexion de l'utilisateur
	 * 
	 * @param pEmail
	 * @param pMotDePasse
	 * @return 
	 * @throws UtilisateurDALException
	 * @throws SQLException
	 * @throws ConnexionException
	 */
	public Utilisateur seConnecter(String pEmail, String pMotDePasse) throws UtilisateurDALException, SQLException, ConnexionException;
	
}
