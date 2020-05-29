<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
	
	<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>

<!-- Permet de chercher un chemin absolue ctx = /SpringMvc -->
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"></c:set>

<link rel="stylesheet" type="text/css"
	href="${ctx}/bootstrap/css/bootstrap.min.css">
<script type="text/javascript"
	src="${ctx}/bootstrap/js/bootstrap.min.js"></script>


</head>
<body>


${ctx}
	
	<!-- Inclus la page langue pour la traduction -->
	

	<div class="container">
		<h1>Liste de chien</h1>
		<table class="table">
			<tr>
				<td>id</td>
				<td>surnom</td>
				<td>Sexe du Chien</td>
				<td>age</td>
				<td>photo</td>
				<td>poids</td>
				<td>race</td>
			</tr>

			<c:forEach var="c" items="${chiens}">

				<tr>
					<td>${c.id}</td>
					<td>${c.surnom}</td>
					<td>${c.sexeChien.sexeChien}</td>
					<td>${c.age}</td>
					<td>${c.photo}</td>
					<td>${c.poids}</td>
					<td>${c.race}</td>
					<td><a href="${ctx}/chien/edit?id=${c.id}}"
						class="btn btn-outline-info">editer</a></td>
					<td><a href="${ctx}/chien/delete?id=${c.id}"
						class="btn btn-outline-danger">supprimer</a></td>
				</tr>

			</c:forEach>
		</table>
	
	<div class="btn-group">
		<a href="${ctx}/chien" class="btn btn-outline-info">Ajouter Chien</a>
	</div>

	</div>
</body>
</html>