<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
<link href="../ressources/styles/main_test.css" rel="stylesheet">

</head>
<header>
	<h1>ENI - Enchères</h1>
</header>

<body>
	<div class="conteneur" id="Connexion">
		<div class="section">
			<div>
					<label for="id">Identifiant :</label>
					<input type="text" id="id" name="id">
				<br>
				<br>
					<label for="mdp">Mot de passe :</label>
					<input type="password" id="mdp" name="mdp">
				<br>
				<br>
					<a href="ListeEncheresConnecte.jsp">
						<button id="btnConnexion" name="btnConnexion">Connexion</button>
					</a>
				<br>
				<br>
					<input type="checkbox" id="ckSouvenir" name="ckSouvenir">
					<label for="ckSouvenir">Se souvenir de moi</label>
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