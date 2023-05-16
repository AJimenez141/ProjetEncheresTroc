package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ConnectionProvider;
import fr.eni.projet.dal.EnchereDALException;
import fr.eni.projet.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String SELECT_ALL_ENCHERES = "SELECT * FROM ENCHERES AS E INNER JOIN UTILISATEURS AS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AS A ON A.no_article = E.no_article";

	@Override
	public Enchere selectById(int pArticleVenduId) throws EnchereDALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> selectAll() throws EnchereDALException, SQLException {
		List<Enchere> encheres = new ArrayList<>();
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			Statement pStmt 	 = connexion.createStatement();
			Statement pStmt2 	 = connexion.createStatement();
		){
			ResultSet rs 	= pStmt.executeQuery(SELECT_ALL_ENCHERES);
			ResultSet rs2 	= pStmt.executeQuery();
			
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
				int noArticle 				= rs.getInt("no_article");
				String nomArticle 			= rs.getString("nom_article");
				String description 			= rs.getString("description");
				LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFinEncheres 	= rs.getDate("date_fin_encheres").toLocalDate();
				int miseAPrix 				= rs.getInt("prix_initial");
				int prixVente 				= rs.getInt("prix_vente");
				boolean enVente 			= rs.getBoolean("en_vente");
				
//				Utilisateur qui vend l'article		
				
//				Categorie
				int noCategorie				= rs.getInt("no_categorie");
				String libelle				= rs.getString("libelle");
				
//				Enchere
				int noEnchere				= rs.getInt("no_enchere");
				LocalDate dateEnchere 		= rs.getDate("date_enchere").toLocalDate();
				int montantEnchere			= rs.getInt("montant_enchere");
				
				encheres.add(new Enchere(dateEnchere, noEnchere, utilisateur, null));
			}
		}
		
		return null;
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

}
