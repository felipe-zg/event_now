<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Cadastro de endereço" bodyClass="bg-dark">
		<div class="container">
			<form action="/sinqia/empresa/endereco" method="POST">
				<div class="form-group">
					<label class="text-light">Rua</label>
					<input type="text" path="rua" name="rua" class="form-control" required="true"/>
				</div>
				
				<div class="form-group">
					<label class="text-light">Número</label>
					<input type="number" path="numero" name="numero"class="form-control" />
				</div>
				
				<div class="form-group">
					<label class="text-light">Bairro</label>
					<input type="text" path="bairro" name="bairro" class="form-control" />
				</div>
				<div class="form-group">
					<label class="text-light">CEP</label>
					<input type="text" path="cep" name="cep" class="form-control" />
				</div>
				<div class="form-group">
					<label class="text-light">Cidade</label>
					<input type="text" path="cidade" name="cidade" class="form-control" />
				</div>
				<div class="form-group">
					<label class="text-light">Estado</label>
					<input type="text" path="estado" name="estado" class="form-control" />
				</div>
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
				
				<button type="submit" class="btn btn-primary">Enviar</button>
			</form>
		</div>
</tags:pageTemplate>
	
