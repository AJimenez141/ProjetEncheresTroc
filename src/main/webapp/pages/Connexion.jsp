<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
<link href="../ressources/styles/main.css" rel="stylesheet">
<link rel="icon" type="image/png" href="../ressources/images/tacos_favicon.png">
</head>
<header>
	<h1>ENI - Enchères</h1>
</header>

<body>
	<div class="conteneur" id="Connexion">
		<div class="section" id="section_connexion">
			<div id="section_connexion_div">
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
					<a href="ListeEncheresConnecte.jsp">
						<button id="btnConnexion" name="btnConnexion">Connexion</button>
					</a>
				<br>
				<br>
					<input type="checkbox" id="ckSouvenir" name="ckSouvenir">
					<label for="ckSouvenir">Se souvenir de moi</label>
				<br>
					<a href="https://www.google.com">Mot de passe oublié</a>
				<br>
				<br>
				<a href="CreerCompte.jsp">
					<button class="bouton__enregistrer--couleur" id="btnCreerCompte" name="btnCreerCompte">Créer un compte</button>
				</a>
			</div>
		</div>
	</div>
</body>
</html>