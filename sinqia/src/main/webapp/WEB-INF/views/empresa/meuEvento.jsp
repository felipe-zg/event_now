<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


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
			<p class="text-light">
				${evento.endereco.rua}, ${evento.endereco.numero} - ${evento.endereco.bairro} <br/>
				${evento.endereco.cidade} - ${evento.endereco.estado} - ${evento.endereco.cep}
			</p>
		</div>
	</div>
</tags:pageTemplate>