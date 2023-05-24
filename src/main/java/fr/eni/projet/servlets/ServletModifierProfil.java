package fr.eni.projet.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.BLLException;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.UtilisateurDALException;

/**
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet("/ModifierProfil")
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifierProfil() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On récupère l'utilisateur en session
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		Utilisateur util = null;
		
		try {
			util = UtilisateurManager.getInstance().recupererUnUtilisateur(utilisateur.getNoUtilisateur());
			request.setAttribute("util", util);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/pages/ModifierProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On récupère l'utilisateur en session
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		String rue = request.getParameter("rue");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("ville");
		
		Adresse adresse = new Adresse(rue, cp, ville);
		
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");

		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(tel);
		utilisateur.setAdresse(adresse);
		
		String mdp = utilisateur.getMotDePasse();
		String mdpNouveau = request.getParameter("mdpNouveau");
		String confirmMdp = request.getParameter("confirmMdp");
		

		if(mdpNouveau.isEmpty() && confirmMdp.isEmpty()) {
			utilisateur.setMotDePasse(mdp);
		} else if (mdpNouveau.equals(confirmMdp)) {
			utilisateur.setMotDePasse(mdpNouveau);
		} else {
			System.out.println("Confirmer le mdp");
		}
		
		try {
			UtilisateurManager.getInstance().modifierUtilisateur(utilisateur);
			session.setAttribute("pseudo", pseudo);
			session.setAttribute("nom", nom);
			session.setAttribute("prenom", prenom);
			session.setAttribute("email", email);
			session.setAttribute("tel", tel);
			session.setAttribute("rue", adresse.getRue());
			session.setAttribute("cp", adresse.getCodePostal());
			session.setAttribute("ville", adresse.getVille());
			
			if(mdpNouveau.isEmpty() && confirmMdp.isEmpty()) {
				session.setAttribute("mdp", mdp);
			} else if (mdpNouveau.equals(confirmMdp)) {
				session.setAttribute("mdp", mdpNouveau);
			} else {
				System.out.println("Confirmer le mdp");
			}
		} catch (UtilisateurDALException | SQLException | BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/Profil");
		rd.forward(request, response);
	}

}
