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

	private static final String SELECT_ALL_ENCHERES = "(SELECT * FROM ENCHERES "
		+ "INNER JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
		+ "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
		+ "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie "
		+ "WHERE montant_enchere = (SELECT MAX(montant_enchere) FROM ENCHERES)) "
		+ "UNION "
		+ "(SELECT * FROM ENCHERES "
		+ "RIGHT OUTER JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
		+ "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
		+ "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie "
		+ "WHERE montant_enchere IS NULL) ";
	
//	POUR TOUS LES UTILISATEURS - CONNECTE/NON CONNECTE
	private static final String SELECT_ALL_ENCHERES_OUVERTES = "(SELECT * FROM ENCHERES "
		+ "INNER JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
		+ "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
		+ "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie "
		+ "WHERE montant_enchere = (SELECT MAX(montant_enchere) FROM ENCHERES) "
		+ "AND GETDATE() BETWEEN ARTICLES_VENDUS.date_debut_encheres AND ARTICLES_VENDUS.date_fin_encheres ) "
		+ "UNION "
		+ "(SELECT * FROM ENCHERES "
		+ "RIGHT OUTER JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
		+ "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
		+ "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie "
		+ "WHERE montant_enchere IS NULL "
		+ "AND GETDATE() BETWEEN ARTICLES_VENDUS.date_debut_encheres AND ARTICLES_VENDUS.date_fin_encheres )";
	
//	FILTRE CATEGORIE	
	private static final String SELECT_ALL_ENCHERES_BY_CATEGORIES = "(SELECT * FROM ENCHERES "
		+ "INNER JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
		+ "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
		+ "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie "
		+ "WHERE montant_enchere = (SELECT MAX(montant_enchere) FROM ENCHERES) AND CATEGORIES.libelle = ?) "
		+ "UNION "
		+ "(SELECT * FROM ENCHERES "
		+ "RIGHT OUTER JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
		+ "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
		+ "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie "
		+ "WHERE montant_enchere IS NULL AND CATEGORIES.libelle = ?)";
	
	private static final String SELECT_ALL_ENCHERES_BY_SEARCH = "(SELECT * FROM ENCHERES "
		+ "INNER JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
		+ "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
		+ "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie "
		+ "WHERE montant_enchere = (SELECT MAX(montant_enchere) FROM ENCHERES) "
		+ "AND ARTICLES_VENDUS.nom_article LIKE '%" 
		+ "?" 
		+ "%') "
		+ "UNION "
		+ "(SELECT * FROM ENCHERES "
		+ "RIGHT OUTER JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
		+ "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
		+ "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie "
		+ "WHERE montant_enchere IS NULL "
		+ "AND ARTICLES_VENDUS.nom_article LIKE '%" 
		+ "?" 
		+ "%')";
	
//	POUR UN UTILISATEUR CONNECTE - ACHATS 
	
	// ENCHERES OUVERTES REPREND LA METHODE SELECT ALL PRECEDENTE
	
	private static final String SELECT_MES_ENCHERES = "SELECT * FROM ENCHERES "
		+ "INNER JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
		+ "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
		+ "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie "
		+ "AND ENCHERES.no_utilisateurs = ? ";
	
	private static final String SELECT_MES_ENCHERES_REMPORTEES = "SELECT * FROM ENCHERES "
		+ "INNER JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
		+ "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
		+ "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie "
		+ "WHERE montant_enchere = (SELECT MAX(montant_enchere) FROM ENCHERES) "
		+ "AND GETDATE() BETWEEN ARTICLES_VENDUS.date_debut_encheres AND ARTICLES_VENDUS.date_fin_encheres "
		+ "AND ENCHERES.no_utilisateurs = ? ";
	
//	POUR UN UTILISATEUR CONNECTE - VENTES
	private static final String SELECT_MES_VENTES_EN_COURS = "SELECT * FROM ENCHERES "
		+ "INNER JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
		+ "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
		+ "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie "
		+ "WHERE ARTICLES_VENDUS.no_utilisateur = ? "
		+ "AND GETDATE() BETWEEN ARTICLES_VENDUS.date_debut_encheres AND ARTICLES_VENDUS.date_fin_encheres";
	
	private static final String SELECT_VENTES_BY_UTILISATEUR_NON_DEBUTEES = "SELECT * FROM ENCHERES "
		+ "INNER JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
		+ "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
		+ "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie "
		+ "WHERE ARTICLES_VENDUS.no_utilisateur = ? "
		+ "AND ARTICLES_VENDUS.date_debut_encheres < GETDATE()";
	
	private static final String SELECT_VENTES_BY_UTILISATEUR_TERMINEES = "SELECT * FROM ENCHERES "
		+ "INNER JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
		+ "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
		+ "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie "
		+ "WHERE ARTICLES_VENDUS.no_utilisateur = ? "
		+ "AND ARTICLES_VENDUS.date_fin_encheres < GETDATE()";
	
	private static final String SELECT_ENCHERE_BY_ID = "SELECT * FROM ENCHERES AS E INNER JOIN UTILISATEURS AS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AS A ON A.no_article = E.no_article WHERE no_enchere = ?";
	
	private static final String SELECT_ENCHERE_BY_UTILISATEUR_ID = "SELECT * FROM ENCHERES AS E INNER JOIN UTILISATEURS AS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AS A ON A.no_article = E.no_article WHERE E.no_utilisateur = ?";
	
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) VALUES (?,?,?,?)";
	
	private static final String SELECT_ARTICLE_BY_ID = "SELECT * FROM ARTICLES_VENDUS "
			 + "INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur "
			 + "INNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie WHERE no_article = ?";
	
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
			throw new EnchereDALException("Impossible de sélectionner les articles dans la base",e);
		}		
		return article;
	}

	/**
	 * Recuperer toutes les enchères
	 */
	@Override
	public List<Enchere> selectAll() throws EnchereDALException {
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
	public List<Enchere> selectEnchereByUtilisateur(int pUtilisateurId) throws EnchereDALException {
		
		List<Enchere> encheres = new ArrayList<>();
		try(
			Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pStmt = connexion.prepareStatement(SELECT_ENCHERE_BY_UTILISATEUR_ID);
		){
			pStmt.setInt(1, pUtilisateurId);
			ResultSet rs = pStmt.executeQuery();
			
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
	public Enchere selectById(int pEnchereId) throws EnchereDALException {
		Enchere enchere = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pStmt = connexion.prepareStatement(SELECT_ENCHERE_BY_ID);
		){
			pStmt.setInt(1, pEnchereId);
			ResultSet rs = pStmt.executeQuery();
			
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
	public void creerEnchere(Enchere pEnchere) throws EnchereDALException {
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
	public Enchere selectMaxEnchereByArticle(int pArticleVenduId) throws EnchereDALException {
		Enchere enchere = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pStmt = connexion.prepareStatement(SELECT_MAX_ENCHERE_BY_ARTICLE);
		){
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
			throw new EnchereDALException("Impossible de récupérer le'enchères", e);
		}
		
		return enchere;
	}
	
}
