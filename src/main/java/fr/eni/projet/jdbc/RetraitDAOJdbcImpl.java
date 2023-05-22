package fr.eni.projet.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Retrait;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ArticleVenduDALException;
import fr.eni.projet.dal.ConnectionProvider;
import fr.eni.projet.dal.DALException;
import fr.eni.projet.dal.EnchereDALException;
import fr.eni.projet.dal.RetraitDALException;
import fr.eni.projet.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	
//	Requetes
	private final String INSERT_RETRAIT = "INSERT INTO RETRAITS(rue,code_postal,ville, no_article) VALUES (?,?,?,?)";
	
	private static final String SELECT_RETRAIT_BY_ID = "SELECT * FROM RETRAITS WHERE no_article = ?";

	@Override
	public void creerRetraitArticle(Retrait pRetrait) throws RetraitDALException {
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
			throw new RetraitDALException("Impossible de créer le retrait", e);
		}
	}

	@Override
	public Retrait selectRetraitArticle(int pArticleVenduId) throws RetraitDALException {


		Retrait article = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pStmt = connexion.prepareStatement(SELECT_RETRAIT_BY_ID);
		) {
			pStmt.setInt(1, pArticleVenduId);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next() ) {

				String rue					= rs.getString("rue");
				String code_postal			= rs.getString("code_postal");
				String ville				= rs.getString("ville");
				
				Adresse adresseVendeur 	= new Adresse(rue, code_postal, ville);
				
				article = new Retrait(adresseVendeur, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RetraitDALException("Impossible de sélectionner le point de retrait dans la base",e);
		}
		return article;
	}

}