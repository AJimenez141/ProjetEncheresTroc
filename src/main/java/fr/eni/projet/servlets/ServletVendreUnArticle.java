package fr.eni.projet.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.BLLException;
import fr.eni.projet.bll.CategorieManager;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Utilisateur;

@WebServlet("/VendreUnArticle")
public class ServletVendreUnArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletVendreUnArticle() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Categorie> listeCategorie = null;
		try {
			listeCategorie = CategorieManager.getInstance().recupererLesCategorie();
			request.setAttribute("listeCategories",listeCategorie);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/pages/VendreUnArticle.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		try
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			//TODO vendeur en dur pour l'instant. Utiliser variable session id utilisateur pour get
			Utilisateur vendeur = UtilisateurManager.getInstance().recupererUnUtilisateur(1);
			
			int idCategorieChoisie = Integer.parseInt(request.getParameter("selectCategories"));
			Categorie categorie = CategorieManager.getInstance().recupererUneCategorie(idCategorieChoisie);
			
			String nom = request.getParameter("article");
			String description = request.getParameter("description");
			
			LocalDate dateDebut = LocalDate.parse(request.getParameter("debutEnchere"),dtf);
			LocalDate dateFin = LocalDate.parse(request.getParameter("finEnchere"),dtf);
			
			int prixInitial = Integer.parseInt(request.getParameter("prixDepart"));
			
			ArticleVendu articleVendu = new ArticleVendu(nom, description, dateDebut, dateFin, prixInitial, vendeur, categorie);
			
			ArticleVenduManager.getInstance().ajouterArticleVendu(articleVendu);
			
		}
		catch(DateTimeParseException | BLLException e)
		{
			e.printStackTrace();
		}	
		
		RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
		rd.forward(request, response);
	}

}
