package fr.eni.projet.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.Enchere;

public interface EnchereDAO {
	/**
	 * selectionner une enchere
	 * 
	 * @param pArticleVenduId
	 * @return
	 * @throws EnchereDALException
	 */
	public Enchere selectById(int pArticleVenduId) throws EnchereDALException,SQLException;
	
	/**
	 * selectionner toutes les encheres en cours
	 * 
	 * @return
	 * @throws EnchereDALException
	 */
	public List<Enchere> selectAll() throws EnchereDALException, SQLException;
	
	/**
	 * selectionner les encheres d'un utilisateur
	 * 
	 * @param pUtilisateurId
	 * @return
	 * @throws EnchereDALException
	 */
	public List<Enchere> selectEnchereByUtilisateur(int pUtilisateurId) throws EnchereDALException,SQLException;
	
	/**
	 * effectuer une enchere
	 * 
	 * @param pEnchereId
	 * @throws EnchereDALException
	 */
	public void encherir(int pEnchereId) throws EnchereDALException,SQLException;	
}
