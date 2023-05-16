package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ArticleVenduDALException;
import fr.eni.projet.dal.ConnectionProvider;
import fr.eni.projet.dal.EnchereDALException;
import fr.eni.projet.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String SELECT_ALL_ENCHERES = "SELECT * FROM ENCHERES AS E INNER JOIN UTILISATEURS AS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AS A ON A.no_article = E.no_article";
	
	private static final String SELECT_ARTICLE_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ? "
			 + "INNER JOIN UTILISATEURS ON UTILISATEUR.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
			 + "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie ";

	private static ArticleVendu selectArticleById(int pArticleVenduId) throws ArticleVenduDALException {
		ArticleVendu article = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			Statement pStmt = connexion.createStatement();
		) {
			ResultSet rs = pStmt.executeQuery(SELECT_ARTICLE_BY_ID);
			
			while(rs.next() ) {

				int noArticle 				= rs.getInt("no_article");
				String nomArticle 			= rs.getString("nom_article");
				String description 			= rs.getString("description");
				LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFinEncheres 	= rs.getDate("date_fin_encheres").toLocalDate();
				int miseAPrix 				= rs.getInt("prix_initial");
				int prixVente 				= rs.getInt("prix_vente");
				boolean enVente 			= rs.getBoolean("en_vente");
				
				int noUtilisateur  			= rs.getInt("no_utilisateur");
				String pseudo				= rs.getString("pseudo");
				String nom					= rs.getString("nom");
				String prenom				= rs.getString("prenom");
				String email				= rs.getString("email");
				String telephone			= rs.getString("telephone");
				String rue					= rs.getString("rue");
				String code_postal			= rs.getString("code_postal");
				String ville				= rs.getString("ville");
				int credit					= rs.getInt("credit");
				
				int noCategorie				= rs.getInt("no_categorie");
				String libelle				= rs.getString("libelle");
				
				Adresse adresseVendeur 	= new Adresse(rue, code_postal, ville);
				Utilisateur vendeur 	= new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, adresseVendeur, credit);
				Categorie categorie 	= new Categorie(noCategorie, libelle);
				
				article = new ArticleVendu(noArticle,nomArticle,description,dateDebutEncheres,dateFinEncheres,miseAPrix,prixVente,enVente,vendeur,categorie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ArticleVenduDALException("Impossible de sélectionner les articles dans la base",e);
		}
		
		return article;
	}

	@Override
	public List<Enchere> selectAll() throws EnchereDALException, SQLException, ArticleVenduDALException {
		List<Enchere> encheres = new ArrayList<>();
		ArticleVendu articleVendu = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			Statement pStmt 	 = connexion.createStatement();
		){
			ResultSet rs 	= pStmt.executeQuery(SELECT_ALL_ENCHERES);
			
			while(rs.next()) {
				
//				Utilisateur
				int noUtilisateur  	= rs.getInt("no_utilisateur");
				String pseudo		= rs.getString("pseudo");
				String nom			= rs.getString("nom");
				String prenom		= rs.getString("prenom");
				String email		= rs.getString("email");
				String telephone	= rs.getString("telephone");
				String rue			= rs.getString("rue");
				String code_postal	= rs.getString("code_postal");
				String ville		= rs.getString("ville");
				int credit			= rs.getInt("credit");
				
				Adresse adresse 	= new Adresse(rue,code_postal,ville);
				
				Utilisateur utilisateur = new Utilisateur(noUtilisateur,pseudo,nom,prenom,email,telephone,adresse,credit);
				
//				ArticleVendu				
				articleVendu = selectArticleById(rs.getInt("no_article"));
				
//				Enchere
				int noEnchere				= rs.getInt("no_enchere");
				LocalDate dateEnchere 		= rs.getDate("date_enchere").toLocalDate();
				int montantEnchere			= rs.getInt("montant_enchere");
				
				encheres.add(new Enchere(noEnchere, dateEnchere, montantEnchere, utilisateur, articleVendu));
			}
			
		}
		
		return encheres;
	}

	@Override
	public List<Enchere> selectEnchereByUtilisateur(int pUtilisateurId) throws EnchereDALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void encherir(int pEnchereId) throws EnchereDALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enchere selectById(int pArticleVenduId) throws EnchereDALException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
