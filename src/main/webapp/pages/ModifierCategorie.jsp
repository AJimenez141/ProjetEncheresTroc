<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier cat�gorie</title>
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<h2>Nouvelle cat�gorie</h2>
	
	<form method="GET">
		<div>
			<label for="libelle">Nom :</label>
			<input type="text" id="libelle" name="libelle">
		</div>
		<br>
		
		<a href="GestionCategories.jsp">
			<button id="btnEnregistrer" name="btnEnregistrer">Enregistrer</button>
		</a>
	</form>
</body>
</html>