package fr.eni.projet.dal;

import java.util.List;

import fr.eni.projet.bo.ArticleVendu;

public interface ArticleVenduDAO {

	/**
	 * selectionner un article
	 * 
	 * @param pArticleVenduId
	 * @return
	 * @throws ArticleVenduDALException
	 */
	public ArticleVendu selectById(int pArticleVenduId) throws ArticleVenduDALException;
	
	/**
	 * selectionner tous les ArticleVendu
	 * 
	 * @return
	 * @throws ArticleVenduDALException
	 */
	public List<ArticleVendu> selectAll() throws ArticleVenduDALException;
	
	/**
	 * lister les ventes d'un utilisateur
	 * 
	 * @param pUtilisateurId
	 * @return
	 * @throws ArticleVenduDALException
	 */
	public List<ArticleVendu> selectArticleVenduByUtilisateur(int pUtilisateurId) throws ArticleVenduDALException;
	
	/**
	 * ajouter une vente
	 * 
	 * @param pArticleVendu
	 * @throws ArticleVenduDALException
	 */
	public void insererArticleVendu(ArticleVendu pArticleVendu) throws ArticleVenduDALException;
	
	/**
	 * annuler une vente
	 * 
	 * @param pArticleVenduId
	 * @throws ArticleVenduDALException
	 */
	public void supprimerArticleVendu(int pArticleVenduId) throws ArticleVenduDALException;
	
	/**
	 * selectionner des articles par categorie
	 * 
	 * @param pLibelleCategorie
	 * @return
	 * @throws ArticleVenduDALException
	 */
	public List<ArticleVendu> selectionnerParCategorie(int pNoCategorie) throws ArticleVenduDALException;
	
}
