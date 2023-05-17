package fr.eni.projet.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.dal.ArticleVenduDALException;
import fr.eni.projet.dal.ArticleVenduDAO;
import fr.eni.projet.dal.ConnectionProvider;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	
	private static final String SELECT_ARTICLE_BY_ID = "SELECT * FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie WHERE no_article = ?";
	
	private static final String SELECT_ALL_ARTICLE = "SELECT * FROM ARTICLES_VENDUS "
												   + "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
												   + "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie ";
	
	private static final String SELECT_ARTICLE_BY_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS "
			 												  + "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
			 												  + "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie WHERE ARTICLES_VENDUS.no_utilisateur = ?";
	
	private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	
	private static final String SELECT_ARTICLE_BY_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE ARTICLES_VENDUS.no_categorie = ? "
			  												+ "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
			  												+ "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie ";
	
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?)";

	@Override
	public ArticleVendu selectById(int pArticleVenduId) throws ArticleVenduDALException {

		ArticleVendu article = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pStmt = connexion.prepareStatement(SELECT_ARTICLE_BY_ID);
		) {
			pStmt.setInt(1, pArticleVenduId);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next() ) {

				int noArticle 				= rs.getInt("no_article");
				String nomArticle 			= rs.getString("nom_article");
				String description 			= rs.getString("description");
				LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFinEncheres 	= rs.getDate("date_fin_encheres").toLocalDate();
				int miseAPrix 				= rs.getInt("prix_initial");
				int prixVente 				= rs.getInt("prix_vente");
				
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
				
				article = new ArticleVendu(noArticle,nomArticle,description,dateDebutEncheres,dateFinEncheres,miseAPrix,prixVente,vendeur,categorie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ArticleVenduDALException("Impossible de sélectionner les articles dans la base",e);
		}
		
		return article;
		
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

				int noArticle 				= rs.getInt("no_article");
				String nomArticle 			= rs.getString("nom_article");
				String description 			= rs.getString("description");
				LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFinEncheres 	= rs.getDate("date_fin_encheres").toLocalDate();
				int miseAPrix 				= rs.getInt("prix_initial");
				int prixVente 				= rs.getInt("prix_vente");
				
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
				
				articles.add(new ArticleVendu(noArticle,nomArticle,description,dateDebutEncheres,dateFinEncheres,miseAPrix,prixVente,vendeur,categorie));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ArticleVenduDALException("Impossible de sélectionner les articles dans la base",e);
		}
		
		return articles;
	}

	@Override
	public List<ArticleVendu> selectArticleVenduByUtilisateur(int pUtilisateurId) throws ArticleVenduDALException {

		List<ArticleVendu> articles = new ArrayList<>();
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pStmt = connexion.prepareStatement(SELECT_ARTICLE_BY_UTILISATEUR);
		) {
			pStmt.setInt(1, pUtilisateurId);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next() ) {

				int noArticle 				= rs.getInt("no_article");
				String nomArticle 			= rs.getString("nom_article");
				String description 			= rs.getString("description");
				LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFinEncheres 	= rs.getDate("date_fin_encheres").toLocalDate();
				int miseAPrix 				= rs.getInt("prix_initial");
				int prixVente 				= rs.getInt("prix_vente");
				
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
				
				articles.add(new ArticleVendu(noArticle,nomArticle,description,dateDebutEncheres,dateFinEncheres,miseAPrix,prixVente,vendeur,categorie));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ArticleVenduDALException("Impossible de sélectionner les articles dans la base",e);
		}
		
		return articles;
		
	}

	@Override
	public void supprimerArticleVendu(int pArticleVenduId) throws ArticleVenduDALException {

		try(
				Connection connexion = ConnectionProvider.getConnection();
				PreparedStatement pStmt = connexion.prepareStatement(DELETE_ARTICLE);
			){
				pStmt.setInt(1, pArticleVenduId);
				pStmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ArticleVenduDALException("Une erreur est survenue lors de la suppression de l'article");
			}
	}

	@Override
	public List<ArticleVendu> selectionnerParCategorie(int pNoCategorie) throws ArticleVenduDALException {

List<ArticleVendu> articles = new ArrayList<>();
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
		) {
			//TODO vérifier pour les autres requêtes avec des paramètres
			PreparedStatement pStmt = connexion.prepareStatement(SELECT_ARTICLE_BY_CATEGORIE);
			pStmt.setInt(1, pNoCategorie);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next() ) {

				int noArticle 				= rs.getInt("no_article");
				String nomArticle 			= rs.getString("nom_article");
				String description 			= rs.getString("description");
				LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFinEncheres 	= rs.getDate("date_fin_encheres").toLocalDate();
				int miseAPrix 				= rs.getInt("prix_initial");
				int prixVente 				= rs.getInt("prix_vente");
				
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
				
				articles.add(new ArticleVendu(noArticle,nomArticle,description,dateDebutEncheres,dateFinEncheres,miseAPrix,prixVente,vendeur,categorie));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ArticleVenduDALException("Impossible de sélectionner les articles dans la base",e);
		}
		
		return articles;
		
	}

	@Override
	public void insererArticleVendu(ArticleVendu pArticleVendu) throws ArticleVenduDALException {


		try(
				Connection connexion = ConnectionProvider.getConnection();
				PreparedStatement pStmt = connexion.prepareStatement(INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS);
			){
				
				Utilisateur vendeurArticle = pArticleVendu.getVendeur();
				Categorie categorieArticle = pArticleVendu.getCategorie();
				
				pStmt.setString(1, pArticleVendu.getNomArticle());
				pStmt.setString(2, pArticleVendu.getDescription());
				pStmt.setDate(3, java.sql.Date.valueOf(pArticleVendu.getDateDebutEncheres()));
				pStmt.setDate(4, java.sql.Date.valueOf(pArticleVendu.getDateFinEncheres()));
				pStmt.setInt(5, pArticleVendu.getMiseAPrix());
				pStmt.setInt(6, pArticleVendu.getPrixVente());
				pStmt.setInt(7, vendeurArticle.getNoUtilisateur());
				pStmt.setInt(8, categorieArticle.getNoCategorie());
				
				pStmt.executeUpdate();
				
				ResultSet rs = pStmt.getGeneratedKeys();
				if(rs.next()) {
					pArticleVendu.setNoArticle(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ArticleVenduDALException("Impossible d'insérer l'article",e);
			}
		
	}

}
