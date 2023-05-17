package fr.eni.projet.bll;

import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.UtilisateurDALException;
import fr.eni.projet.dal.UtilisateurDAO;

import fr.eni.projet.bo.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.recupererUtilisateurDAO();
	}
	
	public List<Utilisateur> recupererUtilisateurs() throws UtilisateurDALException {
		return this.utilisateurDAO.selectAll();
	}
	
	public Utilisateur selectionnerUtilisateur(int pIdUtilisateur) throws UtilisateurDALException, SQLException {
		return this.utilisateurDAO.selectById(pIdUtilisateur);
	}
}
