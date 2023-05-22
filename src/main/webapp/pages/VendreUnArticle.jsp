<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Vendre un article</title>
	<link href="<%=request.getContextPath()%>/ressources/styles/main.css" rel="stylesheet">
	<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/ressources/images/tacos_favicon.png">
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	<h2 class="ombre_foncee">Nouvelle vente</h2>
	<div class="conteneur" id="VendreUnArticle">
		<div class="section">
			<div>
				<img alt="" src="" width="400" height="300">
			</div>
			
			<div>
				<form action="<%=request.getContextPath()%>/VendreUnArticle" method="POST">
		
					<label name="labelArticle" for="article">Article : </label>
					<input type="text" name="article"/>
					
					<label name="labelDescription" for="description">Description : </label>
					<input type="text" name="description"/>
					
					<label name="labelCategorie" for="categorie">Catégorie : </label>
					
					<select>
						<option value=""></option>
					</select>
					
					<label name="labelDebutEnchere" for="debutEnchere">Début de l'enchère : </label>
					<input type="date" name="debutEnchere"/>
					
					<label name="labelFinEnchere" for="finEnchere">Fin de l'enchère : </label>
					<input type="date" name="finEnchere"/>
					
					<h3>Retrait</h3><div></div>
					
					<label name="labelRue" for="rue">Rue : </label>
					<input type="text" name="rue"/>
					
					<label name="labelCodePostal" for="codePostal">Code postal : </label>
					<input type="text" name="codePostal"/>
					
					<label name="labelVille" for="ville">Ville : </label>
					<input type="text" name="ville"/>
					
			
					<button class="bouton__enregistrer--couleur" id="btnEnregistrer" name="btnEnregistrer" type="submit">Enregistrer</button>
					<button id="btnAnnuler" name="btnAnnuler">Annuler</button>
			
				</form>
			</div>
		</div>
		

	</div>
</body>
</html>