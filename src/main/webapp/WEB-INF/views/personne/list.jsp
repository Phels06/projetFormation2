<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
<title>personne</title>
</head>
<body>
	<div class="container">
		<jsp:include page="../langue.jsp"></jsp:include>
		<h1>
			<spring:message code="personne.list.titre"></spring:message>
		</h1>
		<table class="table">
			<tr>
				<td>type</td>
				<td>id</td>
				<td>civilite</td>
				<td>prenom</td>
				<td>nom</td>
				<td>adresse</td>
				<td>code postal</td>
				<td>ville</td>
				<td>experience</td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach var="p" items="${personnes}">
				<tr>
					<td><c:choose>
							<c:when
								test="${p.getClass().name == 'formationJpaSpring.entity.Formateur' }">formateur</c:when>
							<c:otherwise>stagiaire</c:otherwise>
						</c:choose></td>
					<td>${p.id}</td>
					<td>${p.civilite.label}</td>
					<td>${p.prenom}</td>
					<td>${p.nom}</td>
					<td>${p.adresse.numero}&nbsp;${p.adresse.rue}</td>
					<td>${p.adresse.codePostal}</td>
					<td>${p.adresse.ville}</td>
					<td><c:if test="${p.getClass().simpleName == 'Formateur'}">
					${p.experience}</c:if></td>
					<td><a href="${ctx}/personne/edit?id=${p.id}&type=${p.getClass().simpleName}"
						class="btn btn-outline-info">editer</a></td>
					<td><a href="${ctx}/personne/delete?id=${p.id}"
						class="btn
						btn-outline-danger">supprimer</a></td>
				</tr>
			</c:forEach>
		</table>
		<div class="btn-group">
			<a href="${ctx}/personne/formateur" class="btn btn-outline-info">ajout
				formateur</a> <a href="${ctx}/personne/stagiaire"
				class="btn btn-outline-info">ajout stagiaire</a>
		</div>



	</div>
</body>
</html>