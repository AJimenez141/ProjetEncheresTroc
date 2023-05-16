<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier catégorie</title>
<link href="../ressources/styles/main_test.css" rel="stylesheet">

</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<h2>Nouvelle catégorie</h2>
	
	<div class="conteneur" id="ModifierCategorie">
		<div class="section">
			<form method="GET">
				<div>
					<label for="libelle">Nom :</label>
					<input type="text" id="libelle" name="libelle">
				</div>
				<br>
				
				<a href="GestionCategories.jsp">
					<button class="button__enregistrer--color" id="btnEnregistrer" name="btnEnregistrer">Enregistrer</button>
				</a>
			</form>
		</div>
	</div>
</body>
</html>