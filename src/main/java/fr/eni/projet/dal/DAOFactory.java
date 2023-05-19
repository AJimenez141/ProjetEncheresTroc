package fr.eni.projet.dal;

import fr.eni.projet.jdbc.ArticleVenduDAOJdbcImpl;
import fr.eni.projet.jdbc.CategorieDAOJdbcImpl;
import fr.eni.projet.jdbc.EnchereDAOJdbcImpl;
import fr.eni.projet.jdbc.RetraitDAOJdbcImpl;
import fr.eni.projet.jdbc.UtilisateurDAOJdbcImpl;

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
	
	/**
	 * recuperer l'instance de CategogieDAOJdbcImpl
	 * 
	 * @return
	 */
	public static CategorieDAO recupererCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
	
	/**
	 * recuperer l'instance de RetraitDAOJdbcImpl
	 * 
	 * @return
	 */
	public static RetraitDAO recupererRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}
}
