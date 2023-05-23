<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des enchères</title>
<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet" type="text/css">
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	<h2 class="ombre_foncee">Liste des Enchères</h2>
	
	<div class="conteneur" id="ListeEncheresConnecte">
		<div class="section">
			<div>
				
				<form action="<%=request.getContextPath()%>/Accueil" method="POST">
					<h3>Filtres : </h3>
					
					<input type="text" name="filtre"/>
		
		
					<label name="labelCategorie" for="categorie">Categorie : </label>
					<select>
						<option value="toutes">Toutes</option>
					</select>
					
					<div id="divAchats">
						<h3><input type="radio" value="achats" name="choixListe"/>
						<label name="labelAchats" for="achats">Achats</label></h3>
							<input type="checkbox" name="enchereOuvertes"/>
							<label name="labelEncheresOuvertes" for="encheresOuvertes">enchères ouvertes</label>
							
							<input type="checkbox" name="mesEncheres"/>
							<label name="labelMesEncheres" for="mesEncheres">mes enchères</label>
							
							<input type="checkbox" name="mesEncheresRemportees"/>
							<label name="labelMesEncheresRemportees" for="mesEncheresRemportees">mes enchères remportées</label>
					</div>
					<div id="divVentes">
						<h3><input type="radio" value="mesVentes" name="choixListe"/>
						<label name="labelVentes" for="ventes">Mes Ventes</label></h3>
							<input type="checkbox" name="mesVentesEnCours"/>
							<label name="labelMesVentesEnCours" for="mesVentesEnCours">mes ventes en cours</label>
							
							<input type="checkbox" name="ventesNonDebutees"/>
							<label name="labelVentesNonDebutees" for="ventesNonDebutees">ventes non débutées</label>
							
							<input type="checkbox" name="ventesTerminees"/>
							<label name="labelVentesTerminees" for="ventesTerminees">ventes terminées</label>
					</div>
				</form>
			</div>
			<div id="divBouton">
				<button type="submit" id="btnRechercher" name="btnRechercher">Rechercher</button>
			</div>
		</div>
		
		<div class="section">
			<div>
				<img alt="" src="" height="200" width="200">
				<ul>
					<a href="<%=request.getContextPath()%>/Encherir"><li>PC Gamer pour travailler</li></a>
					<li>Prix : 210 points</li>
					<li>Fin de L'enchère : 10/08/2018</li>
					<li>Vendeur : <a href="Profil.jsp">RoiTacos</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>