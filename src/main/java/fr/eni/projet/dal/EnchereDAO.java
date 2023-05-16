package fr.eni.projet.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.Enchere;

public interface EnchereDAO {
	
//	/**
//	 * selectionner un article
//	 * 
//	 * @param pArticleVenduId
//	 * @return
//	 * @throws ArticleVenduDALException
//	 */
//	public ArticleVendu selectArticleById(int pArticleVenduId) throws ArticleVenduDALException;
	
	/**
	 * selectionner une enchere
	 * 
	 * @param pArticleVenduId
	 * @return
	 * @throws EnchereDALException
	 */
	public Enchere selectById(int pArticleVenduId) throws EnchereDALException,SQLException,ArticleVenduDALException;
	
	/**
	 * selectionner toutes les encheres en cours
	 * 
	 * @return
	 * @throws EnchereDALException
	 */
	public List<Enchere> selectAll() throws EnchereDALException, SQLException, ArticleVenduDALException;
	
	/**
	 * selectionner les encheres d'un utilisateur
	 * 
	 * @param pUtilisateurId
	 * @return
	 * @throws EnchereDALException
	 */
	public List<Enchere> selectEnchereByUtilisateur(int pUtilisateurId) throws EnchereDALException,SQLException, ArticleVenduDALException;
	
	/**
	 * effectuer une enchere
	 * 
	 * @param pEnchereId
	 * @throws EnchereDALException
	 */
	public void encherir(Enchere pEnchere) throws EnchereDALException,SQLException;	
	
	/**
	 * Creer une nouvelle enchere
	 * 
	 * @param pEnchere
	 * @throws EnchereDALException
	 * @throws SQLException
	 * @throws ArticleVenduDALException
	 */
	public void creerEnchere(Enchere pEnchere) throws EnchereDALException, SQLException, ArticleVenduDALException;
}
