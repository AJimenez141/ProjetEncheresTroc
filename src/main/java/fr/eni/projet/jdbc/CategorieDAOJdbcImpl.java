package fr.eni.projet.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Categorie;
import fr.eni.projet.dal.CategorieDALException;
import fr.eni.projet.dal.CategorieDAO;
import fr.eni.projet.dal.ConnectionProvider;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	
//	Liste des requetes
	private final String SELECT_CATEGORIE_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie = (?)";
	
	private final String SELECT_ALL_CATEGORIE = "SELECT * FROM CATEGORIES";
	
	private final String INSERT_CATEGORIE = "INSERT INTO CATEGORIES(libelle) VALUES(?)";
	
	private final String DELETE_CATEGORIE = "DELETE FROM CATEGORIES WHERE no_categorie = (?)";
	
	private final String UPDATE_CATEGORIE = "UPDATE CATEGORIES SET libelle = (?) WHERE no_categorie = (?)";

	@Override
	public Categorie selectById(int pCategorieId) throws CategorieDALException {
		
		Categorie categorie = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
		){
			
			PreparedStatement pStmt = connexion.prepareStatement(SELECT_CATEGORIE_BY_ID);
			pStmt.setInt(1, pCategorieId);
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				int noCategorie		= rs.getInt("no_categorie");
				String libelle		= rs.getString("libelle");
				
				categorie = new Categorie(noCategorie, libelle);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CategorieDALException("Impossible de sélectionner cette categorie dans la base");
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


		try(
				Connection connexion = ConnectionProvider.getConnection();
				PreparedStatement pStmt = connexion.prepareStatement(INSERT_CATEGORIE, Statement.RETURN_GENERATED_KEYS);
			){
				
				pStmt.setString(1, pCategorie.getLibelle());
				
				pStmt.executeUpdate();
				
				ResultSet rs = pStmt.getGeneratedKeys();
				if(rs.next()) {
					pCategorie.setNoCategorie(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new CategorieDALException("Impossible d'insérer la catégorie",e);
			}
	}

	@Override
	public void supprimerCategorie(int pCategorieId) throws CategorieDALException {
		try(
				Connection connexion = ConnectionProvider.getConnection();
				PreparedStatement pStmt = connexion.prepareStatement(DELETE_CATEGORIE);
			){
				pStmt.setInt(1, pCategorieId);
				pStmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new CategorieDALException("Une erreur est survenue lors de la suppression de la categorie");
			}	
	}

	@Override
	public void modifierCategorie(Categorie pCategorie) throws CategorieDALException {

		try(
				Connection connexion = ConnectionProvider.getConnection();	
			){
				PreparedStatement pStmt = connexion.prepareStatement(UPDATE_CATEGORIE);
				pStmt.setString(1, pCategorie.getLibelle());
				pStmt.setInt(2, pCategorie.getNoCategorie());
				pStmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new CategorieDALException("Impossible de mettre à jour la categorie");
			}		
	}
}
