<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mon Profil</title>
<link href="../ressources/styles/main_test.css" rel="stylesheet">

</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<div class="conteneur" id="MonProfil">
		<div class="section">
			<div>
				<label for="pseudo">Pseudo :</label>
				XXX
				<br>
				<label for="nom">Nom :</label>
				XXX			
				<br>			
				<label for="prenom">Pr�nom :</label>
				XXX			
				<br>			
				<label for="email">Email :</label>
				XXX			
				<br>			
				<label for="tel">T�l�phone :</label>
				XXX			
				<br>					
				<label for="rue">Rue :</label>
				XXX			
				<br>			
				<label for="cp">Code postal :</label>
				XXX
				<br>
				<label for="ville">Ville :</label>
				XXX
				<br>
							
				<a href="ModifierProfil.jsp">
					<button id="btnModifier" name="btnModifier">Modifier</button>
				</a>
			</div>
		</div>
	</div>
</body>
</html>