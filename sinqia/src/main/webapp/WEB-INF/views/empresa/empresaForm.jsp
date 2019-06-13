<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Cadastro de empresa" bodyClass="bg-dark">
	<div class="container">
		<form action="/sinqia/empresa" method="POST">
			<div class="form-group">
				<label class="text-light">CNPJ</label>
				<input type="text" path="cnpj" name="cnpj" class="form-control" required="true"/>
			</div>
			
			<div class="form-group">
				<label class="text-light">Nome</label>
				<input type="text" path="nomeFantasia" name="nomeFantasia"class="form-control" />
			</div>
			
			<div class="form-group">
				<label class="text-light">E-mail</label>
				<input type="text" path="email" name="email" class="form-control" />
			</div>
			<div class="form-group">
				<label class="text-light">Senha</label>
				<input type="text" path="senha" name="senha" class="form-control" />
			</div>
			
			<!-- Inputs de endereco já cadastrado pelo cliente -->
			<div class="form-group">
				<input type="hidden" value="${endereco.idEndereco}" path="endereco.idEndereco" name="endereco.idEndereco" class="form-control" />
			</div>
			<div class="form-group">
				<input type="hidden" value="${endereco.rua}" path="endereco.rua" name="endereco.rua" class="form-control" />
			</div>
			<div class="form-group">
				<input type="hidden" value="${endereco.numero}" path="endereco.numero" name="endereco.numero" class="form-control" />
			</div>
			<div class="form-group">
				<input type="hidden" value="${endereco.bairro}" path="endereco.bairro" name="endereco.bairro" class="form-control" />
			</div>
			<div class="form-group">
				<input type="hidden" value="${endereco.cep}" path="endereco.cep" name="endereco.cep" class="form-control" />
			</div>
			<div class="form-group">
				<input type="hidden" value="${endereco.cidade}" path="endereco.cidade" name="endereco.cidade" class="form-control" />
			</div>
			<div class="form-group">
				<input type="hidden" value="${endereco.estado}" path="endereco.estado" name="endereco.estado" class="form-control" />
			</div>
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
			
			<button type="submit" class="btn btn-primary">Enviar</button>
		</form>
	</div>
</tags:pageTemplate>
