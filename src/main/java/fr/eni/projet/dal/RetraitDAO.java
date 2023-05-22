package fr.eni.projet.dal;

import java.sql.SQLException;

import fr.eni.projet.bo.Retrait;

public interface RetraitDAO {
	
	/**
	 * creer un retrait
	 * 
	 * @return
	 * @throws RetraitDALException
	 * @throws DALException
	 * @throws SQLException
	 */
	public void creerRetraitArticle(Retrait pRetrait) throws RetraitDALException;

}