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
	public Enchere selectById(int pArticleVenduId) throws EnchereDALException;
	
	/**
	 * selectionner toutes les encheres en cours
	 * 
	 * @param pUtilisateurId
	 * @return Enchere
	 * @throws EnchereDALException
	 */
	public List<Enchere> selectAll() throws EnchereDALException;
	
	/**
	 * selectionner les encheres d'un utilisateur
	 * 
	 * @throws EnchereDALException
	 */
	public List<Enchere> selectEnchereByUtilisateur(int pUtilisateurId) throws EnchereDALException;
	
	/**
	 * Creer une nouvelle enchere
	 * 
	 * @param pUtilisateurId
	 * @throws EnchereDALException
	 * @throws SQLException
	 * @throws ArticleVenduDALException
	 */
	public void creerEnchere(Enchere pEnchere) throws EnchereDALException;
	
	/**
	 * selectionner l'enchère la plus haute en cours pour un article donné
	 * 
	 * @param pArticleVenduId
	 * @throws EnchereDALException
	 * @throws SQLException
	 * @throws ArticleVenduDALException
	 */
	public Enchere selectMaxEnchereByArticle(int pArticleVenduId) throws EnchereDALException;
}
