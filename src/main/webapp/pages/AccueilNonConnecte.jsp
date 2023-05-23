<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<div>
				
				<form action="<%=request.getContextPath()%>/AccueilNonConnecte" method="GET">
					<h3>Filtres : </h3>
	
					<input type="text" name="filtre"/>
					
					<label name="labelCategorie" for="categorie">Categorie : </label>
					<select name="selectCategorie">
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