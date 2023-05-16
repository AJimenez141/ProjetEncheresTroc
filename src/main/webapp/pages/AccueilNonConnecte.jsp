<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<%-- <jsp:include page="Header.jsp" /> --%>
<header>
	<h1>ENI-Enchères</h1>
	<ul>
		<li><a href="/Inscription">S'inscrire</a></li>
		<li><a href="/Connexion">Se Connecter</a></li>
	</ul>
</header>
<body>
	<h2>Liste des Enchères</h2>
	
	<div>
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
				<li>Vendeur : RoiTacos</li>
			</ul>
		</div>
	</div>
</body>
</html>