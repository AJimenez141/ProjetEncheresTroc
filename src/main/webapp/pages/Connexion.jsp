<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
</head>
<body>
	<h1>ENI - Enchères</h1>
	
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
		<input type="button" id="btnConnexion" name="btnConnexion" value="Connexion">
		
		<input type="checkbox" id="ckSouvenir" name="ckSouvenir">
		<label for="ckSouvenir">Se souvenir de moi</label>
		<a href="https://www.google.com">Mot de passe oublié</a>
	</div>
	<br>
	
	<a href="CreerCompte.jsp">
		<input type="button"id="btnCreerCompte" name="btnCreerCompte" value="Créer un compte">
	</a>
	
</body>
</html>