<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enchère non commencé</title>
<link href="../ressources/styles/main.css" rel="stylesheet">
<link rel="icon" type="image/png" href="../ressources/images/tacos_favicon.png">
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
				<form action="<%=request.getContextPath()%>/VendreUnArticle" method="POST">
	
					<label name="labelArticle" for="article">Article : </label>
					<input type="text" name="article"/>
					
					<label name="labelDescription" for="description">Description : </label>
					<input type="text" name="description"/>
					
					<label name="labelCategorie" for="categorie">Catégorie : </label>
					<select>
						<option value=""></option>
					</select>
					
					<label name="labelDebutEnchere" for="debutEnchere">Début de l'enchère : </label>
					<input type="date" name="debutEnchere"/>
					
					<label name="labelFinEnchere" for="finEnchere">Fin de l'enchère : </label>
					<input type="date" name="finEnchere"/>
					
					<h3>Retrait</h3><div></div>
					
					<label name="labelRue" for="rue">Rue : </label>
					<input type="text" name="rue"/>
					
					<label name="labelCodePostal" for="codePostal">Code postal : </label>
					<input type="text" name="codePostal"/>
					
					<label name="labelVille" for="ville">Ville : </label>
					<input type="text" name="ville"/>
					
					<a href="ListeEncheresConnecte.jsp">
						<button type="submit" id="btnEnregistrer" name="btnEnregistrer">Enregistrer</button>
					</a>
					<a href="LsiteEncheresConnecte.jsp">
						<button id="btnAnnuler" name="btnAnnuler">Annuler</button>
					</a>
					<a href="LsiteEncheresConnecte.jsp">
						<button id="btnAnnuler" name="btnAnnuler">Annuler la vente</button>
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