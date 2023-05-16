<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion des catégories</title>
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<h2>Gestion des catégories</h2>
	<br>
	
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
					<button id="btnSupprimer" name="btnSupprimer">Supprimer</button>
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	
	<a href="AjouterCategorie.jsp">
		<button id="btnAjouter" name="btnAjouter">Ajouter</button>
	</a>
</body>
</html>