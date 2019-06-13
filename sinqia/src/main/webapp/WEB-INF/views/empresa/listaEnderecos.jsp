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
					<th>Endereço</th>
					<th>Cidade</th>
					<th>CEP</th>
					<th>Editar</th>
					<th>Excluir</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${enderecos}" var="endereco">
					<tr>
						<td>${endereco.rua}, ${endereco.numero} - ${endereco.bairro}</td>
						<td>${endereco.cidade}</td>
						<td>${endereco.cep}</td>
						<td>
							<a href="/sinqia/empresa/endereco/editar?id=${endereco.idEndereco}"> 
							  <span class="glyphicon glyphicon-pencil" aria-hidden="true">editar</span>
							</a>
						</td>
						<td>
							<a href="/sinqia/empresa/endereco/deletar?id=${endereco.idEndereco}"> 
							  <span class="glyphicon glyphicon-pencil" aria-hidden="true">excluir</span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/sinqia/empresa/enderecoAlternativo">
			<button class="btn btn-sm btn-outline-info bg-dark">Adicionar endereço</button>
		</a>
	</div>
</tags:pageTemplate>