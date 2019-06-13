<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Cadastro de evento" bodyClass="bg-dark">
	<div class="container">
		<form action="/sinqia/evento/pesquisa" method="POST">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label class="text-light">cidade</label>
					<input type="text"  name="endereco.cidade" class="form-control" required="true"/>
				</div>
				<div class="form-group col-md-6">
					<label class="text-light">Bairro</label>
					<input type="text"  name="endereco.bairro" class="form-control" />
				</div>
			</div>
			
			<div class="form-row">
				<div class="form-group col-md-6">
					<label class="text-light">Categoria</label>
					<select name="categoria.id" class="form-control">
						<optgroup label="categoria" class="form-control">
							<option value="">selecione</option>
							<c:forEach items="${categorias}" var="categoria" varStatus="status">
								 <option value="${categoria.id}">${categoria.nome}</option>
							</c:forEach>
						</optgroup>
					</select>
					<input type="hidden" name="categoria.nome" value="${categoria.nome }"/>
				</div>
				<div class="form-group col-md-6">
					<label class="text-light">Data</label>
					<input type="text" placeholder="dd/mm/aaaa" name="data" class="form-control" />
				</div>
			</div>
		
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>  
			<button type="submit" class="btn btn-primary">Enviar</button>
		</form>
	</div>
</tags:pageTemplate>