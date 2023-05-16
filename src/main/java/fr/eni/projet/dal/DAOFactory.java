package fr.eni.projet.dal;

import fr.eni.dal.jdbc.ArticleVenduDAOJdbcImpl;
import fr.eni.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.dal.jdbc.UtilisateurDAOJdbcImpl;
import fr.eni.dal.jdbc.CategorieDAOJdbcImpl;

public abstract class DAOFactory {
	/**
	 * recuperer l'instance de UtilisateurDAOJdbcImpl
	 * 
	 * @return
	 */
	public static UtilisateurDAO recupererUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	/**
	 * recuperer l'instance de ArticleVenduDAOJdbcImpl
	 * 
	 * @return
	 */
	public static ArticleVenduDAO recupererArticleVenduDAO() {
		return new ArticleVenduDAOJdbcImpl();
	}
	
	/**
	 * recuperer l'instance de EnchereDAOJdbcImpl
	 * 
	 * @return
	 */
	public static EnchereDAO recupererEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}
	
	public static CategorieDAO recupererCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
}
