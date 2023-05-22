<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet">
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
</head>
<header>
	<div class="conteneur" id="Header">
		<a href="AccueilNonConnecte.jsp"><h1 class="ombre_foncee">Tacos - Enchères</h1></a>
	</div>
</header>

<body>
	<div class="conteneur" id="Connexion">
		<div class="section" id="section_connexion">
			<form id="section_connexion_div" method="post">
				<div id="section_connexion_id_div">
						<label for="id">Identifiant :</label>
						<input type="text" id="id" name="id">
					<br>
					<br>
						<label for="mdp">Mot de passe :</label>
						<input type="password" id="mdp" name="mdp">
					<br>
					<br>
				</div>
					<a href="/Connexion">
						<button id="btnConnexion" name="btnConnexion">Connexion</button>
					</a>
				<br>
				<br>
					<input type="checkbox" id="ckSouvenir" name="ckSouvenir">
					<label for="ckSouvenir">Se souvenir de moi</label>
				<br>
					<a href="#">Mot de passe oublié</a>
				<br>
				<br>
				<a href="/Inscription">
					<button class="bouton__enregistrer--couleur" id="btnCreerCompte" name="btnCreerCompte">Créer un compte</button>
				</a>
			</form>
		</div>
	</div>
</body>
</html>