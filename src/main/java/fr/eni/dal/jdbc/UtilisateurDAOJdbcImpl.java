package fr.eni.dal.jdbc;

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
	
	private final String SELECT_UTILISATEUR_BY_ID 	= "SELECT * FROM UTILISATEUR WHERE noUtilisateur ?";
	private final String SELECT_ALL_UTILISATEUR	= "SELECT * FROM UTILISATEUR";
	private final String INSERT_UTILISATEUR		= "INSERT INTO UTILISATEUR(no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,0,0,0)";
	private final String DELETE_UTILISATEUR		= "DELETE FROM UTILISATEUR WHERE noUtilisateur = ?";
	private final String UPDATE_UTILISATEUR		= "UPDATE UTILISATEUR SET  WHERE noUtilisateur = ?";
	private final String CONNEXION_UTILISATEUR_EMAIL	= "SELECT * FROM UTILISATEUR WHERE email = ? AND mot_de_passe = ?";
	private final String CONNEXION_UTILISATEUR_PSEUDO	= "SELECT * FROM UTILISATEUR WHERE pseudo = ? AND mot_de_passe = ?";

	@Override
	public Utilisateur selectById(int pUtilisateurId) throws UtilisateurDALException, SQLException {
		Utilisateur utilisateur = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pStmt = connexion.prepareStatement(SELECT_UTILISATEUR_BY_ID);
		){
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				int noUtilisateur  	= rs.getInt("no_utilisateur");
				String pseudo		= rs.getString("pseudo");
				String nom			= rs.getString("nom");
				String prenom		= rs.getString("prenom");
				String email		= rs.getString("email");
				String telephone	= rs.getString("telephone");
				String rue			= rs.getString("rue");
				String code_postal	= rs.getString("code_postal");
				String ville		= rs.getString("ville");
				Double credit		= rs.getDouble("credit");
				
				Adresse adresse 	= new Adresse(rue,code_postal,ville);
				
				utilisateur = new Utilisateur(noUtilisateur,pseudo,nom,prenom,email,telephone,adresse,credit);
			}
			
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
				String telephone	= rs.getString("telephone");
				String rue			= rs.getString("rue");
				String code_postal	= rs.getString("code_postal");
				String ville		= rs.getString("ville");
				Double credit		= rs.getDouble("credit");
				
				Adresse adresse 	= new Adresse(rue,code_postal,ville);
				
				utilisateurs.add(new Utilisateur(noUtilisateur,pseudo,nom,prenom,email,telephone,adresse,credit));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("Impossible de sélectionner les utilisateurs dans la base");
		}
		
		return utilisateurs;
	}

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
			pStmt.setString(5, pUtilisateur.getMotDePasseHashe());
			pStmt.setString(6, pUtilisateur.getTelephone());
			pStmt.setString(7, adresseUtilisateur.getRue());
			pStmt.setString(8, adresseUtilisateur.getCodePostal());
			pStmt.setString(9, adresseUtilisateur.getVille());
			
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

	@Override
	public void supprimerUtilisateur(int pUtilisateurId) throws UtilisateurDALException, SQLException {
		try(
			Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pStmt = connexion.prepareStatement(DELETE_UTILISATEUR);
		){
			pStmt.setInt(pUtilisateurId, pUtilisateurId);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("Une erreur est survenue lors de la suppression de l'utilisateur");
		}
		
	}

	@Override
	public void modifierUtilisateur(Utilisateur pUtilisateur) throws UtilisateurDALException, SQLException {
		try(
			Connection connexion = ConnectionProvider.getConnection();	
			PreparedStatement pStmt = connexion.prepareStatement(UPDATE_UTILISATEUR)
		){
			Adresse adresseUtilisateur = pUtilisateur.getAdresse();
			
			pStmt.setInt(1, pUtilisateur.getNoUtilisateur());
			pStmt.setString(2, pUtilisateur.getPseudo());
			pStmt.setString(3, pUtilisateur.getNom());
			pStmt.setString(4, pUtilisateur.getPrenom());
			pStmt.setString(5, pUtilisateur.getEmail());
			pStmt.setString(6, pUtilisateur.getMotDePasseHashe());
			pStmt.setString(7, pUtilisateur.getTelephone());
			pStmt.setString(8, adresseUtilisateur.getRue());
			pStmt.setString(9, adresseUtilisateur.getCodePostal());
			pStmt.setString(10, adresseUtilisateur.getVille());
			pStmt.setDouble(11, pUtilisateur.getCredit());
			pStmt.setBoolean(12, false);
			
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("Impossible de mettre à jour l'utilisateur " + pUtilisateur.getPseudo());
		}		
	}

	@Override
	public Utilisateur seConnecter(String pEmailOrPseudo, String pMotDePasse)
			throws UtilisateurDALException, SQLException, ConnexionException {
		
		Utilisateur resultat = null;
		
		try(
			Connection connexion = ConnectionProvider.getConnection();	
			PreparedStatement pStmt = connexion.prepareStatement(CONNEXION_UTILISATEUR_EMAIL);
			PreparedStatement pStmt2 = connexion.prepareStatement(CONNEXION_UTILISATEUR_PSEUDO);
		){
			pStmt.setString(1, pEmailOrPseudo);
			pStmt.setString(2, pMotDePasse);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				resultat = new Utilisateur(
					rs.getInt("no_utilisateur"),
					rs.getString("pseudo"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("email"),
					rs.getString("telephone"),
					new Adresse(
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville")
					),
					rs.getDouble("credit"),
					rs.getInt("administrateur")
				);				
			}
			
			pStmt2.setString(1, pEmailOrPseudo);
			pStmt2.setString(2, pMotDePasse);
			
			ResultSet rs2 = pStmt2.executeQuery();
			
			if(rs2.next()) {
				resultat = new Utilisateur(
					rs.getInt("no_utilisateur"),
					rs.getString("pseudo"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("email"),
					rs.getString("telephone"),
					new Adresse(
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville")
					),
					rs.getDouble("credit"),
					rs.getInt("administrateur")
				);				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new ConnexionException("Impossible de se connecter, vérifier les informations de connexion ");
		}		
		return resultat;
	}
}
