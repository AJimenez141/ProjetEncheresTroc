package fr.eni.projet.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ArticleVenduDALException;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.EnchereDALException;
import fr.eni.projet.dal.EnchereDAO;

public class EnchereManager {
	
	private EnchereDAO enchereDAO;
	private static EnchereManager instance;
	
	/**
	 * Recuperer categorieDAO via la Factory
	 */
	private EnchereManager() {
		enchereDAO = DAOFactory.recupererEnchereDAO();
	}
	
	public static EnchereManager getInstance() {
		if(instance==null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	/**
	 * Recupere toutes les encheres
	 * 
	 * @return List<Enchere>
	 * @throws EnchereDALException
	 * @throws SQLException
	 * @throws ArticleVenduDALException
	 */
	public List<Enchere> recupererLesEncheres() throws BLLException{
		try {
			return enchereDAO.selectAll();
		} catch (EnchereDALException e) {
			throw new BLLException(e.getMessage());
		}
	}
	
	/**
	 * Recupere une enchere en fonction de son ID
	 * 
	 * @param pEnchereId
	 * @return
	 * @throws EnchereDALException
	 * @throws SQLException
	 * @throws ArticleVenduDALException
	 */
	public Enchere recupererUneEnchere(int pEnchereId) throws BLLException {
		try {
			return enchereDAO.selectById(pEnchereId);
		} catch (EnchereDALException e) {
			throw new BLLException(e.getMessage());
		}
	}
	
	/**
	 * Recupere toutes les encheres d'un utilisateur
	 * à partir de son ID
	 * 
	 * @param pUtilisateurId
	 * @return List<Enchere>
	 * @throws EnchereDALException
	 * @throws SQLException
	 * @throws ArticleVenduDALException
	 */
	public List<Enchere> recupererLesEncheresUtilisateur(int pUtilisateurId) throws BLLException {
		try {
			return enchereDAO.selectEnchereByUtilisateur(pUtilisateurId);
		} catch (EnchereDALException e) {
			throw new BLLException(e.getMessage());
		}
	}
	
	/**
	 * Creer une enchere
	 * 
	 * 
	 * @param pEnchere
	 * @throws EnchereDALException
	 * @throws SQLException
	 * @throws ArticleVenduDALException
	 */
	public void creerEnchere(Enchere pEnchere) throws BLLException {
		validerEnchere(pEnchere);
		try {
			enchereDAO.creerEnchere(pEnchere);
		} catch (EnchereDALException e) {
			throw new BLLException(e.getMessage());
		}
	}
	
	/**
	 * Recuperer l'enchere la plus haute
	 * 
	 * @param pEnchereId
	 * @return 
	 * @throws EnchereDALException
	 * @throws SQLException
	 */
	public Enchere recupererEnchereLaPlusHaute(int pArticleVenduId) throws BLLException {
		try {
			return enchereDAO.selectMaxEnchereByArticle(pArticleVenduId);
		} catch (EnchereDALException e) {
			throw new BLLException(e.getMessage());
		}
	}
	
	private void validerEnchere(Enchere pEnchere) throws BLLException{
		
		StringBuilder sb = new StringBuilder();
		
		Utilisateur encherisseur = pEnchere.getEncherisseur();
		
		ArticleVendu articleVendu = pEnchere.getArticleVendu();
		
		Enchere enchereMax = recupererEnchereLaPlusHaute(articleVendu.getNoArticle());
		
		
		//Check crédits suffisants
		if((encherisseur.getCredit() - pEnchere.getMontant_enchere()) < 0) {
			sb.append("Pas assez de crédits pour cette enchère. \n");
		}
		
		//Check montant supérieur à prix initial
		if(pEnchere.getMontant_enchere() < articleVendu.getMiseAPrix()) {
			sb.append("Le montant doit être supérieur au prix initial. \n");
		}
		
		//Check montant enchère supérieure
		if(enchereMax != null) {
			if(pEnchere.getMontant_enchere() < enchereMax.getMontant_enchere()) {
				sb.append("L'enchère doit être supérieure à la dernière enchère. \n");
			}
		}
		
		if(sb.length()>0) {
			throw new BLLException(sb.toString());
		}
	}
}