<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fr.eni.projet.bo.ArticleVendu" %>
<%@page import="fr.eni.projet.bo.Retrait" %>
<%@page import="fr.eni.projet.bo.Categorie" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enchère non commencé</title>
<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet">
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	<h2>Nouvelle vente</h2>
	
	<div class="conteneur" id="EnchereNonCommencee">
		<div class="section">
			<div>
				<img alt="" src="" width="400" height="300">		
			</div>
			<div>
				<form action="<%=request.getContextPath()%>/ActionEnchere" method="POST">
					<%ArticleVendu article = (ArticleVendu) request.getAttribute("article");%>
					<%Retrait retrait = (Retrait) request.getAttribute("retrait"); %>
					<label name="labelArticle" for="article">Article : </label>
					<input type="text" name="article" value="<c:out value="${article.getNomArticle()}"/>"/>
					
					<label name="labelDescription" for="description">Description : </label>
					<input type="text" name="description" value="<c:out value="${article.getDescription()}"/>"/>
					
					<label name="labelCategorie" for="categorie">Catégorie : </label>
					<% @SuppressWarnings("unchecked")
					List<Categorie> listeCategories = (List<Categorie>)request.getAttribute("listeCategories");
					%>
					<select name="selectCategories">
						<c:forEach var="categorie" items="${listeCategories}">
							<c:if test="${article.getCategorie().getLibelle() == categorie.getLibelle()}">
								<option selected value='<c:out value="${article.getCategorie().getNoCategorie()}"/>'><c:out value="${article.getCategorie().getLibelle()}" /></option>
							</c:if>		
								<option value='<c:out value="${categorie.getNoCategorie()}"/>'><c:out value="${categorie.getLibelle()}"/></option>		
						</c:forEach>
					</select>
					
					<label name="labelDebutEnchere" for="debutEnchere">Début de l'enchère : </label>
					<input type="date" name="debutEnchere" value="<c:out value="${article.getDateDebutEncheres()}"/>"/>
					
					<label name="labelFinEnchere" for="finEnchere">Fin de l'enchère : </label>
					<input type="date" name="finEnchere" value="<c:out value="${article.getDateFinEncheres()}"/>"/>
					
					<label name="labelPrixDepart" for="prixDepart">Prix: </label>
					<input type="number" name="prixDepart" value="<c:out value="${article.getMiseAPrix()}"/>"/>
					
					<h3>Retrait</h3><div></div>
					
					<label name="labelRue" for="rue">Rue : </label>
					<input type="text" name="rue" value="<c:out value="${retrait.getAdresse().getRue()}"/>"/>
					
					<label name="labelCodePostal" for="codePostal">Code postal : </label>
					<input type="text" name="codePostal" value="<c:out value="${retrait.getAdresse().getCodePostal()}"/>"/>
					
					<label name="labelVille" for="ville">Ville : </label>
					<input type="text" name="ville" value="<c:out value="${retrait.getAdresse().getVille()}"/>"/>
					
					<a href="<%=request.getContextPath()%>/ActionEnchere">
						<button type="submit" id="btnEnregistrer" name="btnEnregistrer">Enregistrer</button>
					</a>
					<a href="<%=request.getContextPath()%>/Accueil">
						<button type="button" id="btnAnnuler" name="btnAnnuler">Annuler</button>
					</a>
					<a href="<%=request.getContextPath()%>/Accueil">
						<button type="button" id="btnAnnuler" name="btnAnnuler">Annuler la vente</button>
					</a>
			
				</form>
			</div>
	</div>
	
	
	
	<div>
		<div>
			<img alt="" src="" height="200" width="200">
			<ul>
				<li>PC Gamer pour travailler</li>
				<li>Prix : 210 points</li>
				<li>Fin de L'enchère : 10/08/2018</li>
				<li>Vendeur : RoiTacos</li>
			</ul>
		</div>
	</div>
</body>
</html>