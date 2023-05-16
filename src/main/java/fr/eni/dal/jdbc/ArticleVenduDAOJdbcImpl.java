package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.dal.ArticleVenduDALException;
import fr.eni.projet.dal.ArticleVenduDAO;
import fr.eni.projet.dal.ConnectionProvider;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	
	private static final String SELECT_ARTICLE_BY_ID = "";
	private static final String SELECT_ALL_ARTICLE = "";
	private static final String SELECT_ARTICLE_BY_UTILISATEUR = "";
	private static final String DELETE_ARTICLE = "";
	private static final String SELECT_ARTICLE_BY_CATEGORIE = "";
	private static final String INSERT_ARTICLE = "";

	@Override
	public ArticleVendu selectById(int pArticleVenduId) throws ArticleVenduDALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> selectAll() throws ArticleVenduDALException {
		
		List<ArticleVendu> articles = new ArrayList<>();
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			Statement pStmt = connexion.createStatement();
		) {
			ResultSet rs = pStmt.executeQuery(SELECT_ALL_ARTICLE);
			
			while(rs.next() ) {

				int noArticle 			= rs.getInt("no_article");
				String nomArticle 		= rs.getString("nom_article");
				String description 		= rs.getString("description");
				Date dateDebutEncheres 	= rs.getDate("date_debut_encheres");
				Date dateFinEncheres 	= rs.getDate("date_fin_encheres");
				Double miseAPrix 		= rs.getDouble("prix_initial");
				Double prixVente 		= rs.getDouble("prix_vente");
				boolean enVente 		= rs.getBoolean("en_vente");
				
				int noUtilisateur  		= rs.getInt("no_utilisateur");
				String pseudo			= rs.getString("pseudo");
				String nom				= rs.getString("nom");
				String prenom			= rs.getString("prenom");
				String email			= rs.getString("email");
				String telephone		= rs.getString("telephone");
				String rue				= rs.getString("rue");
				String code_postal		= rs.getString("code_postal");
				String ville			= rs.getString("ville");
				Double credit			= rs.getDouble("credit");
				
				int noCategorie			= rs.getInt("no_categorie");
				String libelle			= rs.getString("libelle");
				
				Adresse adresseVendeur = new Adresse(rue, code_postal, ville);
				Utilisateur vendeur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, adresseVendeur, credit);
				Categorie categorie = new Categorie(noCategorie, libelle);
				
				articles.add(new ArticleVendu(noArticle,nomArticle,description,dateDebutEncheres,dateFinEncheres,miseAPrix,prixVente,enVente,vendeur,categorie));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ArticleVenduDALException("Impossible de sélectionner les articles dans la base",e);
		}
		
		return articles;
	}

	@Override
	public List<ArticleVendu> selectArticleVenduByUtilisateur(int pUtilisateurId) throws ArticleVenduDALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerArticleVendu(int pArticleVenduId) throws ArticleVenduDALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ArticleVendu> selectionnerParCategorie(String pLibelleCategorie) throws ArticleVenduDALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insererArticleVendu(ArticleVendu pArticleVendu) throws ArticleVenduDALException {
		// TODO Auto-generated method stub
		
	}

}
