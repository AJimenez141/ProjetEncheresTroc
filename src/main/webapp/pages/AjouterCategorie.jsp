<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter catégorie</title>
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>	
	<h2>Nouvelle catégorie</h2>
	
	<form method="POST">
		<div>
			<label for="pseudo">Nom :</label>
			<input type="text" id="pseudo" name="pseudo">
		</div>
		<br>
		
		<a href="GestionCategories.jsp">
			<button type="submit" id="btnCreer" name="btnCreer">Créer</button>
		</a>
	</form>
</body>
</html>