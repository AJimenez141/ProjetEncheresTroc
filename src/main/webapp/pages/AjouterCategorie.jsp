<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter catégorie</title>
<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet">
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>	
	<h2>Nouvelle catégorie</h2>
	
	<div class="conteneur" id="AjouterCategorie">
		<div class="section">
			<form method="POST">
				<div>
					<label for="libelle">Libellé :</label>
					<input type="text" id="libelle" name="libelle">
				</div>
				<br>
				
				<a href="GestionCategories.jsp">
					<button type="submit" class="bouton__enregistrer--couleur" id="btnCreer" name="btnCreer">Créer</button>
				</a>
			</form>
		</div>
	</div>
</body>
</html>