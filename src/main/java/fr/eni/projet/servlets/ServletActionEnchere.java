package fr.eni.projet.servlets;

import java.io.IOException;
import java.util.List;
import java.time.LocalDate;

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
import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Retrait;
import fr.eni.projet.bo.Utilisateur;

/**
 * Servlet implementation class ActionEnchere
 */
@WebServlet("/ActionEnchere")
public class ServletActionEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActionEnchere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categorie> listeCategorie = null;
		
		try {
			//On récupère la liste des categories et on les envoie à la jsp pour les charger dans la combobox
			listeCategorie = CategorieManager.getInstance().recupererLesCategorie();
			request.setAttribute("listeCategories",listeCategorie);
			
			
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		try {
			ArticleVendu article = null;
			article = ArticleVenduManager.getInstance().recupererUnArticleVendu(Integer.parseInt(request.getParameter("idArticle")));
			
			Utilisateur utilisateurPage = article.getVendeur();
			
			HttpSession session = request.getSession();
			Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateur");
			
			if( utilisateurPage.getNoUtilisateur() == utilisateurSession.getNoUtilisateur())
			{
				//vendeur
				
				request.setAttribute("article", article);
				
				Retrait retrait;
				try {
					retrait = RetraitManager.getInstance().selectionnerRetraitArticle(article.getNoArticle());
					request.setAttribute("retrait", retrait);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/EnchereNonCommencee.jsp");
				rd.forward(request, response);
			} else {
				//acheteur
				
				request.setAttribute("article", article);
				Retrait retrait;
				try {
					retrait = RetraitManager.getInstance().selectionnerRetraitArticle(article.getNoArticle());
					request.setAttribute("retrait", retrait);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Encherir.jsp");
				rd.forward(request, response);
			}
		} catch (NumberFormatException e) {
			System.out.println("l'article n'existe pas");
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect( ( (HttpServletRequest) request).getContextPath() + "/Accueil");
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				ArticleVendu articleVendu = null;
				articleVendu = ArticleVenduManager.getInstance().recupererUnArticleVendu(Integer.parseInt(request.getParameter("idArticle")));
			
				String rue = request.getParameter("rue");
				String codePostal = request.getParameter("codePostal");
				String ville = request.getParameter("ville");
				
				Adresse adresse = new Adresse(rue, codePostal, ville);
				
				String article = request.getParameter("article");
				String description = request.getParameter("description");
				String categorieLibelle = request.getParameter("categorie");
				LocalDate debutEnchere = LocalDate.parse(request.getParameter("debutEnchere"));
				LocalDate finEnchere = LocalDate.parse(request.getParameter("finEnchere"));
				int prixDepart = Integer.parseInt(request.getParameter("prixDepart"));
				
				Categorie categorie = new Categorie(categorieLibelle);
				
				articleVendu.setNomArticle(article);
				articleVendu.setDescription(description);
				articleVendu.setCategorie(categorie);
				articleVendu.setDateDebutEncheres(debutEnchere);
				articleVendu.setDateFinEncheres(finEnchere);
				articleVendu.setMiseAPrix(prixDepart);
				
				Retrait retrait = new Retrait(adresse, articleVendu);
				
				retrait.setAdresse(adresse);

				ArticleVenduManager.getInstance().mettreAJourUnArticleVendu(articleVendu);
				request.setAttribute("article", articleVendu);
				request.setAttribute("retrait", retrait);
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
			rd.forward(request, response);
	}

}
