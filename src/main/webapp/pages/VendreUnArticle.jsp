<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vendre un article</title>
</head>
<header>
	<jsp:include page="Header.jsp" />
</header>

<body>
	<h2>Nouvelle vente</h2>
	
	<div>
		<div>
			<img alt="" src="" width="400" height="300">
		</div>
		
		<div>
			<form action="<%=request.getContextPath()%>/VendreUnArticle" method="POST">
	
				<label name="labelArticle" for="article">Article : </label>
				<input type="text" name="article"/>
				
				<label name="labelDescription" for="description">Description : </label>
				<input type="text" name="description"/>
				
				<label name="labelCategorie" for="categorie">Cat�gorie : </label>
				<select>
					<option value=""></option>
				</select>
				
				<label name="labelDebutEnchere" for="debutEnchere">D�but de l'ench�re : </label>
				<input type="date" name="debutEnchere"/>
				
				<label name="labelFinEnchere" for="finEnchere">Fin de l'ench�re : </label>
				<input type="date" name="finEnchere"/>
				
				Retrait
				
				<label name="labelRue" for="rue">Rue : </label>
				<input type="text" name="rue"/>
				
				<label name="labelCodePostal" for="codePostal">Code postal : </label>
				<input type="text" name="codePostal"/>
				
				<label name="labelVille" for="ville">Ville : </label>
				<input type="text" name="ville"/>
				
		
				<button type="submit" id="btnEnregistrer" name="btnEnregistrer">Enregistrer</button>
				<button id="btnAnnuler" name="btnAnnuler">Annuler</button>
		
			</form>
		</div>
	</div>
	
	
	
	<div>
		<div>
			<img alt="" src="" height="200" width="200">
			<ul>
				<li>PC Gamer pour travailler</li>
				<li>Prix : 210 points</li>
				<li>Fin de L'ench�re : 10/08/2018</li>
				<li>Vendeur : RoiTacos</li>
			</ul>
		</div>
	</div>
</body>
</html>