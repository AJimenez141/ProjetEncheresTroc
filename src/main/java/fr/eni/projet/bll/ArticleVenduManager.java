package fr.eni.projet.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.dal.ArticleVenduDALException;
import fr.eni.projet.dal.ArticleVenduDAO;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.UtilisateurDALException;

public class ArticleVenduManager {
	
	private ArticleVenduDAO articleVenduDAO;
	
	/**
	 * Recuperer articleVenduDAO via la Factory
	 */
	public ArticleVenduManager() {
		this.articleVenduDAO=DAOFactory.recupererArticleVenduDAO();
	}
	
	/**
	 * Ajout d'un article
	 * 
	 * @throws ArticleVenduDALException
	 */
	public void ajouterArticleVendu(ArticleVendu articleVendu) throws ArticleVenduDALException, SQLException 
	{
		this.articleVenduDAO.insererArticleVendu(articleVendu);
	}
	
	/**
	 * Suppression d'un article
	 * 
	 * @throws ArticleVenduDALException
	 */
	public void supprimerArticleVendu(int pIdArticleVendu) throws ArticleVenduDALException, SQLException 
	{
		this.articleVenduDAO.supprimerArticleVendu(pIdArticleVendu);
	}
	
	/**
	 * Recuperation d'un article
	 * 
	 * @return ArticleVendu
	 * @throws ArticleVenduDALException
	 */
	public ArticleVendu recupererUnArticleVendu(int pIdArticleVendu) throws ArticleVenduDALException, SQLException {
		return this.articleVenduDAO.selectById(pIdArticleVendu);
	}
	
	/**
	 * Recuperation de tous les articles
	 * 
	 * @return List<ArticleVendu>
	 * @throws ArticleVenduDALException
	 */
	public List<ArticleVendu> recupererLesArticlesVendus() throws ArticleVenduDALException, SQLException {
		return this.articleVenduDAO.selectAll();
	}

	/**
	 * Recuperation de tous les article d'une categorie
	 * 
	 * @return List<ArticleVendu>
	 * @throws ArticleVenduDALException
	 */
	public List<ArticleVendu> recupererLesArticlesVendusParCategorie(int pNoCategorie) throws ArticleVenduDALException, SQLException  {
		return this.articleVenduDAO.selectionnerParCategorie(pNoCategorie);
	}
	
	/**
	 * Recuperation de tous les articles d'un utilisateur
	 * 
	 * @return List<ArticleVendu>
	 * @throws ArticleVenduDALException
	 */
	public List<ArticleVendu> recupererLesArticlesVendusParUtilisateur(int pNoUtilisateur) throws ArticleVenduDALException, SQLException  {
		return this.articleVenduDAO.selectArticleVenduByUtilisateur(pNoUtilisateur);
	}
}
