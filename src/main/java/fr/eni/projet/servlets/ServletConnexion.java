package fr.eni.projet.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.BLLException;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Utilisateur;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/Connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/pages/Connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailOuPseudo = request.getParameter("id");
		String mdp = request.getParameter("mdp");
		
		System.out.println("emailOuPseudo " + emailOuPseudo + " mdp " + mdp);
		
//		Utilisateur utilisateur = null;
		
		try {
			Utilisateur utilisateur = UtilisateurManager.getInstance().seConnecter(emailOuPseudo, mdp);
			System.out.println(utilisateur);
			System.out.println(utilisateur);
			
			HttpSession session = request.getSession();
			
//			session.setAttribute("pseudo", utilisateur.getPseudo());
//			session.setAttribute("nom", utilisateur.getNom());
//			session.setAttribute("prenom", utilisateur.getPrenom());
//			session.setAttribute("email", utilisateur.getEmail());
//			session.setAttribute("tel", utilisateur.getTelephone());
//			session.setAttribute("rue", utilisateur.getAdresse().getRue());
//			session.setAttribute("cp", utilisateur.getAdresse().getCodePostal());
//			session.setAttribute("ville", utilisateur.getAdresse().getVille());
//			session.setAttribute("credit", utilisateur.getCredit());
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ListeEncheresConnecte.jsp");
			rd.forward(request, response);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
