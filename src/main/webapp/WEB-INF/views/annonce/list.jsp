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
<title>annonces</title>
</head>
<body>
	<div class="container">
		<jsp:include page="../langue.jsp"></jsp:include>
		<h1>
			<spring:message code="annonce.list.titre"></spring:message>
		</h1>
		<table class="table">
			<tr>
				<td>annonce.list.id</td>
				<td>annonce.list.dateAnnonce</td>
				<td>annonce.list.note</td>
				<td>annonce.list.nbChiens</td>
				<td>annonce.list.nbHeures</td>
				<td>annonce.list.prixIRTchienIRTheure</td>
				<td>annonce.list.taxes</td>
				<td>annonce.list.prixTotal</td>
				<td>annonce.list.maitre</td>
				<td>annonce.list.promeneur</td>
			</tr>
			<c:forEach var="annonce" items="${annonces}">
				<tr>
					<td>${annonce.id}</td>
					<td>${annonce.dateAnnonce}</td>
					<td>${annonce.note}</td>
					<td>${annonce.tarif.nbChiens}</td>
					<td>${annonce.tarif.nbHeures}</td>
					<td>${annonce.tarif.prixIRTchienIRTheure}</td>
					<td>${annonce.tarif.taxes}</td>
					<td>${annonce.tarif.prixTotal}</td>
					<td>${annonce.maitre}</td>
					<td>${annonce.promeneur}</td>
					<td><a href="${ctx}/annonce/edit?id=${annnonce.id}"
						class="btn btn-outline-info">editer</a></td>
					<td><a href="${ctx}/annonce/delete?id=${p.id}"
						class="btn
						btn-outline-danger">supprimer</a></td>
				</tr>
			</c:forEach>
		</table>
		<div class="btn-group">
			<a href="${ctx}/annonce/add" class="btn btn-outline-info">ajout
				annonce</a>
		</div>



	</div>
</body>
</html>