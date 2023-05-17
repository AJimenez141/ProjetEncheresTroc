<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter cat�gorie</title>
<link href="../ressources/styles/main_test.css" rel="stylesheet">

</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>	
	<h2>Nouvelle cat�gorie</h2>
	
	<div class="conteneur" id="AjouterCategorie">
		<div class="section">
			<form method="POST">
				<div>
					<label for="libelle">Libell� :</label>
					<input type="text" id="libelle" name="libelle">
				</div>
				<br>
				
				<a href="GestionCategories.jsp">
					<button type="submit" class="button__enregistrer--color" id="btnCreer" name="btnCreer">Cr�er</button>
				</a>
			</form>
		</div>
	</div>
</body>
</html>