package fr.eni.projet.dal;

import java.util.List;

import fr.eni.projet.bo.Utilisateur;

public interface UtilisateurDAO {
	/**
	 * selectionner un utilisateur par son id
	 * 
	 * @param pUtilisateurId
	 * @return
	 * @throws UtilisateurDALException
	 */
	public Utilisateur selectById(int pUtilisateurId) throws UtilisateurDALException;
	
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
	 */
	public void supprimerUtilisateur(int pUtilisateurId) throws UtilisateurDALException;
	
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
	 */
	public void modifierUtilisateur(Utilisateur pUtilisateur) throws UtilisateurDALException;
	
	/**
	 * modifier les credits de l'utilisateur
	 * 
	 * @param pUtilisateurId
	 * @throws UtilisateurDALException
	 */
	public void modifierCredit(int pUtilisateurId) throws UtilisateurDALException;
}
