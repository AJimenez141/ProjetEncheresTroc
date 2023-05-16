package fr.eni.projet.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.Categorie;

public interface CategorieDAO {
	
	/**
	 * selectionner une categorie par son id
	 * 
	 * @param pUtilisateurId
	 * @return
	 * @throws UtilisateurDALException
	 * @throws CategorieDALException 
	 */
	public Categorie selectById(int pCategorieId) throws CategorieDALException, SQLException;
	
	/**
	 * selectionner toutes les categories
	 * 
	 * @return List<Categorie>
	 * @throws UtilisateurDALException
	 * @throws CategorieDALException 
	 */
	public List<Categorie> SelectAll() throws CategorieDALException;
	
	/**
	 * inserer une nouvelle categorie
	 * 
	 * @param pCategorie
	 * @return
	 * @throws UtilisateurDALException
	 * @throws CategorieDALException 
	 */
	public void insererCategorie(Categorie pCategorie) throws CategorieDALException;
	
	/**
	 * supprimer une categorie
	 * 
	 * @param pCategorieId
	 * @return
	 * @throws UtilisateurDALException
	 * @throws CategorieDALException 
	 */
	public void supprimerCategorie(int pCategorieId) throws CategorieDALException, SQLException;
	
	/**
	 * modifier une categorie
	 * 
	 * @param pCategorie
	 * @return
	 * @throws UtilisateurDALException
	 * @throws CategorieDALException 
	 */
	public void modifierCategorie(Categorie pCategorie) throws CategorieDALException;

}
