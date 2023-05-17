<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des ench�res</title>
<link href="../ressources/styles/main.css" rel="stylesheet">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	<h2>Liste des Ench�res</h2>
	
	<div class="conteneur">
		<div class="section">
			<div>
				
				<form action="<%=request.getContextPath()%>/ListeEncheresConnecte" method="POST">
					<h3>Filtres : </h3>
					
					<input type="text" name="filtre"/>
		
		
					<label name="labelCategorie" for="categorie">Categorie : </label>
					<select>
						<option value="toutes">Toutes</option>
					</select>
					
					<div>
						<input type="radio" value="achats" name="choixListe"/>
						<label name="labelAchats" for="achats">Achats</label>
							<input type="checkbox" name="enchereOuvertes"/>
							<label name="labelEncheresOuvertes" for="encheresOuvertes">ench�res ouvertes</label>
							
							<input type="checkbox" name="mesEncheres"/>
							<label name="labelMesEncheres" for="mesEncheres">mes ench�res</label>
							
							<input type="checkbox" name="mesEncheresRemportees"/>
							<label name="labelMesEncheresRemportees" for="mesEncheresRemportees">mes ench�res remport�es</label>
						
						<input type="radio" value="mesVentes" name="choixListe"/>
						<label name="labelVentes" for="ventes">Mes Ventes</label>
							<input type="checkbox" name="mesVentesEnCours"/>
							<label name="labelMesVentesEnCours" for="mesVentesEnCours">mes ventes en cours</label>
							
							<input type="checkbox" name="ventesNonDebutees"/>
							<label name="labelVentesNonDebutees" for="ventesNonDebutees">ventes non d�but�es</label>
							
							<input type="checkbox" name="ventesTerminees"/>
							<label name="labelVentesTerminees" for="ventesTerminees">ventes termin�es</label>
					</div>
				</form>
			</div>
			<div>
				<button type="submit" id="btnRechercher" name="btnRechercher">Rechercher</button>
			</div>
		</div>
		
		<div class="section">
			<div>
				<img alt="" src="" height="200" width="200">
				<ul>
					<li>PC Gamer pour travailler</li>
					<li>Prix : 210 points</li>
					<li>Fin de L'ench�re : 10/08/2018</li>
					<li>Vendeur : <a href="Profil.jsp">RoiTacos</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>