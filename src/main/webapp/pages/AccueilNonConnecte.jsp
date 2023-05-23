<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
<<<<<<< Updated upstream
<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet" type="text/css">
=======
<link type="text/css" href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet">
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
					<select name="selectCategorie">
=======
					<select name="categorie">
>>>>>>> Stashed changes
						<option value="toutes">Toutes</option>
						<!-- 
						<c:forEach var="categorie" items="${ categories }">
							<option value="${ option.getLibelle() }"></option>
						</c:forEach>
						 -->
					</select>
			
				</div>
				<div id="divBouton">
					<button class="couleur__bouton--general" type="submit" id="btnRechercher">Rechercher</button>
				</div>
			</form>
		</div>
		
		<div class="section">
		
		<!-- A decommenter quand mise en vente sera fonctionnelle -->
		<!-- 
			<c:if test="${!empty encheresCourantes}">
				<div class="alert alert-danger" role="alert">
					<p>Aucune enchère pour le moment</p>
				</div>
			</c:if>
			<c:forEach var="enchere" items="${ encheresCourantes }">
				<div>
					<img alt="" src="" height="200" width="200">
					<ul>
						<li><c:out value="${ enchere.getArticleVendu().getNomArticle() }" /></li>
						<li>Prix : 
							<c:choose>
							    <c:when test="${ enchere.getMontant_enchere() != null }">
							        <c:out value="${ enchere.getMontant_enchere() }" />
							    </c:when>    
							    <c:otherwise>
							        <c:out value="${ enchere.getArticleVendu().getMiseAPrix() }" />
							    </c:otherwise>
							</c:choose>
						</li>
						<li>Fin de L'enchère : <c:out value="${ enchere.getArticleVendu().getDateFinEncheres() }" /></li>
						<li>Vendeur : <c:out value="${ enchere.getArticleVendu().getVendeur().getPseudo() }" /></li>
					</ul>
				</div>
			</c:forEach>
		-->
			
		</div>
		
	</div>
</body>
</html>