package fr.eni.projet.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
	
	private static final String SELECT_ENCHERE_BY_ID = "SELECT * FROM ENCHERES AS E INNER JOIN UTILISATEURS AS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AS A ON A.no_article = E.no_article WHERE no_enchere = ?";
	
	private static final String SELECT_ENCHERE_BY_UTILISATEUR_ID = "SELECT * FROM ENCHERES AS E INNER JOIN UTILISATEURS AS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AS A ON A.no_article = E.no_article WHERE no_utilisateur = ?";
	
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) VALUES (?,?,?,?)";
	
	private static final String SELECT_ARTICLE_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ? "
			 + "INNER JOIN UTILISATEURS ON UTILISATEUR.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
			 + "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie ";
	
	private static final String SELECT_MAX_ENCHERE_BY_ARTICLE = "SELECT * FROM ENCHERES INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ENCHERES.no_utilisateur WHERE no_article = ? AND montant_enchere = (SELECT MAX(montant_enchere) FROM ENCHERES)";

	/**
	 * retourne un article en fonction de son ID
	 * 
	 * @param pArticleVenduId
	 * @return
	 * @throws ArticleVenduDALException
	 */
	private static ArticleVendu selectArticleById(int pArticleVenduId) throws EnchereDALException {
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
			throw new EnchereDALException("Impossible de sélectionner les articles dans la base",e);
		}		
		return article;
	}

	/**
	 * Recuperer toutes les enchères
	 */
	@Override
	public List<Enchere> selectAll() throws EnchereDALException, SQLException {
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
		}catch (SQLException e) {
			e.printStackTrace();
			throw new EnchereDALException("Impossible de récupérer les enchères",e);
		}
		return encheres;
	}

	/**
	 * Recuperer toutes les encheres d'un utilisateur
	 */
	@Override
	public List<Enchere> selectEnchereByUtilisateur(int pUtilisateurId) throws EnchereDALException, SQLException {
		List<Enchere> encheres = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			Statement pStmt = connexion.createStatement();
		){
			ResultSet rs = pStmt.executeQuery(SELECT_ENCHERE_BY_UTILISATEUR_ID);
			
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
				ArticleVendu articleVendu = selectArticleById(rs.getInt("no_article"));	
				
//				Enchere
				int noEnchere				= rs.getInt("no_enchere");
				LocalDate dateEnchere 		= rs.getDate("date_enchere").toLocalDate();
				int montantEnchere			= rs.getInt("montant_enchere");
				
				encheres.add(new Enchere(noEnchere, dateEnchere, montantEnchere, utilisateur, articleVendu));
			} 
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new EnchereDALException("Impossible de récupérer les enchères de l'utilisateur "+pUtilisateurId, e);
		}
		
		return encheres;
	}

	/**
	 * Selectionner une enchere par son id
	 */
	@Override
	public Enchere selectById(int pEnchereId) throws EnchereDALException, SQLException {
		Enchere enchere = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			Statement pStmt = connexion.createStatement();
		){
			ResultSet rs = pStmt.executeQuery(SELECT_ENCHERE_BY_ID);
			
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
				ArticleVendu articleVendu = selectArticleById(rs.getInt("no_article"));	
				
//				Enchere
				int noEnchere				= rs.getInt("no_enchere");
				LocalDate dateEnchere 		= rs.getDate("date_enchere").toLocalDate();
				int montantEnchere			= rs.getInt("montant_enchere");
				
				enchere = new Enchere(noEnchere, dateEnchere, montantEnchere, utilisateur, articleVendu);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new EnchereDALException("Inpossible de récupérer l'enchère id:"+pEnchereId, e);
		}
		return enchere;
	}

	/**
	 * Creation d'une enchere
	 */
	@Override
	public void creerEnchere(Enchere pEnchere) throws EnchereDALException, SQLException {
		try(
			Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pStmt = connexion.prepareStatement(INSERT_ENCHERE, Statement.RETURN_GENERATED_KEYS);	
		)
		{
			int noUtilisateur 	= pEnchere.getEncherisseur().getNoUtilisateur();
			int noArticle		= pEnchere.getArticleVendu().getNoArticle();
			
			pStmt.setDate(1, Date.valueOf(pEnchere.getDateEnchere()));
			pStmt.setInt(2, pEnchere.getMontant_enchere());
			pStmt.setInt(3, noArticle);
			pStmt.setInt(4, noUtilisateur);
			
			pStmt.executeUpdate();
			
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				pEnchere.setNoEnchere(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EnchereDALException("Impossible d'insérer l'enchère",e);
		}
	}
	
	
	
	/**
	 * Recuperer l'enchère la plus haute en cours pour un article donné
	 */
	@Override
	public Enchere selectMaxEnchereByArticle(int pArticleVenduId) throws EnchereDALException, SQLException {
		Enchere enchere = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
		){
			PreparedStatement pStmt = connexion.prepareStatement(SELECT_MAX_ENCHERE_BY_ARTICLE);
			pStmt.setInt(1, pArticleVenduId);
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
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
				ArticleVendu articleVendu = selectArticleById(rs.getInt("no_article"));	
				
//				Enchere
				int noEnchere				= rs.getInt("no_enchere");
				LocalDate dateEnchere 		= rs.getDate("date_enchere").toLocalDate();
				int montantEnchere			= rs.getInt("montant_enchere");
				
				enchere = new Enchere(noEnchere, dateEnchere, montantEnchere, utilisateur, articleVendu);
			} 
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new EnchereDALException("Impossible de récupérer les enchères", e);
		}
		
		return enchere;
	}
	
}
