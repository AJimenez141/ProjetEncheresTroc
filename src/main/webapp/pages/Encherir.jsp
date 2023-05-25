<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fr.eni.projet.bo.ArticleVendu" %>
<%@page import="fr.eni.projet.bo.Retrait" %>
<%@page import="fr.eni.projet.bll.RetraitManager" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enchérir</title>
<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet" type="text/css">
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<h2>Détail vente</h2>
	<div class="conteneur" id="Encherir">
		<div class="section">
			<div>
				<img src="../ressources/images/tacos_dos.jpg" alt="J'ai faim !">
			</div>
			<div>
				<%ArticleVendu article = (ArticleVendu) request.getAttribute("article");%>
				<%Retrait retrait = (Retrait) request.getAttribute("retrait"); %>
				<form action="<%=request.getContextPath()%>/Article?idArticle=${ article.getNoArticle()}" method="POST">
					<div>
						<label for="article">Article :</label>
						<c:out value="${article.getNomArticle()}"/>
						<br>
						<br>
						
						<label for="description">Description :</label>
						<c:out value="${article.getDescription()}"/>
						<br>
						<br>				

						<label for="categorie">Catégorie :</label>
						<c:out value="${article.getCategorie().getLibelle()}"/>
						<br>
						<br>

						<label for="offre">Meilleur offre :</label>
						<c:out value="${article.getPrixVente()}"/>
						<br>
						<br>

						<label for="prix">Mise à prix :</label>
						<c:out value="${article.getMiseAPrix()}"/>
						<br>
						<br>

						<label for="finEnchere">Fin de l'enchère :</label>
						<c:out value="${article.getDateFinEncheres()}"/>
						<br>
						<br>

						<label for="retrait">Retrait :</label>
						<c:out value="${retrait.getAdresse().getRue()}"/>
						<c:out value="${retrait.getAdresse().getCodePostal()}"/>
						<c:out value="${retrait.getAdresse().getVille()}"/>
						<br>
						<br>

						<label for="vendeur">Vendeur :</label>
						<c:out value="${article.getVendeur().getPseudo()}"/>
						<br>
						<br>

						<label for="proposition">Ma proposition :</label>
						<input type="number" name="proposition">

						<button class="bouton__enregistrer--couleur" id="btnEncherir" name="btnEncherir">Enchérir</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>