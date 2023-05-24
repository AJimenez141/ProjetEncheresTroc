<%@page import="java.util.List"%>
<%@page import="fr.eni.projet.bo.Categorie" %>
<%@page import="fr.eni.projet.bo.Utilisateur" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Vendre un article</title>
	<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet" type="text/css">
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
				<form method="POST" onsubmit="return controleChamps()" action="<%=request.getContextPath()%>/VendreUnArticle">
		
					<label name="labelArticle" for="article">Article : </label>
					<input type="text" id="article" name="article"/>
					
					<label name="labelDescription" for="description">Description : </label>
					<input type="text" id="description" name="description"/>
					
					<label name="labelCategorie" for="categorie">Catégorie : </label>
					
					<% @SuppressWarnings("unchecked")
					Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateur");
					List<Categorie> listeCategories = (List<Categorie>)request.getAttribute("listeCategories");
					%>
					
					<select name="selectCategories">
						<c:forEach var="categorie" items="${listeCategories}">
							<option value='<c:out value="${categorie.getNoCategorie()}"/>'><c:out value="${categorie.getLibelle()}"/></option>				
						</c:forEach>
					</select>
					
					<label name="labelPrixDepart" for="prixDepart">Prix de départ : </label>
					<input type="number" id="prixDepart" name="prixDepart"/>
					
					<label name="labelDebutEnchere" for="debutEnchere">Début de l'enchère : </label>
					<input type="date" id="debutEnchere" name="debutEnchere"/>
					
					<label name="labelFinEnchere" for="finEnchere">Fin de l'enchère : </label>
					<input type="date" id="finEnchere" name="finEnchere"/>
					
					<h3>Retrait</h3><div></div>
					
					
					<label name="labelCheckbox" for="chkMonAdresse">Utiliser mon adresse: </label>
					<input type="checkbox" id="chkMonAdresse" name="chkMonAdresse" onclick="toggle()" checked>
					
					<label name="labelRue" for="rue">Rue : </label>
					<input disabled type="text" id="rue" name="rue" value="${utilisateur.getAdresse().getRue()}"/>
					
					<label name="labelCodePostal" for="codePostal">Code postal : </label>
					<input disabled type="text" id="codePostal" name="codePostal" value="${utilisateur.getAdresse().getCodePostal()}"/>
					
					<label name="labelVille" for="ville">Ville : </label>
					<input disabled type="text" id="ville" name="ville" value="${utilisateur.getAdresse().getVille()}"/>
					
			
					<button class="bouton__enregistrer--couleur" id="btnEnregistrer" name="btnEnregistrer" type="submit">Enregistrer</button>
					<button id="btnAnnuler" name="btnAnnuler"  type="button">Annuler</button>
			
				</form>
			</div>
		</div>
		

	</div>
</body>

<script type="text/javascript">
function toggle() {
  
  if(document.getElementById("rue").disabled){
	  
	  document.getElementById("rue").value = "";
	  document.getElementById("rue").disabled = false;
  } else {
	  document.getElementById("rue").value = document.getElementById("rue").defaultValue;
	  document.getElementById("rue").disabled = true; 
  }
  
  if(document.getElementById("codePostal").disabled){
	  
	  document.getElementById("codePostal").value = "";
	  document.getElementById("codePostal").disabled = false;
  } else {
	  document.getElementById("codePostal").value = document.getElementById("codePostal").defaultValue;
	  document.getElementById("codePostal").disabled = true; 
  }
  
  if(document.getElementById("ville").disabled){
	  
	  document.getElementById("ville").value = "";
	  document.getElementById("ville").disabled = false;
  } else {
	  document.getElementById("ville").value = document.getElementById("ville").defaultValue;
	  document.getElementById("ville").disabled = true; 
  }
  
}

function controleChamps() {
	
	var ok = true;
	
	if(document.querySelector("#article").value === "")
	{
		ok = false;
	}
	
	if(document.querySelector("#description").value === "")
	{
		ok = false;
	}
	
	if(document.querySelector("#prixDepart").value === "")
	{
		ok = false;
	}
	
	if(document.querySelector("#debutEnchere").value === "")
	{
		ok = false;
	}
	
	if(document.querySelector("#finEnchere").value === "")
	{
		ok = false;
	}
	
	if(document.querySelector("#rue").value === "" || document.querySelector("#codePostal").value === "" || document.querySelector("#ville").value === "")
	{
		ok = false;
	}
	
	if(!ok)
	{
		alert("Merci de renseigner tous les champs");
		return false;
	} 
}

</script>

</html>