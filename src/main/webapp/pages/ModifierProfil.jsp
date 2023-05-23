<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fr.eni.projet.bo.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier profil</title>
<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet" type="text/css">
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<h2>Mon profil</h2>
	
	<div class="conteneur" id="ModifierProfil">
		<div class="section">
			<form action="<%=request.getContextPath()%>/ModifierProfil" method="POST">
				<div id="section_profil">
					<%Utilisateur util = (Utilisateur)request.getAttribute("util");%>
					<div>
						<label for="pseudo">Pseudo :</label>
						<input type="text" id="pseudo" name="pseudo" value="<c:out value="${util.getPseudo()}"/>">
					<br>
					<br>
						<label for="prenom">Prénom :</label>
						<input type="text" id="prenom" name="prenom" value="<c:out value="${util.getPrenom()}"/>">
					<br>
					<br>
						<label for="tel">Téléphone :</label>
						<input type="text" id="tel" name="tel" value="<c:out value="${util.getTelephone()}"/>">
					<br>
					<br>
						<label for="cp">Code postal :</label>
						<input type="text" id="cp" name="cp" value="<c:out value="${util.getAdresse().getCodePostal()}"/>">
					<br>
					<br>	
						<label for="mdp">Mot de passe :</label>
						<input type="password" id="mdp" name="mdp">
					<br>
					<br>
						<label for="mdpNouveau">Nouveau mot de passe :</label>
						<input type="password" id="mdpNouveau" name="mdpNouveau">
					<br>
					<br>
						<label>Crédit :</label>
						<c:out value="${util.getCredit()}"/>
					<br>
					<br>
					</div>
					
					<div>
						<label for="nom">Nom :</label>
						<input type="text" id="nom" name="nom" value="<c:out value="${util.getNom()}"/>">
					<br>
					<br>
						<label for="email">Email :</label>
						<input type="email" id="email" name="email" value="<c:out value="${util.getEmail()}"/>">
					<br>
					<br>
						<label for="rue">Rue :</label>
						<input type="text" id="rue" name="rue" value="<c:out value="${util.getAdresse().getRue()}"/>">
					<br>
					<br>
						<label for="ville">Ville :</label>
						<input type="text" id="ville" name="ville" value="<c:out value="${util.getAdresse().getVille()}"/>">
					<br>
					<br>
						<label for="confirmMdp">Confirmation :</label>
						<input type="password" id="confirmMdp" name="confirmMdp">
					<br>
					<br>
					</div>
				</div>
				<div id="section_profil_bouton">
					<a href="<%=request.getContextPath()%>/Profil">
						<button type="submit" class="bouton__enregistrer--couleur" id="btnEnregistrerTest" name="btnEnregistrerTest">Enregistrer</button>
					</a>
					<a href="AccueilNonConnecte.jsp">
						<button class="bouton__supprimer--couleur" id="btnSupprimerTest" name="btnSupprimerTest">Supprimer mon compte</button>
					</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>