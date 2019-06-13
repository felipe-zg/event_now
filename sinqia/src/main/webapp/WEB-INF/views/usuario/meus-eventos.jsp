<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>



<tags:pageTemplate titulo="home" bodyClass="bg-dark">
	<div class="container">
		<div class="bg-info rounded text-center">
			<h3 class="text-dark align-middle">Meus Eventos</h3>
		</div>
		<!-- Eventos confirmados do usuário -->
		<c:forEach items="${eventosConfirmados}" var="evento">
			<div>
				<table class="table  table-sm table-bordered table-hover table-dark">
					<thead>
						<tr class="bg-secondary">
							<th width="20%">Evento</th>
							<th width="70%">${evento.nome}</th>
							<th width="10%" class="bg-success">confirmado</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Categoria</td>
							<td colspan="2">${evento.categoria.nome}</td>
						</tr>
						<tr>
							<td>Valor</td>
							<td colspan="2">R$ ${evento.valor}</td>
						</tr>
						<tr>
							<td>Data</td>
							<td colspan="2"><fmt:formatDate pattern="dd/MM/yyyy" value="${evento.data.time}"/></td>
						</tr>
						<tr>
							<td>Horario</td>
							<td colspan="2">${evento.hora}</td>
						</tr>
						<td colspan="2">
							<a href="/sinqia/usuario/evento/deleta?idEvento=${evento.id}">
								<button class="btn btn-danger">Deletar da minha lista</button>
							</a>
						</td>
					</tbody>
				</table>
			</div>
		</c:forEach>
		
		
		
		
		
		<!-- Eventos da lista do usuário -->
		<c:forEach items="${eventos }" var="evento">
			<div>
				<table class="table  table-sm table-bordered table-hover table-dark">
					<thead>
						<tr class="bg-secondary">
							<th width="20%">Evento</th>
							<th width="80%">${evento.nome}</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Categoria</td>
							<td>${evento.categoria.nome}</td>
						</tr>
						<tr>
							<td>Valor</td>
							<td>R$ ${evento.valor}</td>
						</tr>
						<tr>
							<td>Data</td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${evento.data.time}"/></td>
						</tr>
						<tr>
							<td>Horario</td>
							<td>${evento.hora}</td>
						</tr>
						<td colspan="2">
							<a href="/sinqia/usuario/evento/deleta?idEvento=${evento.id}">
								<button class="btn btn-danger">Deletar da minha lista</button>
							</a>
						</td>
					</tbody>
				</table>
			</div>
		</c:forEach>
		
	</div>
</tags:pageTemplate>









