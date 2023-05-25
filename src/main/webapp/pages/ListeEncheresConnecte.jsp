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
					<select name="categorie">
						<option>Toutes</option>
						<c:forEach var="categorie" items="${categories}">
							<option value="${categorie.libelle}">${categorie.libelle}</option>
						</c:forEach>
					</select>
					
					<div id="divAchats">
						<h3>
							<c:choose>
							    <c:when test="${ venteChecked }">
							        <input type="radio" value="achats" name="choixListe" />
							    </c:when>    
							    <c:otherwise>
							        <input type="radio" value="achats" name="choixListe" checked />
							    </c:otherwise>
							</c:choose>
							<label for="achats">Achats</label>
						</h3>
						 
						<input type="radio" name="enchereAchat" value="encheresOuvertes" checked />
						<label for="enchereAchat">ench�res ouvertes</label>
						
						<input type="radio" name="enchereAchat" value="mesEncheres" />
						<label for="enchereAchat">mes ench�res</label>
						
						<input type="radio" name="enchereAchat" value="mesEncheresRemportees"/>
						<label for="enchereAchat">mes ench�res remport�es</label>
					</div>
					<div id="divVentes">
						<h3>
							<c:choose>
							    <c:when test="${ venteChecked }">
							        <input type="radio" value="mesVentes" name="choixListe" checked />
							    </c:when>    
							    <c:otherwise>
							        <input type="radio" value="mesVentes" name="choixListe" />
							    </c:otherwise>
							</c:choose>
							<label for="ventes">Mes Ventes</label>
						</h3>
						 
						 <input type="radio" name="enchereVente" value="mesVentesEnCours" checked />
						<label for="enchereVente">mes ventes en cours</label>
						
						<input type="radio" name="enchereVente" value="ventesNonDebutees"/>
						<label for="enchereVente">ventes non d�but�es</label>
						
						<input type="radio" name="enchereVente" value="ventesTerminees" />
						<label for="enchereVente">ventes termin�es</label>
						
					</div>
					<div id="divBouton">
						<button type="submit" id="btnRechercher" name="btnRechercher">Rechercher</button>
					</div>
				</form>
			</div>
		</div>
		
		<c:if test="${ !empty erreurs }">
			<div class="section">
				<ul>
		 		<c:forEach var="erreur" items="${ erreurs }">
		 			<li style="color:red">${ erreur }</li><br>
		 		</c:forEach>
				</ul>
			</div>
	 	</c:if>
	 	
		<div class="section">
		 	<c:if test="${ empty encheresCourantes && empty articlesEnVente }">
		 		<p>Aucun �l�ment � afficher</p>
		 	</c:if>
		 	<c:forEach var="enchere" items="${ encheresCourantes }">
		 		<div>
			 		<div>
			 			<img alt="" src="" height="200" width="200">
			 		</div>
			 		<ul>
						<li><a href="<%=request.getContextPath()%>/ActionEnchere?idArticle=${ enchere.getArticleVendu().getNoArticle()}">${ enchere.getArticleVendu().getNomArticle() }</a></li>
						
						<c:choose>
						    <c:when test="${ enchere.getArticleVendu().isEnVente() }">
						        <li>Enchere en cours : ${ enchere.montant_enchere }</li>
						        <li>Fin d'ench�re le : ${ enchere.getArticleVendu().getDateFinEncheres() }</li>
						    </c:when>    
						    <c:otherwise>
						        <li>Vendu : ${ enchere.montant_enchere }</li>
								<li>Fin de L'ench�re : ${ enchere.getArticleVendu().getDateFinEncheres() }</li>
						    </c:otherwise>
						</c:choose>
						<li>Vendeur : <a href="<%=request.getContextPath()%>/AutreProfil?idArticle=${ enchere.getArticleVendu().getNoArticle()}">${ enchere.getArticleVendu().getVendeur().getPseudo() }</a></li>
					</ul>		 		
		 		</div>
		 	</c:forEach>
		 	<c:forEach var="article" items="${ articlesEnVente }">
		 		<div>
			 		<div>
			 			<img alt="" src="" height="200" width="200">
			 		</div>
			 		<ul>
						<li><a href="<%=request.getContextPath()%>/ActionEnchere?idArticle=${ article.getNoArticle()}">${ article.nomArticle }</a></li>
						<c:choose>
						    <c:when test="${ enchere.getArticleVendu().isEnVente() }">
						        <li>Prix : ${ article.miseAPrix }</li>
								<li>Fin d'ench�re le : ${ article.dateFinEncheres }</li>
						    </c:when>    
						    <c:otherwise>
						        <li>Non vendu</li>
						        <li>Termin� le : ${ article.dateFinEncheres }</li>
						    </c:otherwise>
						</c:choose>
						<li>Vendeur : <a href="<%=request.getContextPath()%>/AutreProfil?idArticle=${ article.getNoArticle()}">${ article.getVendeur().getPseudo() }</a></li>
					</ul>
		 		</div>
		 	</c:forEach>

		</div>
	</div>
</body>
</html>