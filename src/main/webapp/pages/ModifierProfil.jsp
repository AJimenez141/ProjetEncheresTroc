<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier profil</title>
<link href="../ressources/styles/main_test.css" rel="stylesheet">

</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<h2>Mon profil</h2>
	
	<div class="conteneur" id="ModifierProfil">
		<div class="section">
			<form method="POST">
				<div>
					<label for="pseudo">Pseudo :</label>
					<input type="text" id="pseudo" name="pseudo">
				<br>
					<label for="prenom">Prénom :</label>
					<input type="text" id="prenom" name="prenom">
				<br>
					<label for="tel">Téléphone :</label>
					<input type="text" id="tel" name="tel">
				<br>
					<label for="cp">Code postal :</label>
					<input type="text" id="cp" name="cp">
				<br>	
					<label for="mdp">Mot de passe :</label>
					<input type="password" id="mdp" name="mdp">
				<br>
					<label for="mdpNouveau">Nouveau mot de passe :</label>
					<input type="password" id="mdpNouveau" name="mdpNouveau">
				<br>
					<label>Crédit :</label>
					XXX
				<br>
				</div>
				
				<div>
					<label for="nom">Nom :</label>
					<input type="text" id="nom" name="nom">
				<br>
					<label for="email">Email :</label>
					<input type="email" id="email" name="email">
				<br>
					<label for="rue">Rue :</label>
					<input type="text" id="rue" name="rue">
				<br>
					<label for="ville">Ville :</label>
					<input type="text" id="ville" name="ville">
				<br>
					<label for="confirmMdp">Confirmation :</label>
					<input type="password" id="confirmMdp" name="confirmMdp">
				<br>
				</div>
				
				<a href="MonProfil.jsp">
					<button type="submit" class="button__enregistrer--color" id="btnEnregistrerTest" name="btnEnregistrerTest">Enregistrer</button>
				</a>
				<a href="AccueilNonConnecte.jsp">
					<button class="button__supprimer--color" id="btnSupprimerTest" name="btnSupprimerTest">Supprimer mon compte</button>
				</a>
			</form>
		</div>
	</div>
</body>
</html>