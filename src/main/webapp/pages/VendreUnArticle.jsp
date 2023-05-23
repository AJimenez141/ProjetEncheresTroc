<%@page import="java.util.List"%>
<%@page import="fr.eni.projet.bo.Categorie" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Vendre un article</title>
	<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet">
	<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	<h2 class="ombre_foncee">Nouvelle vente</h2>
	<div class="conteneur" id="VendreUnArticle">
		<div class="section">
			<div>
				<img alt="" src="" width="400" height="300">
			</div>
			
			<div>
				<form action="<%=request.getContextPath()%>/VendreUnArticle" method="POST">
		
					<label name="labelArticle" for="article">Article : </label>
					<input type="text" name="article"/>
					
					<label name="labelDescription" for="description">Description : </label>
					<input type="text" name="description"/>
					
					<label name="labelCategorie" for="categorie">Cat�gorie : </label>
					
					<% @SuppressWarnings("unchecked")
					List<Categorie> listeCategories = (List<Categorie>)request.getAttribute("listeCategories");
					%>
					
					<select name="selectCategories">
						<c:forEach var="categorie" items="${listeCategories}">
							<option value='<c:out value="${categorie.getNoCategorie()}"/>'><c:out value="${categorie.getLibelle()}"/></option>				
						</c:forEach>
					</select>
					
					<label name="labelPrixDepart" for="prixDepart">Prix de d�part : </label>
					<input type="number" name="prixDepart"/>
					
					<label name="labelDebutEnchere" for="debutEnchere">D�but de l'ench�re : </label>
					<input type="date" name="debutEnchere"/>
					
					<label name="labelFinEnchere" for="finEnchere">Fin de l'ench�re : </label>
					<input type="date" name="finEnchere"/>
					
					<h3>Retrait</h3><div></div>
					
					
					<label name="labelCheckbox" for="myCheck">Utiliser mon adresse: </label>
					<input type="checkbox" id="myCheck" onclick="toggle()">
					
					<label name="labelRue" for="rue">Rue : </label>
					<input type="text" id="rue" name="rue"/>
					
					<label name="labelCodePostal" for="codePostal">Code postal : </label>
					<input type="text" id="codePostal" name="codePostal"/>
					
					<label name="labelVille" for="ville">Ville : </label>
					<input type="text" id="ville" name="ville"/>
					
			
					<button class="bouton__enregistrer--couleur" id="btnEnregistrer" name="btnEnregistrer" type="submit">Enregistrer</button>
					<button id="btnAnnuler" name="btnAnnuler">Annuler</button>
			
				</form>
			</div>
		</div>
		

	</div>
</body>

<script>
function toggle() {
  document.getElementById("rue").disabled = !document.getElementById("rue").disabled;
  document.getElementById("codePostal").disabled = !document.getElementById("codePostal").disabled;
  document.getElementById("ville").disabled = !document.getElementById("ville").disabled;
}

</script>

</html>