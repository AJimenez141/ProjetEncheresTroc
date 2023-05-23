<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enchérir</title>
<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet" type="text/css">
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<h2>Détail vente</h2>
	<div class="conteneur" id="Encherir">
		<div class="section">
			<div>
				<img src="../ressources/images/tacos_dos.jpg" alt="J'ai faim !">
			</div>
			<div>
				<form action="">
					<div>
						XXX
						<br>
						<br>
							<label for="description">Description :</label>
							XXX						
						<br>
						<br>
							<label for="categorie">Catégorie :</label>
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
							<label for="proposition">Ma proposition :</label>
							<input type="number">
						<br>
						<br>
							<button class="bouton__enregistrer--couleur" id="btnEncherir" name="btnEncherir">Enchérir</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>