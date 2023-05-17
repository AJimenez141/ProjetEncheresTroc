package fr.eni.projet.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.Enchere;
import fr.eni.projet.dal.ArticleVenduDALException;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.EnchereDALException;
import fr.eni.projet.dal.EnchereDAO;

public class EnchereManager {
	private EnchereDAO enchereDAO;
	
	/**
	 * Recuperer enchereDAO via la Factory
	 */
	public EnchereManager() {
		this.enchereDAO = DAOFactory.recupererEnchereDAO();
	}
	
	/**
	 * Recupere toutes les encheres
	 * 
	 * @return List<Enchere>
	 * @throws EnchereDALException
	 * @throws SQLException
	 * @throws ArticleVenduDALException
	 */
	public List<Enchere> recupererLesEncheres() throws EnchereDALException, SQLException, ArticleVenduDALException{
		return this.enchereDAO.selectAll();
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
	public Enchere recupererUneEnchere(int pEnchereId) throws EnchereDALException, SQLException, ArticleVenduDALException {
		return this.enchereDAO.selectById(pEnchereId);
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
	public List<Enchere> recupererLesEncheresUtilisateur(int pUtilisateurId) throws EnchereDALException, SQLException, ArticleVenduDALException {
		return this.enchereDAO.selectEnchereByUtilisateur(pUtilisateurId);
	}
	
	/**
	 * Creer une enchere
	 * 
	 * TODO - faire des verifications avant ajout d'une enchere
	 * en bdd
	 * 
	 * @param pEnchere
	 * @throws EnchereDALException
	 * @throws SQLException
	 * @throws ArticleVenduDALException
	 */
	public void creerEnchere(Enchere pEnchere) throws EnchereDALException, SQLException, ArticleVenduDALException {
		this.enchereDAO.creerEnchere(pEnchere);
	}
}