<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détail fin enchère</title>
<link href="../ressources/styles/main_test.css" rel="stylesheet">
<link rel="icon" type="image/png" href="../ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<h2>XXX a remporté l'enchère</h2>
	<div class="conteneur" id="DetailMaVenteFinEnchere">
		<div class="section">
			<div>
				<img src="../ressources/images/tacos_tres.jpg" alt="Manger, manger !">
			</div>
			<div>
				<form>
					<div>
							XXX
						<br>
						<br>
							<label for="description">Description :</label>
							XXX
						<br>
						<br>
							<label for="offre">Meilleur offre :</label>
							XXX
						<br>
						<br>
							<label for="prix">Mise à prix :</label>
							XXX
						<br>
						<br>
							<label for="finEnchere">Fin de l'enchère :</label>
							XXX
						<br>
						<br>
							<label for="retrait">Retrait :</label>
							XXX
						<br>
						<br>
							<label for="vendeur">Vendeur :</label>
							XXX
						<br>
						<br>
						<a href="ListeEncheresConnecte.jsp">
							<button id="btnRetraitEffec" class="bouton__enregistrer--couleur" name="btnRetraitEffec">Retrait effectué</button>
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>