package fr.eni.projet.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.bll.BLLException;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.Utilisateur;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/Inscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInscription() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/pages/CreerCompte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Adresse adresse = new Adresse(request.getParameter("rue"), request.getParameter("cp"), request.getParameter("ville"));

		Utilisateur utilisateur = new Utilisateur(
				request.getParameter("pseudo"),
				request.getParameter("nom"),
				request.getParameter("prenom"),
				request.getParameter("email"),
				request.getParameter("mdp"),
				request.getParameter("tel"),
				adresse,
				100);
		System.out.println(utilisateur);
		try {
			UtilisateurManager.getInstance().insererUtilisateur(utilisateur);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
}
