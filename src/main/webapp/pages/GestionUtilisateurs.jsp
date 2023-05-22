<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion des utilisateurs</title>
<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet">
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<h2 class="ombre_foncee">Gestion des utilisateurs</h2>
	
	<div class="conteneur" id="GestionUtilisateurs">
		<div class="section">
			<table>
				<thead class="couleur__fond_jaune">
					<tr>
						<th scope="col">Pseudo</th>
		      			<th scope="col">Nom</th>
		      			<th scope="col">Prénom</th>
		      			<th scope="col">Email</th>
		      			<th scope="col">Téléphone</th>
		      			<th scope="col">Rue</th>
		      			<th scope="col">Code postal</th>
		      			<th scope="col">Ville</th>
		      			<th scope="col">Etat</th>
		      		</tr>
				</thead>
				<tbody>
					<tr class="couleur__fond_orange">
						<td>XXX</td>
						<td>XXX</td>
						<td>XXX</td>
						<td>XXX</td>
						<td>XXX</td>
						<td>XXX</td>
						<td>XXX</td>
						<td>XXX</td>
						<td>
							<select>
								<option>Actif</option>
								<option>Inactif</option>
							</select>
						</td>
						<td>
							<button class="bouton__supprimer--couleur" id="btnSupprimer" name="btnSupprimer">Supprimer</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>