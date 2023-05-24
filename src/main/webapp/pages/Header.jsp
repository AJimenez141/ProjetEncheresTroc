<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fr.eni.projet.bo.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<div class="conteneur" id="Header">
		<div>
			<img alt="" src="<%=request.getContextPath()%>/ressources/images/tacos_logo.png">
			<a class="ombre_foncee" href="<%=request.getContextPath()%>/Accueil"><h1>Tacos - Enchères</h1></a>
		</div>
		<ul>
			<!-- <li><a class="ombre_foncee" href="GestionUtilisateurs.jsp">Gestion des utilisateurs</a></li>
			<li><a class="ombre_foncee" href="GestionCategories.jsp">Gestion des catégories</a></li> -->
			<li><a class="ombre_foncee" href="<%=request.getContextPath()%>/Accueil">Enchères</a></li>
			<li><a class="ombre_foncee" href="<%=request.getContextPath()%>/VendreUnArticle">Vendre un Article</a></li>
			<li><a class="ombre_foncee" href="<%=request.getContextPath()%>/Profil">Mon Profil</a></li>
			<li><a class="ombre_foncee" href="<%=request.getContextPath()%>/Deconnecter">Déconnexion</a></li>
		</ul>
	</div>
	<% Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur"); %>
	<div id="credit_volant">Crédit : <c:out value="${ utilisateur.getCredit() }"/></div>
</body>
</html>