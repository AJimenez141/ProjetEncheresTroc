package fr.eni.projet.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.BLLException;
import fr.eni.projet.bll.CategorieManager;
import fr.eni.projet.bll.RetraitManager;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Retrait;
import fr.eni.projet.bo.Adresse;
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
			//On récupère la liste des categories et on les envoie à la jsp pour les charger dans la combobox
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
		// CREATION D'UNE LISTE D'ERREUR POUR AFFICHAGE 
		List<String> erreurs = new ArrayList<>();
		
		try
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			//On récupère l'utilisateur en session en guise de vendeur
			HttpSession session = request.getSession();
			Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");
			
			//On récup la catégorie choisie dans la combobox
			int idCategorieChoisie = Integer.parseInt(request.getParameter("selectCategories"));
			Categorie categorie = CategorieManager.getInstance().recupererUneCategorie(idCategorieChoisie);
			
			//On récup les autres infos saisies pour construire l'objet articleVendu
			String nom = request.getParameter("article");
			String description = request.getParameter("description");
			
			LocalDate dateDebut = LocalDate.parse(request.getParameter("debutEnchere"),dtf);
			LocalDate dateFin = LocalDate.parse(request.getParameter("finEnchere"),dtf);
			
			int prixInitial = Integer.parseInt(request.getParameter("prixDepart"));
			
			ArticleVendu articleVendu = new ArticleVendu(nom, description, dateDebut, dateFin, prixInitial, vendeur, categorie);
			
			//On insère l'article
			ArticleVenduManager.getInstance().ajouterArticleVendu(articleVendu);
			
			//On s'occupe maintenant des infos du retrait
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			
			Retrait retrait = new Retrait(null, articleVendu);
			
			//Si on a coché "utiliser mon adresse", on prend l'adresse vendeur par défaut
			if(request.getParameter("chkMonAdresse") == null) {
				retrait.setAdresse(vendeur.getAdresse());
			//Sinon, on prend l'adresse saisie
			} else {
				retrait.setAdresse(new Adresse(rue, codePostal, ville));
			}
			//On insère ce retrait
			RetraitManager.getInstance().creerRetrait(retrait);
			
			//Enfin, on redirige sur la bonne page
			RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
			rd.forward(request, response);
		}
		catch(DateTimeParseException | NumberFormatException | BLLException e)
		{
			e.printStackTrace(); 
			erreurs.add(e.toString()); 
			
			this.getServletContext().setAttribute("erreurs",erreurs);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/VendreUnArticle.jsp");
			rd.forward(request, response);
		}	
	}
}
