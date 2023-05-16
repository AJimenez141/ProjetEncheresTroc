<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
</head>
<header>
	<h1>ENI - Enchères</h1>
</header>

<body>
	<div>
		<label for="id">Identifiant :</label>
		<input type="text" id="id" name="id">
	</div>
	<br>
	
	<div>
		<label for="mdp">Mot de passe :</label>
		<input type="password" id="mdp" name="mdp">
	</div>
	<br>
	
	<div>
		<a href="ListeEncheresConnecte.jsp">
			<button id="btnConnexion" name="btnConnexion">Connexion</button>
		</a>
		
		<input type="checkbox" id="ckSouvenir" name="ckSouvenir">
		<label for="ckSouvenir">Se souvenir de moi</label>
		<a href="https://www.google.com">Mot de passe oublié</a>
	</div>
	<br>
	
	<a href="CreerCompte.jsp">
		<button id="btnCreerCompte" name="btnCreerCompte">Créer un compte</button>
	</a>
	
</body>
</html>