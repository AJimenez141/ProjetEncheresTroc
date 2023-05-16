<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion des utilisateurs</title>
<link href="../ressources/styles/main_test.css" rel="stylesheet">

</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<h2>Gestion des utilisateurs</h2>
	
	<div class="conteneur" id="GestionUtilisateurs">
		<div class="section">
			<table>
				<caption>Liste des utilisateurs</caption>
				<tbody>
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
					<tr>
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
							<button class="button__supprimer--color" id="btnSupprimer" name="btnSupprimer">Supprimer</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>