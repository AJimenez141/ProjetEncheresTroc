<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier catégorie</title>
<link href="../ressources/styles/main.css" rel="stylesheet">
<link rel="icon" type="image/png" href="../ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<h2>Modifier catégorie</h2>
	
	<div class="conteneur" id="ModifierCategorie">
		<div class="section">
			<form method="GET">
				<div>
					<label for="libelle">Libellé :</label>
					<input type="text" id="libelle" name="libelle">
				</div>
				<br>
				<a href="GestionCategories.jsp">
					<button class="bouton__enregistrer--couleur" id="btnEnregistrer" name="btnEnregistrer">Enregistrer</button>
				</a>
			</form>
		</div>
	</div>
</body>
</html>