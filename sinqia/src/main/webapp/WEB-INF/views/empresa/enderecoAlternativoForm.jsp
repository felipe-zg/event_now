<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Cadastro de endereço" bodyClass="bg-dark">
		<div class="container">
			<div class="bg-primary rounded text-center">
				<h3 class="align-middle">Cadastro de endereço alternativo</h3>
			</div>
			<form action="/sinqia/empresa/enderecoAlternativo" method="POST">
				<div class="form-group">
					<label class="text-light">Rua</label>
					<input type="text" name="rua" class="form-control" required="true"/>
				</div>
				
				<div class="form-group">
					<label class="text-light">Número</label>
					<input type="number" name="numero"class="form-control" />
				</div>
				
				<div class="form-group">
					<label class="text-light">Bairro</label>
					<input type="text" name="bairro" class="form-control" />
				</div>
				<div class="form-group">
					<label class="text-light">CEP</label>
					<input type="text"  name="cep" class="form-control" />
				</div>
				<div class="form-group">
					<label class="text-light">Cidade</label>
					<input type="text"  name="cidade" class="form-control" />
				</div>
				<div class="form-group">
					<label class="text-light">Estado</label>
					<input type="text"  name="estado" class="form-control" />
				</div>
				<input type="hidden" name="referer" value="${referer}"/>
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
				
				<button type="submit" class="btn btn-primary">Enviar</button>
			</form>
		</div>
</tags:pageTemplate>
	
