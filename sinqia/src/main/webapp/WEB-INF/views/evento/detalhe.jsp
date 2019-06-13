<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>


<tags:pageTemplate titulo="Cadastro de evento" bodyClass="bg-dark">
	<div class="container rounded border border-success">
		<div class="rounded border-bottom  border-primary">
			<p class="text-light">Nome: ${evento.nome}</p>
		</div>
		<div class="rounded border-bottom border-primary">
			<p class="text-light">Data: <fmt:formatDate pattern="dd/MM/yyyy" value="${evento.data.time}"/> </p>
		</div>
		<div class="rounded border-bottom border-primary">
			<p class="text-light">Horario: ${evento.hora}</p>
		</div>
		<div class="rounded border-bottom border-primary">
			<p class="text-light">Valor: R$ ${evento.valor}</p>
		</div>
		<div class="rounded">
			<p class="text-light">Categoria: ${evento.categoria.nome}</p>
		</div>
		<div class="rounded ">
			<div class="row">
				<div class="col-md-6">
					<p class="text-light">
						${evento.endereco.rua}, ${evento.endereco.numero} - ${evento.endereco.bairro} <br/>
						${evento.endereco.cidade} - ${evento.endereco.estado} - ${evento.endereco.cep}
					</p>
				</div>
				<div class="col-md-6">
					<security:authorize access="hasRole('ROLE_USUARIO')">
						<a href="/sinqia/usuario/evento/adiciona?idEvento=${evento.id}">
							<button class="btn btn-primary" style="float:right;">Adicionar a minha lista</button>
						</a>
					</security:authorize>
				</div>
			</div>
		</div>
	</div>
</tags:pageTemplate>