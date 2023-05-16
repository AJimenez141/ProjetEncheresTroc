<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier profil</title>
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<h2>Mon profil</h2>
	
	<div class="conteneur" id="ModifierProfil">
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
			
			<a href="MonProfil.jsp">
				<button type="submit" id="btnEnregistrerTest" name="btnEnregistrerTest">Enregistrer</button>
			</a>
			<a href="AccueilNonConnecte.jsp">
				<button id="btnSupprimerTest" name="btnSupprimerTest">Supprimer mon compte</button>
			</a>
		</form>
	</div>
</body>
</html>