package fr.eni.dal.jdbc;

import java.util.List;

import fr.eni.projet.bo.Categorie;
import fr.eni.projet.dal.CategorieDALException;
import fr.eni.projet.dal.CategorieDAO;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	@Override
	public Categorie selectById(int pCategorieId) throws CategorieDALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categorie> SelectAll() throws CategorieDALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insererCategorie(Categorie pCategorie) throws CategorieDALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerCategorie(int pCategorieId) throws CategorieDALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierCategorie(int pCategorieId) throws CategorieDALException {
		// TODO Auto-generated method stub
		
	}

}
