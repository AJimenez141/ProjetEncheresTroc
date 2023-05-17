<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mon Profil</title>
<link href="../ressources/styles/main_test.css" rel="stylesheet">
<link rel="icon" type="image/png" href="../ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<div class="conteneur" id="MonProfil">
		<div class="section" id="section_profil">
				<label for="pseudo">Pseudo :</label>
				XXX
			<br>
			<br>
				<label for="nom">Nom :</label>
				XXX			
			<br>
			<br>			
				<label for="prenom">Prénom :</label>
				XXX			
			<br>
			<br>			
				<label for="email">Email :</label>
				XXX			
			<br>
			<br>			
				<label for="tel">Téléphone :</label>
				XXX			
			<br>
			<br>					
				<label for="rue">Rue :</label>
				XXX			
			<br>
			<br>			
				<label for="cp">Code postal :</label>
				XXX
			<br>
			<br>
				<label for="ville">Ville :</label>
				XXX
			<br>
			<br>			
				<a href="ModifierProfil.jsp">
					<button id="btnModifier" name="btnModifier">Modifier</button>
				</a>
		</div>
	</div>
</body>
</html>