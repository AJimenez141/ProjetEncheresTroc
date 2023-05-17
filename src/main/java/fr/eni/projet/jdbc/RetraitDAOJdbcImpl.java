package fr.eni.projet.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.Retrait;
import fr.eni.projet.dal.ConnectionProvider;
import fr.eni.projet.dal.DALException;
import fr.eni.projet.dal.RetraitDALException;
import fr.eni.projet.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	
//	Requetes
	private final String INSERT_RETRAIT = "INSERT INTO RETRAIT(rue,code_postal, ville) VALUES (?,?,?)";

	@Override
	public void creerRetraitArticle(Retrait pRetrait) throws RetraitDALException, DALException, SQLException {
		try(
			Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pStmt = connexion.prepareStatement(INSERT_RETRAIT);		
		){
			Adresse pRetraitAdresse = pRetrait.getAdresse();
			
			pStmt.setString(1, pRetraitAdresse.getRue());
			pStmt.setString(2, pRetraitAdresse.getCodePostal());
			pStmt.setString(3, pRetraitAdresse.getVille());
			pStmt.setInt(4, pRetrait.getArticleVendu().getNoArticle());
			
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RetraitDALException("Impossible de créer le retrait");
		}
	}

}