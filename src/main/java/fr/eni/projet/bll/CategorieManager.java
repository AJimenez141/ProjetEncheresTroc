package fr.eni.projet.bll;

import java.util.List;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.dal.CategorieDALException;
import fr.eni.projet.dal.CategorieDAO;

public class CategorieManager {
	
	private CategorieDAO categorieDAO;
	private static CategorieManager instance;
	
	/**
	 * Recuperer categorieDAO via la Factory
	 */
	public static CategorieManager getInstance() {
		if(instance==null) {
			instance = new CategorieManager();
		}
		return instance;
	}
	
	/**
	 * Recuperation d'une categorie
	 * 
	 * @throws CategorieDALException
	 */
	public Categorie recupererUneCategorie(int pNoCategorie) throws BLLException 
	{
			try {
				return categorieDAO.selectById(pNoCategorie);
			} catch (CategorieDALException e) {
				throw new BLLException(e.getMessage());
			}
	}
	
	/**
	 * Recuperation de toutes les categories
	 * 
	 * @throws CategorieDALException
	 */
	public List<Categorie> recupererLesCategorie() throws BLLException 
	{
		try {
			return categorieDAO.SelectAll();
		} catch (CategorieDALException e) {
			throw new BLLException(e.getMessage());
		}
	}
	
	/**
	 * Ajout d'une categorie
	 * 
	 * @throws CategorieDALException
	 */
	public void ajouterCategorie(Categorie pCategorie) throws BLLException  
	{
			validerCategorie(pCategorie);
			try {
				categorieDAO.insererCategorie(pCategorie);
			} catch (CategorieDALException e) {
				throw new BLLException(e.getMessage());
			}
	}
	
	/**
	 * Modification d'une categorie
	 * 
	 * @throws CategorieDALException
	 */
	public void modifierCategorie(Categorie pCategorie) throws BLLException 
	{
			validerCategorie(pCategorie);
			try {
				categorieDAO.modifierCategorie(pCategorie);
			} catch (CategorieDALException e) {
				throw new BLLException(e.getMessage());
			}
	}
	
	/**
	 * Suppression d'une categorie
	 * 
	 * @throws CategorieDALException
	 */
	public void supprimerCategorie(int pNoCategorie) throws BLLException   
	{
		try {
			categorieDAO.supprimerCategorie(pNoCategorie);
		} catch (CategorieDALException e) {
			throw new BLLException(e.getMessage());
		}
	}
	
	private void validerCategorie(Categorie pCategorie) throws BLLException{
		
		StringBuilder sb = new StringBuilder();
		
		if(pCategorie.getLibelle()==null || pCategorie.getLibelle().isBlank()) {
			sb.append("Le libellé de la catégorie doit être renseigné. \n");
		}
		
		if(sb.length()>0) {
			throw new BLLException(sb.toString());
		}
	}
}
