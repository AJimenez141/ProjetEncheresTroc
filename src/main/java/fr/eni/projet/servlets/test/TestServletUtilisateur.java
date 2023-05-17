package fr.eni.projet.servlets.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Adresse;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.UtilisateurDALException;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServletUtilisateur")
public class TestServletUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServletUtilisateur() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Adresse adresseEncherisseur1 = new Adresse("95 rue des champs", "44100", "Nantes");
		Utilisateur encherisseur1 = new Utilisateur(1, "jdupont", "dupont", "jean", "jdupont@mail.com", "aeroToto000","0612345678", adresseEncherisseur1, 15);
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		try {
			utilisateurManager.insererUtilisateur(encherisseur1);
			response.getWriter().append("Utilisateur inséré");
		} catch (UtilisateurDALException e) {
			e.printStackTrace();
		}

	}
}
