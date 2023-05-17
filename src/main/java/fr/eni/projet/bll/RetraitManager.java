package fr.eni.projet.bll;

import java.sql.SQLException;

import fr.eni.projet.bo.Retrait;
import fr.eni.projet.dal.DALException;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.RetraitDALException;
import fr.eni.projet.dal.RetraitDAO;

public class RetraitManager {
	private RetraitDAO retraitDAO;
	
	/**
	 * Recupere RetraitDAO via la Factory
	 */
	public RetraitManager() {
		this.retraitDAO = DAOFactory.recupererRetraitDAO();
	}
	
	/**
	 * Creation d'un retrait
	 * 
	 * TODO - faire des verification avant l'ajout en BDD
	 * 
	 * @param pRetrait
	 * @throws RetraitDALException
	 * @throws DALException
	 * @throws SQLException
	 */
	public void creerRetrait(Retrait pRetrait) throws RetraitDALException, DALException, SQLException {
		this.retraitDAO.creerRetraitArticle(pRetrait);
	}
}
 