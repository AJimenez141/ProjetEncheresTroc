package fr.eni.projet.servlets.test;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.dal.CategorieDALException;
import fr.eni.projet.bll.CategorieManager;

@WebServlet("/TestServletCategorie")
public class TestServletCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestServletCategorie() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategorieManager categorieManager = new CategorieManager();
		
		//=======================TEST 1 - INSERTION (REUSSI)===========================================
		
		
		Categorie categorie = new Categorie("Art");
		
		/*
		try {
			categorieManager.ajouterCategorie(categorie);
			response.getWriter().append("Served at: ").append("Categorie insérée");
		} catch (CategorieDALException e) {
			response.getWriter().append("Served at: ").append(e.toString());
			e.printStackTrace();
		} 
		*/
		
		//=======================TEST 2 - SELECT BY ID (REUSSI)===========================================
		
		/*
		try {
			Categorie categorie = categorieManager.recupererUneCategorie(1);
			response.getWriter().append("Served at: ").append(categorie.toString());
		} catch (CategorieDALException e) {
			response.getWriter().append("Served at: ").append(e.toString());
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		
		//=======================TEST 3 - SELECT ALL===========================================
		
	}
}
