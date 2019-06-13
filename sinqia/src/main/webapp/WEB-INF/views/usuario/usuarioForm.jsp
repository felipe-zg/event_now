<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Cadastro de empresa" bodyClass="bg-dark">
	<div class="container">
		<form action="/sinqia/usuario" method="POST">
			<div class="form-group">
				<label class="text-light">CPF</label>
				<input type="text" name="cpf" class="form-control" required="true"/>
			</div>
			
			<div class="form-group">
				<label class="text-light">Nome</label>
				<input type="text"  name="nome"class="form-control" />
			</div>
			
			<div class="form-group">
				<label class="text-light">E-mail</label>
				<input type="text"  name="email" class="form-control" />
			</div>
			<div class="form-group">
				<label class="text-light">Senha</label>
				<input type="text" name="senha" class="form-control" />
			</div>
			
		
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</form>
	</div>
</tags:pageTemplate>
