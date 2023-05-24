<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des ench�res</title>
<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet" type="text/css">
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	<h2 class="ombre_foncee">Liste des Ench�res</h2>
	
	<div class="conteneur" id="ListeEncheresConnecte">
		<div class="section">
			<div>
			
				<c:if test="${ !empty erreurs }">
					<c:forEach var="erreur" items="${ erreurs }">
						<p>${ erreur }</p>
						<br>
					</c:forEach>
				</c:if>
				
				<form action="<%=request.getContextPath()%>/Accueil" method="POST">
					<h3>Filtres : </h3>
					
					<input type="text" name="filtre"/>
		
		
					<label name="labelCategorie" for="categorie">Categorie : </label>
					<select>
						<option value="toutes">Toutes</option>
						<c:forEach var="categorie" items="${categories}">
							<option value="${categorie.libelle}">${categorie.libelle}</option>
						</c:forEach>
					</select>
					
					<div id="divAchats">
						<h3><input type="radio" value="achats" name="choixListe"/>
						<label name="labelAchats" for="achats">Achats</label></h3>
							<input type="checkbox" name="enchereOuvertes"/>
							<label name="labelEncheresOuvertes" for="encheresOuvertes">ench�res ouvertes</label>
							
							<input type="checkbox" name="mesEncheres"/>
							<label name="labelMesEncheres" for="mesEncheres">mes ench�res</label>
							
							<input type="checkbox" name="mesEncheresRemportees"/>
							<label name="labelMesEncheresRemportees" for="mesEncheresRemportees">mes ench�res remport�es</label>
					</div>
					<div id="divVentes">
						<h3><input type="radio" value="mesVentes" name="choixListe"/>
						<label name="labelVentes" for="ventes">Mes Ventes</label></h3>
							<input type="checkbox" name="mesVentesEnCours"/>
							<label name="labelMesVentesEnCours" for="mesVentesEnCours">mes ventes en cours</label>
							
							<input type="checkbox" name="ventesNonDebutees"/>
							<label name="labelVentesNonDebutees" for="ventesNonDebutees">ventes non d�but�es</label>
							
							<input type="checkbox" name="ventesTerminees"/>
							<label name="labelVentesTerminees" for="ventesTerminees">ventes termin�es</label>
					</div>
				</form>
			</div>
			<div id="divBouton">
				<button type="submit" id="btnRechercher" name="btnRechercher">Rechercher</button>
			</div>
		</div>
		
		<div class="section">
			<!-- 
			<div>
				<img alt="" src="" height="200" width="200">
				<ul>
					<a href="<%=request.getContextPath()%>/ActionEnchere"><li>PC Gamer pour travailler</li></a>
					<li>Prix : 210 points</li>
					<li>Fin de L'ench�re : 10/08/2018</li>
					<li>Vendeur : <a href="Profil.jsp">RoiTacos</a></li>
				</ul>
			</div>
		 	-->
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
					<li>Fin de L'ench�re : ${ enchere.getArticleVendu().getDateFinEncheres() }</li>
					<li>Vendeur : <a href="Profil.jsp">${ enchere.getArticleVendu().getVendeur().getPseudo() }</a></li>
				</ul>
		 	</c:forEach>
		 	<c:forEach var="article" items="${ articlesEnVente }">
		 		<div>
		 			<img alt="" src="" height="200" width="200">
		 		</div>
		 		<ul>
					<a href="<%=request.getContextPath()%>/ActionEnchere"><li>${ article.nomArticle }</li></a>
					<li>Prix : ${ article.miseAPrix }</li>
					<li>Fin de L'ench�re : ${ article.dateFinEncheres }</li>
					<li>Vendeur : <a href="Profil.jsp">${ article.getVendeur().getPseudo() }</a></li>
				</ul>
		 	</c:forEach>

		</div>
	</div>
</body>
</html>