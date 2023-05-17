package fr.eni.projet.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.Categorie;
import fr.eni.projet.dal.CategorieDALException;
import fr.eni.projet.dal.CategorieDAO;
import fr.eni.projet.dal.DAOFactory;

public class CategorieManager {
	
	private CategorieDAO categorieDAO;
	
	/**
	 * Recuperer categorieDAO via la Factory
	 */
	public CategorieManager() {
		this.categorieDAO=DAOFactory.recupererCategorieDAO();
	}
	
	/**
	 * Recuperation d'une categorie
	 * 
	 * @throws CategorieDALException
	 */
	public Categorie recupererUneCategorie(int pNoCategorie) throws CategorieDALException, SQLException 
	{
		return this.categorieDAO.selectById(pNoCategorie);
	}
	
	/**
	 * Recuperation de toutes les categories
	 * 
	 * @throws CategorieDALException
	 */
	public List<Categorie> recupererLesCategorie() throws CategorieDALException, SQLException 
	{
		return this.categorieDAO.SelectAll();
	}
	
	/**
	 * Ajout d'une categorie
	 * 
	 * @throws CategorieDALException
	 */
	public void ajouterCategorie(Categorie pCategorie) throws CategorieDALException, SQLException 
	{
		this.categorieDAO.insererCategorie(pCategorie);
	}
	
	/**
	 * Modification d'une categorie
	 * 
	 * @throws CategorieDALException
	 */
	public void modifierCategorie(Categorie pCategorie) throws CategorieDALException, SQLException 
	{
		this.categorieDAO.modifierCategorie(pCategorie);
	}
	
	/**
	 * Suppression d'une categorie
	 * 
	 * @throws CategorieDALException
	 */
	public void supprimerCategorie(int pNoCategorie) throws CategorieDALException, SQLException 
	{
		this.categorieDAO.supprimerCategorie(pNoCategorie);
	}
}
