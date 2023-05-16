<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion des catégories</title>
<link href="../ressources/styles/main_test.css" rel="stylesheet">

</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<h2>Gestion des catégories</h2>
	<br>
	<div class="conteneur" id="GestionCategories">
		<div class="section">
			<table>
				<caption>Liste des catégories</caption>
				<tbody>
					<tr>
						<th scope="col">Nom de la catégorie</th>
		      			<th scope="col">Nombre d'articles</th>
					</tr>
					<tr>
						<td>XXX</td>
						<td>XXX</td>
						<td>
							<a href="ModifierCategorie.jsp">
								<button id="btnModifier" name="btnModifier">Modifier</button>
							</a>
						</td>
						<td>
							<button class="button__supprimer--color" id="btnSupprimer" name="btnSupprimer">Supprimer</button>
						</td>
					</tr>
				</tbody>
			</table>
			<br>
			
			<a href="AjouterCategorie.jsp">
				<button class="button__enregistrer--color" id="btnAjouter" name="btnAjouter">Ajouter</button>
			</a>
		</div>
	</div>
</body>
</html>