package fr.eni.projet.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ConnectionProvider;
import fr.eni.projet.dal.ConnexionException;
import fr.eni.projet.dal.UtilisateurDALException;
import fr.eni.projet.dal.UtilisateurDAO;
import fr.eni.projet.bo.Adresse;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
//	Liste des requetes
	private final String SELECT_UTILISATEUR_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
	
	private final String SELECT_ALL_UTILISATEUR = "SELECT * FROM UTILISATEURS";
	
	private final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,0)";
	
	//TODO Solution provisoire pour éviter les conflits. A corriger par contrainte "delete on cascade" directement sur la bdd.
	private final String DELETE_UTILISATEUR = "DELETE ENCHERES FROM ENCHERES "
											+ "INNER JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
											+ "WHERE ARTICLES_VENDUS.no_utilisateur = ? "
											+ "DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur = ? "
											+ "DELETE FROM ENCHERES WHERE no_utilisateur = ? "
											+ "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
	
	private final String UPDATE_UTILISATEUR = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ? WHERE no_utilisateur = ?";
	
	private final String CONNEXION_UTILISATEUR_EMAIL = "SELECT * FROM UTILISATEURS WHERE email = ? AND mot_de_passe = ?";
	
	private final String CONNEXION_UTILISATEUR_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo = ? AND mot_de_passe = ?";
	
