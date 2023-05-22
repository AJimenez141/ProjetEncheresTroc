<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Créer un compte</title>
<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet">
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
</head>
<header>
	<div class="conteneur" id="Header">
		<a href="AccueilNonConnecte.jsp"><h1 class="ombre_foncee">Tacos - Enchères</h1></a>
	</div>
</header>

<body>
	<h2>Mon profil</h2>
	<div class="conteneur" id="CreerCompte">
		<div class="section">
			<form method="POST">
				<div id="section_profil">
					<div>
						<label for="pseudo">Pseudo :</label>
						<input type="text" id="pseudo" name="pseudo">
					<br>
					<br>
						<label for="prenom">Prénom :</label>
						<input type="text" id="prenom" name="prenom">
					<br>
					<br>
						<label for="tel">Téléphone :</label>
						<input type="text" id="tel" name="tel">
					<br>
					<br>
						<label for="cp">Code postal :</label>
						<input type="text" id="cp" name="cp">
					<br>
					<br>	
						<label for="mdp">Mot de passe :</label>
						<input type="password" id="mdp" name="mdp">
					<br>
					<br>
					</div>
					
					<div>
						<label for="nom">Nom :</label>
						<input type="text" id="nom" name="nom">
					<br>
					<br>
						<label for="email">Email :</label>
						<input type="email" id="email" name="email">
					<br>
					<br>
						<label for="rue">Rue :</label>
						<input type="text" id="rue" name="rue">
					<br>
					<br>
						<label for="ville">Ville :</label>
						<input type="text" id="ville" name="ville">
					<br>
					<br>
						<label for="confirmMdp">Confirmation :</label>
						<input type="password" id="confirmMdp" name="confirmMdp">
					<br>
					<br>
					</div>
				</div>
				<div id="section_profil_bouton">
					<a href="ListeEncheresConnecte.jsp">
						<button type="submit" class="bouton__enregistrer--couleur" id="btnCreer" name="btnCreer">Créer</button>
					</a>
					<a href="Connexion.jsp">
						<button id="btnAnnuler" name="btnAnnuler">Annuler</button>
					</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>