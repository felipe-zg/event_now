<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<tags:pageTemplate titulo="Cadastro de evento" bodyClass="bg-dark">
	<div class="container">
		<table class="table table-striped table-bordered table-hover table-dark">
			<thead>
				<tr>
					<th>Evento</th>
					<th>Data</th>
					<th>Detalhes</th>
					<th>Editar</th>
					<th>Excluir</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${eventos }" var="evento">
					<tr>
						<td>${evento.nome}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${evento.data.time}"/></td>
						<td>
							<a href="/sinqia/evento/meu-evento?id=${evento.id}"> 
							  <span class="glyphicon glyphicon-pencil" aria-hidden="true">detalhes</span>
							</a>
						</td>
						<td>
							<a href="/sinqia/evento/editar?id=${evento.id}"> 
							  <span class="glyphicon glyphicon-pencil" aria-hidden="true">editar</span>
							</a>
						</td>
						<td>
							<a href="/sinqia/evento/deletar?id=${evento.id}"> 
							  <span class="glyphicon glyphicon-pencil" aria-hidden="true">excluir</span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</tags:pageTemplate>