<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<div class="conteneur" id="Header">
		<div>
			<img alt="" src="../ressources/images/tacos_logo.png">
			<a class="ombre_foncee" href="<%=request.getContextPath()%>/Accueil"><h1>Tacos - Ench�res</h1></a>
		</div>
		<ul>
			<li><a class="ombre_foncee" href="GestionUtilisateurs.jsp">Gestion des utilisateurs</a></li>
			<li><a class="ombre_foncee" href="GestionCategories.jsp">Gestion des cat�gories</a></li>
			<li><a class="ombre_foncee" href="action="<%=request.getContextPath()%>/Accueil"">Ench�res</a></li>
			<li><a class="ombre_foncee" href="action="<%=request.getContextPath()%>/VendreUnArticle"">Vendre un Article</a></li>
			<li><a class="ombre_foncee" href="action="<%=request.getContextPath()%>/Profil"">Mon Profil</a></li>
			<li><a class="ombre_foncee" href="action="<%=request.getContextPath()%>/AccueilNonConnecte"">D�connexion</a></li>
		</ul>
	</div>
</body>
</html>