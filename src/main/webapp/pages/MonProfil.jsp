<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fr.eni.projet.bo.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mon Profil</title>
<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet" type="text/css">
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	
	<div class="conteneur" id="MonProfil">
		<div class="section" id="section_profil">
			<%Utilisateur util = (Utilisateur)request.getAttribute("util");%>
				<label for="pseudo">Pseudo :</label>
				<c:out value="${util.getPseudo()}"/>
			<br>
			<br>
				<label for="nom">Nom :</label>
				<c:out value="${util.getNom()}"/>			
			<br>
			<br>			
				<label for="prenom">Pr�nom :</label>
				<c:out value="${util.getPrenom()}"/>		
			<br>
			<br>			
				<label for="email">Email :</label>
				<c:out value="${util.getEmail()}"/>			
			<br>
			<br>			
				<label for="tel">T�l�phone :</label>
				<c:out value="${util.getTelephone()}"/>		
			<br>
			<br>					
				<label for="rue">Rue :</label>
				<c:out value="${util.getAdresse().getRue()}"/>		
			<br>
			<br>			
				<label for="cp">Code postal :</label>
				<c:out value="${util.getAdresse().getCodePostal()}"/>
			<br>
			<br>
				<label for="ville">Ville :</label>
				<c:out value="${util.getAdresse().getVille()}"/>
			<br>
			<br>			
				<a href="<%=request.getContextPath()%>/ModifierProfil">
					<button id="btnModifier" name="btnModifier">Modifier</button>
				</a>
		</div>
	</div>
</body>
</html>