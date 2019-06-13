<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>



<tags:pageTemplate titulo="home" bodyClass="bg-dark">
	<div class="container">
		<div class="bg-primary rounded text-center">
			<h3 class="align-middle">eventos fintrados por por nome</h3>
		</div>
		<c:forEach items="${eventos}" var="evento">
			<div class="bg-secondary rounded" style="margin-top:20px;">
				<h2>${evento.nome}</h2>
				<p>${evento.categoria.nome}</p>
				<p>R$ ${evento.valor}</p>
				<p><fmt:formatDate pattern="dd/MM/yyyy" value="${evento.data.time}"/></p>
				<p>${evento.hora}</p>
				<div style="display:inline-block;">
					<a href="/sinqia/evento/detalhe?idEvento=${evento.id}">
						<button class="btn btn-sm btn-outline-info bg-dark">Detalhe</button>
					</a>
					<security:authorize access="hasRole('ROLE_USUARIO')">
						<a href="/sinqia/usuario/evento/adiciona?idEvento=${evento.id}">
							<button class="btn btn-sm btn-outline-primary bg-dark">Adicionar a minha lista</button>
						</a>
						<a href="">
							<button class="btn btn-sm btn-outline-success bg-dark">Confirmar presença</button>
						</a>
						<a href="">
							<button class="btn btn-sm btn-outline-warning bg-dark">Talvez</button>
						</a>
					</security:authorize>
				</div>
			</div>
		</c:forEach>
	</div>
	
</tags:pageTemplate>


