package fr.eni.projet.bll;

import java.sql.SQLException;

import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Retrait;
import fr.eni.projet.dal.ArticleVenduDALException;
import fr.eni.projet.dal.DALException;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.RetraitDALException;
import fr.eni.projet.dal.RetraitDAO;

public class RetraitManager {
	private RetraitDAO retraitDAO;
	private static RetraitManager instance;
	
	/**
	 * Recupere RetraitDAO via la Factory
	 */
	public RetraitManager() {
		this.retraitDAO = DAOFactory.recupererRetraitDAO();
	}
	
	public static RetraitManager getInstance() {
		if(instance==null) {
			instance = new RetraitManager();
		}
		return instance;
	}
	
	/**
	 * Creation d'un retrait
	 * 
	 * TODO - faire des verification avant l'ajout en BDD
	 * 
	 * @param pRetrait
	 * @throws BLLException
	 */
	public void creerRetrait(Retrait pRetrait) throws BLLException {
		try {
			retraitDAO.creerRetraitArticle(pRetrait);
		} catch (RetraitDALException e) {
			throw new BLLException(e.getMessage());
		}
	}
	
	/**
	 * Récupération d'un retrait par num d'article
	 * 
	 * @param pRetrait
	 * @throws BLLException
	 */
	public Retrait selectionnerRetraitArticle(int pArticleVenduId) throws BLLException {
		
		ArticleVendu articleVendu = null;
		Retrait retrait = null;
		
		try {
			articleVendu = ArticleVenduManager.getInstance().recupererUnArticleVendu(pArticleVenduId);
		} catch (ArticleVenduDALException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			retrait = retraitDAO.selectRetraitArticle(pArticleVenduId);
			
			//Si il y a un point de retrait, on le récupère
			if (retrait != null) {
				return retrait;
			}
			//Si il n'y a pas de point de retrait, on prend l'adresse du vendeur
			else if (articleVendu != null) {
				return new Retrait(articleVendu.getVendeur().getAdresse(), articleVendu);
			//Sinon renvoyer null
			} else {
				return null;
			}
			
		} catch (RetraitDALException e) {
			throw new BLLException(e.getMessage());
		}
		
	}
}
 