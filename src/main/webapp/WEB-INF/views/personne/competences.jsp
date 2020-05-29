<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"></c:set>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx}/bootstrap/css/bootstrap.min.css">
<script type="text/javascript"
	src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div>compentences de ${formateur.prenom}&nbsp;${formateur.nom}</div>
		<ul>
			<c:forEach var="c" items="${competence.id.formateur.competences}">
				<li>${c.id.matiere.nom}&nbsp;${c.niveau}</li>
			</c:forEach>
		</ul>
		<form:form action="${ctx}/personne/competence/add" method="post"
			modelAttribute="competence">
			<form:hidden path="id.formateur.id" />
			<form:label path="id.matiere">matiere:</form:label>
			<form:select path="id.matiere.id" items="${matieres}" itemValue="id"
				itemLabel="nom"></form:select>
			<form:input path="niveau" />
			<button type="submit" class="btn btn-outline-success">ajouter</button>
			<a
				href="${ctx}/personne/edit?id=${competence.id.formateur.id}&type=Formateur"
				class="btn btn-outline-warning">retour</a>
		</form:form>
	</div>
</body>
</html>