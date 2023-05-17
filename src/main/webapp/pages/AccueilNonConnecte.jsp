<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
<link href="../ressources/styles/main.css" rel="stylesheet">
<link rel="icon" type="image/png" href="../ressources/images/tacos_favicon.png">
</head>
	<%-- <jsp:include page="Header.jsp" /> --%>
<header>
	<h1>ENI-Enchères</h1>
	<ul>
		<li><a href="CreerCompte.jsp">S'inscrire</a></li>
		<li><a href="Connexion.jsp">Se Connecter</a></li>
	</ul>
</header>
<body>
	<h2>Liste des Enchères</h2>
	<div class="conteneur" id="AccueilNonConnecte">
		<div class="section">
			<div>
				<h3>Filtres : </h3>
				
				<form action="<%=request.getContextPath()%>/AccueilNonConnecte" method="GET">
	
					<input type="text" name="filtre"/>
					
					<label name="labelCategorie" for="categorie">Categorie : </label>
					<select>
						<option value="toutes">Toutes</option>
					</select>
		
				</form>
			</div>
			<div id="divBouton">
				<button class="couleur__bouton--general"type="submit" id="btnRechercher" name="btnRechercher">Rechercher</button>
			</div>
		</div>
		
		<div class="section">
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
		
	</div>
</body>
</html>