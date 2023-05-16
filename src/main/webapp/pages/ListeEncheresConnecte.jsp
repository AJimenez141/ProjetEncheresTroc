<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<jsp:include page="Header.jsp" />
<body>
	<h2>Liste des Enchères</h2>
	
	<div>
		<div>
			<h3>Filtres : </h3>
			
			<form action="<%=request.getContextPath()%>/ListeEncheresConnecte" method="POST">
			<input type="text" name="filtre"/>


			<label name="labelCategorie" for="categorie">Categorie : </label>
			<select>
				<option value="toutes">Toutes</option>
			</select>
			
			<div>
				<input type="radio" value="achats" name="choixListe"/>
				<label name="labelAchats" for="achats">Achats</label>
					<input type="checkbox" name="enchereOuvertes"/>
					<label name="labelEncheresOuvertes" for="encheresOuvertes">enchères ouvertes</label>
					
					<input type="checkbox" name="mesEncheres"/>
					<label name="labelMesEncheres" for="mesEncheres">mes enchères</label>
					
					<input type="checkbox" name="mesEncheresRemportees"/>
					<label name="labelMesEncheresRemportees" for="mesEncheresRemportees">mes enchères remportées</label>
				
				<input type="radio" value="mesVentes" name="choixListe"/>
				<label name="labelVentes" for="ventes">Mes Ventes</label>
					<input type="checkbox" name="mesVentesEnCours"/>
					<label name="labelMesVentesEnCours" for="mesVentesEnCours">mes ventes en cours</label>
					
					<input type="checkbox" name="ventesNonDebutees"/>
					<label name="labelVentesNonDebutees" for="ventesNonDebutees">ventes non débutées</label>
					
					<input type="checkbox" name="ventesTerminees"/>
					<label name="labelVentesTerminees" for="ventesTerminees">ventes terminées</label>
			</div>
	
		</form>
		</div>
		<div>
			<button name="btnRechercher" value="Submit">Rechercher</button>
		</div>
	</div>
	
	
	
	<div>
		<div>
			<img alt="" src="" height="200" width="200">
			<ul>
				<li>PC Gamer pour travailler</li>
				<li>Prix : 210 points</li>
				<li>Fin de L'enchère : 10/08/2018</li>
				<li>Vendeur : <a href="/Profil">RoiTacos</a></li>
			</ul>
		</div>
	</div>
</body>
</html>