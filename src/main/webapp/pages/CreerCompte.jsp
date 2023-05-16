<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Créer un compte</title>
</head>
<body>
	<h1>ENI - Enchères</h1>
	
	<h2>Mon profil</h2>
	
	<form method="POST">
		<div>
			<label for="pseudo">Pseudo :</label>
			<input type="text" id="pseudo" name="pseudo">
			<label for="nom">Nom :</label>
			<input type="text" id="nom" name="nom">
		</div>
		<br>
	
		<div>
			<label for="prenom">Prénom :</label>
			<input type="text" id="prenom" name="prenom">
			<label for="email">Email :</label>
			<input type="email" id="email" name="email">
		</div>
		<br>
		
		<div>
			<label for="tel">Téléphone :</label>
			<input type="text" id="tel" name="tel">
			<label for="rue">Rue :</label>
			<input type="text" id="rue" name="rue">
		</div>
		<br>
		
		<div>
			<label for="cp">Code postal :</label>
			<input type="text" id="cp" name="cp">
			<label for="ville">Ville :</label>
			<input type="text" id="ville" name="ville">
		</div>
		<br>
		
		<div>
			<label for="mdp">Mot de passe :</label>
			<input type="password" id="mdp" name="mdp">
			<label for="confirmMdp">Confirmation :</label>
			<input type="password" id="confirmMdp" name="confirmMdp">
		</div>
		<br>
		
		<input type="submit" id="btnCreer" name="btnCreer" value="Créer">
		<input type="button" id="btnAnnuler" name="btnAnnuler" value="Annuler">
	</form>
</body>
</html>