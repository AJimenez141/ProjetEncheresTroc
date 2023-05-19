package fr.eni.projet.bll;

import fr.eni.projet.dal.ConnexionException;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.UtilisateurDALException;
import fr.eni.projet.dal.UtilisateurDAO;

import fr.eni.projet.bo.Utilisateur;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;
	private static UtilisateurManager instance;
	
	public static final Pattern VALID_ADRESSE_MAIL_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public static final Pattern VALID_CODE_POSTAL_REGEX = 
			Pattern.compile("^[0-9]{5}");
	
	public static final Pattern VALID_TELEPHONE_REGEX =
			Pattern.compile("^[0-9]{10}");
	
	/**
	 * Recuperer utilisateurDAO via la Factory
	 */
	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.recupererUtilisateurDAO();
	}
	
	public static UtilisateurManager getInstance() {
		if(instance ==null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	/**
	 * Recuperation de tous les utilisateurs
	 * 
	 * @return List<Utilisateur>
	 * @throws UtilisateurDALException
	 * @throws BLLException 
	 */
	public List<Utilisateur> recupererLesUtilisateurs() throws UtilisateurDALException, BLLException {
		try {
			return this.utilisateurDAO.selectAll();
		} catch (UtilisateurDALException e) {
			e.printStackTrace();
			throw new BLLException("Impossible de récupérer les données de tous les utilisateurs",e);
		}
	}
	
	/**
	 * Recuperation d'un utilisateur via son ID
	 * 
	 * @param pIdUtilisateur
	 * @return Utilisateur
	 * @throws UtilisateurDALException
	 * @throws SQLException
	 * @throws BLLException 
	 */
	public Utilisateur recupererUnUtilisateur(int pIdUtilisateur) throws UtilisateurDALException, SQLException, BLLException {
		try {
			return this.utilisateurDAO.selectById(pIdUtilisateur);
		} catch (UtilisateurDALException e) {
			e.printStackTrace();
			throw new BLLException("Impossible de récupérer l'utilisateur",e);
		}
	}
	
	/**
	 * Insertion d'un utilisateur dans la BDD
	 * 
	 * TODO - effectuer des controles avant mis en bdd
	 * 
	 * @param pUtilisateur
	 * @throws UtilisateurDALException
	 * @throws BLLException 
	 */
	public void insererUtilisateur(Utilisateur pUtilisateur) throws UtilisateurDALException, BLLException {
		try {
			this.utilisateurDAO.insererUtilisateur(pUtilisateur);
		} catch (UtilisateurDALException e) {
			e.printStackTrace();
			throw new BLLException("Impossible d'insérer un utilisateur dans la base de données",e);
		}
	}
	
	/**
	 * Modification d'un utilisateur
	 * 
	 * TODO - effectuer des controles avant modification dans la bdd
	 * 
	 * @param pUtilisateur
	 * @throws UtilisateurDALException
	 * @throws SQLException
	 * @throws BLLException 
	 */
	public void modifierUtilisateur(Utilisateur pUtilisateur) throws UtilisateurDALException, SQLException, BLLException {
		try {
			this.utilisateurDAO.modifierUtilisateur(pUtilisateur);
		} catch (UtilisateurDALException e) {
			e.printStackTrace();
			throw new BLLException("Impossible de modifier l'utilisateur",e);
		}
	}
	
	/**
	 * Suppression d'un utilisateur
	 * 
	 * TODO - NE PAS OUBLIER DE DEMANDER UNE CONFIRMATION
	 * AU PREALABLE
	 * 
	 * @param pUtilisateurId
	 * @throws UtilisateurDALException
	 * @throws SQLException
	 * @throws BLLException 
	 */
	public void supprimerUtilisateur(int pUtilisateurId) throws UtilisateurDALException, SQLException, BLLException {
		try {
			this.utilisateurDAO.supprimerUtilisateur(pUtilisateurId);
		} catch (UtilisateurDALException e) {
			e.printStackTrace();
			throw new BLLException("Impossible de supprimer l'utilisateur");
		}
	}
	
	/**
	 * Recuperer l'utilisateur au moment de la connexion
	 * 
	 * TODO - faire les controles au moment de la connexion
	 * TODO - methode dans le DAOJdbcImpl possiblement à revoir,
	 * ajout d'un token en front ?
	 * 
	 * @param pEmailOrPseudo
	 * @param pMotDePasse
	 * @return Utilisateur
	 * @throws UtilisateurDALException
	 * @throws SQLException
	 * @throws ConnexionException
	 */
	public Utilisateur seConnecter(String pEmailOrPseudo, String pMotDePasse) throws UtilisateurDALException, SQLException {
		Utilisateur utilisateurConnecte;
		
		if(this.utilisateurDAO.seConnecterEmail(pEmailOrPseudo, pMotDePasse)  != null) {
			utilisateurConnecte = this.utilisateurDAO.seConnecterEmail(pEmailOrPseudo, pMotDePasse);
		} else {
			utilisateurConnecte = this.utilisateurDAO.seConnecterPseudo(pEmailOrPseudo, pMotDePasse);
		}
		
		return utilisateurConnecte;
	}
	
	/**
	 * Verifie que la chaine corresponde à un format standard de mail
	 * 
	 * @param pEmailUtilisateur
	 * @return
	 */
	public boolean validerFormatEmail(String pEmailUtilisateur) {
		Matcher correspond = VALID_ADRESSE_MAIL_REGEX.matcher(pEmailUtilisateur);
        return correspond.matches();
	}
	
	/**
	 * Verifie que la chaine corresponde à un format standard de code postal FR
	 * 
	 * @param pCodePostal
	 * @return
	 */
	public boolean validerFormatCodePostal(String pCodePostal) {
		Matcher correspond = VALID_CODE_POSTAL_REGEX.matcher(pCodePostal);
		return correspond.matches();
	}
	
	/**
	 * Verifie que la chaine corresponde à un format standar de telephone FR
	 * 
	 * @param pTelephone
	 * @return
	 */
	public boolean validerFormatTelephone(String pTelephone) {
		Matcher correspond = VALID_TELEPHONE_REGEX.matcher(pTelephone);
		return correspond.matches();
	}
	
	private void validerUtilisateur(Utilisateur pUtilisateur) throws BLLException {
		
		StringBuilder sb = new StringBuilder();
		
//		String pseudo;
//		String nom;
//		String prenom;
//		String email;
//		String motDePasse;
//		String telephone;
//		Adresse adresse
//		rue
//		code postal
//		
		
		if(pUtilisateur.getPseudo() == null || pUtilisateur.getPseudo().isBlank()) {
			sb.append("Le pseudo doit être renseigné.");
		}
		if(pUtilisateur.getNom() == null || pUtilisateur.getNom().isBlank()) {
			sb.append("Le nom de l'utilisateur doit être renseigné");
		}
		if(pUtilisateur.getPrenom() == null || pUtilisateur.getPrenom().isBlank()) {
			sb.append("Le prénom de l'utilisateur doit être renseigné");
		}
		if(pUtilisateur.getEmail() == null || pUtilisateur.getEmail().isBlank()) {
			sb.append("L'email de l'utilisateur doit être renseigné.");
		}
		if(!this.validerFormatEmail(pUtilisateur.getEmail())) {
			sb.append("L'email doit respecter le format standard.");
		}
		
		/**
		 * TODO - À redéfinir une fois que le hash du mot de passe sera correctement défini
		 */
		if(pUtilisateur.getMotDePasse() == null ||pUtilisateur.getMotDePasse().isBlank()) {
			sb.append("Le mot de passe doit être défini");
		}
		if(pUtilisateur.getTelephone() == null || pUtilisateur.getTelephone().isBlank()) {
			sb.append("Le numéro de téléphone doit être rensigné.");
		}
		if(!this.validerFormatTelephone(pUtilisateur.getTelephone())) {
			sb.append("Le numéro de téléphone doit correspondre au format français");
		}
		if(pUtilisateur.getAdresse().getRue() == null || pUtilisateur.getAdresse().getRue().isBlank()) {
			sb.append("Le numero de rue doit être renseigné.");
		}
		if(pUtilisateur.getAdresse().getVille() == null || pUtilisateur.getAdresse().getVille().isBlank()) {
			sb.append("Le nom de la ville doit être renseigné.");
		}
		if(pUtilisateur.getAdresse().getCodePostal() == null || pUtilisateur.getAdresse().getCodePostal().isBlank()) {
			sb.append("Le code postal doit être renseigné.");
		}
		if(!validerFormatCodePostal(pUtilisateur.getAdresse().getCodePostal())) {
			sb.append("Le code postal doit être renseigné.");
		}	
		if(sb.length() > 0) {
			throw new BLLException(sb.toString());
		}
		
	}

}
