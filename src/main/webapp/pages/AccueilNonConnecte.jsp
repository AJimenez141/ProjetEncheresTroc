<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet" type="text/css">
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
</head>
<header>
	<div class="conteneur" id="Header">
		<a href="<%=request.getContextPath()%>/AccueilNonConnecte">
			<h1 class="ombre_foncee">Tacos - Enchères</h1>
		</a>
		<ul>
			<li><a class="ombre_foncee" href="<%=request.getContextPath()%>/Inscription">S'inscrire</a></li>
			<li><a class="ombre_foncee" href="<%=request.getContextPath()%>/Connexion">Se Connecter</a></li>
		</ul>
	</div>
</header>
<body>
	<h2 class="ombre_foncee">Liste des Enchères</h2>
	<div class="conteneur" id="AccueilNonConnecte">
		<div class="section">
			<form action="<%=request.getContextPath()%>/AccueilNonConnecte" method="POST">
				<div>
					<h3>Filtres : </h3>
	
					<input type="text" name="filtre"/>
					
					<label name="labelCategorie" for="categorie">Categorie : </label>
					<select name="categorie">
						<option>Toutes</option>
						<c:forEach var="categorie" items="${categories}">
							<option value="${categorie.libelle}">${categorie.libelle}</option>
						</c:forEach>
					</select>
			
				</div>
				<div id="divBouton">
					<button class="couleur__bouton--general" type="submit" id="btnRechercher">Rechercher</button>
				</div>
			</form>
		</div>
		
		<div class="section">
			<c:if test="${ empty erreurs }">
		 		<c:forEach var="erreur" items="${ erreurs }">
		 			<p>${ erreur }</p><br>
		 		</c:forEach>
		 	</c:if>
		
		 	<c:if test="${ empty encheresCourantes && empty articlesEnVente }">
		 		<p>Pas de ventes en cours...</p>
		 	</c:if>
		 	<c:forEach var="enchere" items="${ encheresCourantes }">
		 		<div>
		 			<img alt="" src="" height="200" width="200">
		 		</div>
		 		<ul>
					<a href="<%=request.getContextPath()%>/ActionEnchere"><li>${ enchere.getArticleVendu().getNomArticle() }</li></a>
					<li>Enchere en cours : ${ enchere.montant_enchere }</li>
					<li>Fin de L'enchère : ${ enchere.getArticleVendu().getDateFinEncheres() }</li>
					<li>Vendeur : ${ enchere.getArticleVendu().getVendeur().getPseudo() }</a></li>
				</ul>
		 	</c:forEach>
		 	<c:forEach var="article" items="${ articlesEnVente }">
		 		<div>
		 			<img alt="" src="" height="200" width="200">
		 		</div>
		 		<ul>
					<a href="<%=request.getContextPath()%>/ActionEnchere"><li>${ article.nomArticle }</li></a>
					<li>Prix : ${ article.miseAPrix }</li>
					<li>Fin de L'enchère : ${ article.dateFinEncheres }</li>
					<li>Vendeur : ${ article.getVendeur().getPseudo() }</a></li>
				</ul>
		 	</c:forEach>
		</div>
	</div>
</body>
</html>