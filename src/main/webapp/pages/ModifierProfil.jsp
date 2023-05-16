<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier profil</title>
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
			<label for="mdpActuel">Mot de passe :</label>
			<input type="password" id="mdpActuel" name="mdpActuel">
		</div>
		<br>
		
		<div>
			<label for="mdpNouveau">Nouveau mot de passe :</label>
			<input type="password" id="mdpNouveau" name="mdpNouveau">
			<label for="confirmMdp">Confirmation :</label>
			<input type="password" id="confirmMdp" name="confirmMdp">
		</div>
		<br>
		
		<div>
			<label>Crédit :</label>
			XXX
		</div>
		<br>
		
		<!-- Refresh de la page ? -->
		<input type="submit" id="btnEnregistrer" name="btnEnregistrer" value="Enregistrer">
		
		<a href="AccueilNonConnecte.jsp">
			<input type="button" id="btnSupprimer" name="btnSupprimer" value="Supprimer mon compte">
		</a>
	</form>
</body>
</html>