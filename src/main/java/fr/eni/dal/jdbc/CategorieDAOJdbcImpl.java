package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.CategorieDALException;
import fr.eni.projet.dal.CategorieDAO;
import fr.eni.projet.dal.ConnectionProvider;
import fr.eni.projet.dal.UtilisateurDALException;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	
//	Liste des requetes
	private final String SELECT_CATEGORIE_BY_ID = "SELECT * FROM CATEGORIE WHERE no_categorie ?";
	
	private final String SELECT_ALL_CATEGORIE = "SELECT * FROM CATEGORIE";
	
	private final String INSERT_CATEGORIE = "INSERT INTO CATEGORIE(libelle) VALUES (?)";
	
	private final String DELETE_CATEGORIE = "DELETE FROM CATEGORIE WHERE no_categorie = ?";
	
	private final String UPDATE_CATEGORIE = "UPDATE CATEGORIE SET libelle = ? WHERE no_categorie = ?";

	@Override
	public Categorie selectById(int pCategorieId) throws CategorieDALException, SQLException {
		
		Categorie categorie = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pStmt = connexion.prepareStatement(SELECT_CATEGORIE_BY_ID);
		){
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				int noCategorie		= rs.getInt("no_categorie");
				String libelle		= rs.getString("libelle");
				
				categorie = new Categorie(noCategorie, libelle);
			}
			
		}
		return categorie;
	}

	@Override
	public List<Categorie> SelectAll() throws CategorieDALException {
		
		List<Categorie> categories = new ArrayList<>();
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			Statement pStmt = connexion.createStatement();
		) {
			ResultSet rs = pStmt.executeQuery(SELECT_ALL_CATEGORIE);
			
			while(rs.next() ) {
				int noCategorie		= rs.getInt("no_categorie");
				String libelle		= rs.getString("libelle");
				
				categories.add(new Categorie(noCategorie, libelle));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CategorieDALException("Impossible de sélectionner les categories dans la base");
		}
		
		return categories;
	}

	@Override
	public void insererCategorie(Categorie pCategorie) throws CategorieDALException {
		// TODO Auto-generated method stub
	}

	@Override
	public void supprimerCategorie(int pCategorieId) throws CategorieDALException {
		// TODO Auto-generated method stub
	}

	@Override
	public void modifierCategorie(int pCategorieId) throws CategorieDALException {
		// TODO Auto-generated method stub
	}
}