//	Definition de nombre de credit par defaut
	private final int DEFAULT_CREDIT	= 100;

	/**
	 * Selectionner un utilisateur en fonction de son id
	 */
	@Override
	public Utilisateur selectById(int pUtilisateurId) throws UtilisateurDALException {
		
		Utilisateur utilisateur = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pStmt = connexion.prepareStatement(SELECT_UTILISATEUR_BY_ID);
		){
			pStmt.setInt(1, pUtilisateurId);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				int noUtilisateur  	= rs.getInt("no_utilisateur");
				String pseudo		= rs.getString("pseudo");
				String nom			= rs.getString("nom");
				String prenom		= rs.getString("prenom");
				String email		= rs.getString("email");
				String telephone	= rs.getString("telephone");
				String motDePasse	= rs.getString("mot_de_passe");
				String rue			= rs.getString("rue");
				String code_postal	= rs.getString("code_postal");
				String ville		= rs.getString("ville");
				int credit			= rs.getInt("credit");
				
				Adresse adresse 	= new Adresse(rue,code_postal,ville);
				
				utilisateur = new Utilisateur(noUtilisateur,pseudo,nom,prenom,email,motDePasse,telephone,adresse,credit);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("Impossible de récupérer l'utilisateur avec l'id "+pUtilisateurId, e);
		}
		
		return utilisateur;
	}

	/**
	 * Selectionner tous les utilisateurs
	 */
	@Override
	public List<Utilisateur> selectAll() throws UtilisateurDALException {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			Statement pStmt = connexion.createStatement();
		) {
			ResultSet rs = pStmt.executeQuery(SELECT_ALL_UTILISATEUR);
			
			while(rs.next() ) {
				int noUtilisateur  	= rs.getInt("no_utilisateur");
				String pseudo		= rs.getString("pseudo");
				String nom			= rs.getString("nom");
				String prenom		= rs.getString("prenom");
				String email		= rs.getString("email");
				String motDePasse	= rs.getString("mot_de_passe");
				String telephone	= rs.getString("telephone");
				String rue			= rs.getString("rue");
				String code_postal	= rs.getString("code_postal");
				String ville		= rs.getString("ville");
				int credit			= rs.getInt("credit");
				
				Adresse adresse 	= new Adresse(rue,code_postal,ville);
				
				utilisateurs.add(new Utilisateur(noUtilisateur,pseudo,nom,prenom,email, motDePasse,telephone,adresse,credit));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("Impossible de sélectionner les utilisateurs dans la base",e);
		}
		
		return utilisateurs;
	}

	/**
	 * Inserer un nouvel utilisateur
	 */
	@Override
	public void insererUtilisateur(Utilisateur pUtilisateur) throws UtilisateurDALException {
		try(
			Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pStmt = connexion.prepareStatement(INSERT_UTILISATEUR, Statement.RETURN_GENERATED_KEYS);
		){
			Adresse adresseUtilisateur = pUtilisateur.getAdresse();
			
			pStmt.setString(1, pUtilisateur.getPseudo());
			pStmt.setString(2, pUtilisateur.getNom());
			pStmt.setString(3, pUtilisateur.getPrenom());
			pStmt.setString(4, pUtilisateur.getEmail());
			pStmt.setString(5, pUtilisateur.getTelephone());
			pStmt.setString(6, adresseUtilisateur.getRue());
			pStmt.setString(7, adresseUtilisateur.getCodePostal());
			pStmt.setString(8, adresseUtilisateur.getVille());
			
//			TODO à redéfinir après la création de la méthode avec HASH
			pStmt.setString(9, pUtilisateur.getMotDePasse());
			
			pStmt.setInt(10, DEFAULT_CREDIT);
			
			pStmt.executeUpdate();
			
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				pUtilisateur.setNoUtilisateur(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("Impossible d'insérer l'utilisateur",e);
		}
	}

	/**
	 * Supprimer un utilisateur
	 */
	@Override
	public void supprimerUtilisateur(int pUtilisateurId) throws UtilisateurDALException {
		try(
			Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pStmt = connexion.prepareStatement(DELETE_UTILISATEUR);
		){
			pStmt.setInt(1, pUtilisateurId);
			pStmt.setInt(2, pUtilisateurId);
			pStmt.setInt(3, pUtilisateurId);
			pStmt.setInt(4, pUtilisateurId);
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("Une erreur est survenue lors de la suppression de l'utilisateur");
		}
		
	}

	/**
	 * Modifier un utilisateur
	 */
	@Override
	public void modifierUtilisateur(Utilisateur pUtilisateur) throws UtilisateurDALException {
		try(
			Connection connexion = ConnectionProvider.getConnection();	
			PreparedStatement pStmt = connexion.prepareStatement(UPDATE_UTILISATEUR)
		){
			
//			private final String UPDATE_UTILISATEUR = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ? WHERE no_utilisateur = ?";
			Adresse adresseUtilisateur = pUtilisateur.getAdresse();
			
			pStmt.setString(1, pUtilisateur.getPseudo());
			pStmt.setString(2, pUtilisateur.getNom());
			pStmt.setString(3, pUtilisateur.getPrenom());
			pStmt.setString(4, pUtilisateur.getEmail());
			pStmt.setString(5, pUtilisateur.getTelephone());
			pStmt.setString(6, adresseUtilisateur.getRue());
			pStmt.setString(7, adresseUtilisateur.getCodePostal());
			pStmt.setString(8, adresseUtilisateur.getVille());
			
//			TODO à redéfinir après la création de la méthode avec HASH
			pStmt.setString(9, pUtilisateur.getMotDePasse());
			
			pStmt.setInt(10, pUtilisateur.getCredit());
			pStmt.setInt(11, pUtilisateur.getNoUtilisateur());
			
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("Impossible de mettre à jour l'utilisateur " + pUtilisateur.getPseudo(), e);
		}		
	}

	/**
	 * Comparer le mail et le pseudo pour la connexion 
	 */
	@Override
	public Utilisateur seConnecterEmail(String pEmail, String pMotDePasse) throws UtilisateurDALException {
		
		Utilisateur resultat = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();	
			PreparedStatement pStmt = connexion.prepareStatement(CONNEXION_UTILISATEUR_EMAIL);
		){
			pStmt.setString(1, pEmail);
			pStmt.setString(2, pMotDePasse);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				resultat = new Utilisateur(
					rs.getInt("no_utilisateur"),
					rs.getString("pseudo"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("email"),
					rs.getString("mot_de_passe"),
					rs.getString("telephone"),
					new Adresse(
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville")
					),
					rs.getInt("credit"),
					rs.getBoolean("administrateur")
				);				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("Impossible de se connecter, vérifier les informations de connexion",e);
		}
		
		return resultat;
	}
	
	/**
	 * Comparer le mail et le pseudo pour la connexion 
	 */
	@Override
	public Utilisateur seConnecterPseudo(String pPseudo, String pMotDePasse) throws UtilisateurDALException {
		
		Utilisateur resultat = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();	
			PreparedStatement pStmt = connexion.prepareStatement(CONNEXION_UTILISATEUR_PSEUDO);
		){
			
			pStmt.setString(1, pPseudo);
			pStmt.setString(2, pMotDePasse);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				resultat = new Utilisateur(
					rs.getInt("no_utilisateur"),
					rs.getString("pseudo"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("email"),
					rs.getString("mot_de_passe"),
					rs.getString("telephone"),
					new Adresse(
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville")
					),
					rs.getInt("credit"),
					rs.getBoolean("administrateur")
				);				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("Impossible de se connecter, vérifier les informations de connexion",e);
		}
		
		return resultat;
	}
}
