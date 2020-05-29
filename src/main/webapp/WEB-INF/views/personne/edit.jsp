<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<h1>edition</h1>
		<c:choose>
			<c:when test="${p.getClass().simpleName == 'Formateur' }">
				<c:url var="action" value="/personne/save/formateur"></c:url>
			</c:when>
			<c:otherwise>
				<c:url var="action" value="/personne/save/stagiaire"></c:url>
			</c:otherwise>
		</c:choose>
		<form:form action="${action}" method="post" modelAttribute="p">
			<form:hidden path="version" />
			<div class="form-group">
				<form:label path="id">id:</form:label>
				<form:input path="id" readonly="true"
					placeholder="generer automatiquement" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="civilite">civilite:</form:label>
				<form:select path="civilite" items="${civilites}" itemLabel="label"
					cssClass="form-control"></form:select>
			</div>
			<div class="form-group">
				<form:label path="prenom">prenom:</form:label>
				<form:input path="prenom" cssClass="form-control" />
				<form:errors path="prenom"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="nom">nom:</form:label>
				<form:input path="nom" cssClass="form-control" />
				<form:errors path="nom"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="adresse.numero">numero:</form:label>
				<form:input type="number" path="adresse.numero"
					cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.rue">rue:</form:label>
				<form:input path="adresse.rue" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.codePostal">code postal:</form:label>
				<form:input path="adresse.codePostal" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.ville">ville:</form:label>
				<form:input path="adresse.ville" cssClass="form-control" />
			</div>
			<c:choose>
				<c:when test="${p.getClass().simpleName == 'Formateur' }">
					<div class="form-group">
						<form:label path="experience">experience:</form:label>
						<form:input path="experience" cssClass="form-control" />
					</div>
					<div class="form-group">
						<div>competences:</div>
						<c:forEach var="c" items="${p.competences}">
							<div>${c.id.matiere.nom}&nbsp;${c.niveau}</div>
						</c:forEach>
						<c:if test="${p.id != null }">
							<a href="${ctx}/personne/competence/add?id=${p.id}"
								class="btn btn-outline-info">nouvelle competences</a>
						</c:if>
					</div>
				</c:when>
				<c:otherwise>
					<div class="form-group">
						<form:label path="formation">formation</form:label>
						<form:select path="formation.id" cssClass="form-control">
							<form:option value="">pas de formation</form:option>
							<form:options items="${formations}" itemValue="id"
								itemLabel="infos" />
						</form:select>
					</div>
				</c:otherwise>
			</c:choose>
			<div>
				<button type="submit" class="btn btn-outline-success">enregistrer</button>
				<a href="${ctx}/personne" class="btn btn-outline-warning">annuler</a>
			</div>
		</form:form>
	</div>
</body>
</html>