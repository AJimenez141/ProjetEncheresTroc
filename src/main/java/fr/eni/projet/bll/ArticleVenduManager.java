package fr.eni.projet.bll;

import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.dal.ArticleVenduDALException;
import fr.eni.projet.dal.ArticleVenduDAO;
import fr.eni.projet.dal.DAOFactory;

public class ArticleVenduManager {
	
	private ArticleVenduDAO articleVenduDAO;
	private static ArticleVenduManager instance;
	
	/**
	 * Recuperer categorieDAO via la Factory
	 */
	private ArticleVenduManager() {
		articleVenduDAO = DAOFactory.recupererArticleVenduDAO();
	}
	
	public static ArticleVenduManager getInstance() {
		if(instance==null) {
			instance = new ArticleVenduManager();
		}
		return instance;
	}
	
	/**
	 * Ajout d'un article
	 * 
	 * @throws ArticleVenduDALException
	 */
	public void ajouterArticleVendu(ArticleVendu articleVendu) throws BLLException 
	{
		validerArticleVendu(articleVendu);
		try {
			this.articleVenduDAO.insererArticleVendu(articleVendu);
		} catch (ArticleVenduDALException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Suppression d'un article
	 * 
	 * @throws ArticleVenduDALException
	 */
	public void supprimerArticleVendu(int pIdArticleVendu) throws BLLException 
	{
		try {
			this.articleVenduDAO.supprimerArticleVendu(pIdArticleVendu);
		} catch (ArticleVenduDALException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Recuperation d'un article
	 * 
	 * @return ArticleVendu
	 * @throws ArticleVenduDALException
	 */
	public ArticleVendu recupererUnArticleVendu(int pIdArticleVendu) throws BLLException {
		ArticleVendu articleVendu = null;
		try {
			articleVendu = this.articleVenduDAO.selectById(pIdArticleVendu);
		} catch (ArticleVenduDALException e) {
			e.printStackTrace();
		}
		return articleVendu;
	}
	
	/**
	 * Recuperation de tous les articles
	 * 
	 * @return List<ArticleVendu>
	 * @throws ArticleVenduDALException
	 */
	public List<ArticleVendu> recupererLesArticlesVendus() throws BLLException {
		List<ArticleVendu> articlesVendus = new ArrayList<>();
		try {
			articlesVendus = this.articleVenduDAO.selectAll();
		} catch (ArticleVenduDALException e) {
			e.printStackTrace();
		}
		return articlesVendus;
	}

	/**
	 * Recuperation de tous les article d'une categorie
	 * 
	 * @return List<ArticleVendu>
	 * @throws ArticleVenduDALException
	 */
	public List<ArticleVendu> recupererLesArticlesVendusParCategorie(int pNoCategorie) throws BLLException {
		List<ArticleVendu> articlesVendus = new ArrayList<>();
		try {
			articlesVendus = this.articleVenduDAO.selectionnerParCategorie(pNoCategorie);
		} catch (ArticleVenduDALException e) {
			e.printStackTrace();
		}
		return articlesVendus;
	}
	
	/**
	 * Recuperation de tous les articles d'un utilisateur
	 * 
	 * @return List<ArticleVendu>
	 * @throws ArticleVenduDALException
	 */
	public List<ArticleVendu> recupererLesArticlesVendusParUtilisateur(int pNoUtilisateur) throws BLLException  {
		List<ArticleVendu> articlesVendus = new ArrayList<>();
		try {
			articlesVendus = this.articleVenduDAO.selectArticleVenduByUtilisateur(pNoUtilisateur);
		} catch (ArticleVenduDALException e) {
			e.printStackTrace();
		}
		return articlesVendus;
	}
	
	public void validerArticleVendu(ArticleVendu pArticleVendu) throws BLLException
	{
		StringBuilder sb = new StringBuilder();
		
//		Exception sur le nom de l'article
		if(pArticleVendu.getNomArticle() == null || pArticleVendu.getNomArticle().isBlank()) {
			sb.append("Merci de nommer l'article vendu.\n");
		}
		
//		Exception sur la description de l'article
		if(pArticleVendu.getDescription() == null || pArticleVendu.getDescription().isBlank()) {
			sb.append("L'article vendu doit avoir une description.\n");
		}
		
//		Exception sur la date de debut des encheres
		if(pArticleVendu.getDateDebutEncheres() == null) {
			sb.append("Merci de définir une date de début pour les enchères.\n");
		}
		
//		Exception sur la date de fin des encheres
		if(pArticleVendu.getDateFinEncheres() == null) {
			sb.append("Merci de définir une date de fin pour les enchères.\n");
		}
		
//		Verification si la date de debut est avant la date de fin
		if(Date.from(pArticleVendu.getDateDebutEncheres().atStartOfDay(ZoneId.systemDefault()).toInstant()).after(Date.from(pArticleVendu.getDateFinEncheres().atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
			sb.append("La date de début des enchère en doit pas être après la date de fin.\n");
		}
		
//		Verification si la date de fin est apres la date de debut
		if(pArticleVendu.getDateFinEncheres().isBefore(pArticleVendu.getDateDebutEncheres())) {
			sb.append("La date de fin des enchère en doit pas être avant la date de début.\n");
		}
		
//		Le prix de l'article doit etre superieur à zero
		if(pArticleVendu.getMiseAPrix() < 0) {
			sb.append("Le prix de l'article ne doit pas être inférieur à zéro.\n");
		}
		
//		Le prix de l'article de doit pas etre nul
		if(pArticleVendu.getMiseAPrix() == 0) {
			sb.append("L'article ne peut être donné, merci de définir un prix supérieur à zéro.\n");
		}
		
		if(sb.length() > 0) {
			throw new BLLException(sb.toString());
		}
		

	}
}
