<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion des catégories</title>
<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet">
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<h2 class="ombre_foncee">Gestion des catégories</h2>
	<br>
	<div class="conteneur" id="GestionCategories">
		<div class="section">
			<table>
				<thead class="couleur__fond_jaune">
					<tr>
						<th scope="col">Nom de la catégorie</th>
		      			<th scope="col">Nombre d'articles</th>
					</tr>
				</thead>
				<tbody class="couleur__fond_orange">
					<tr>
						<td>XXX</td>
						<td>XXX</td>
						<td>
							<a href="ModifierCategorie.jsp">
								<button id="btnModifier" name="btnModifier">Modifier</button>
							</a>
						</td>
						<td>
							<button class="bouton__supprimer--couleur" id="btnSupprimer" name="btnSupprimer">Supprimer</button>
						</td>
					</tr>
				</tbody>
			</table>

			<div>
				<a href="AjouterCategorie.jsp">
					<button id="boutonAjouter" class="button__enregistrer--color" id="btnAjouter" name="btnAjouter">Ajouter</button>
				</a>			
			</div>
		</div>
	</div>
</body>
</html>