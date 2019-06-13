<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Cadastro de evento" bodyClass="bg-dark">

	
	
	<div class="container">
		<form action="/sinqia/evento/atualizar?id=${evento.id}" method="POST">
			<div class="form-group">
				<label class="text-light">Nome do evento</label>
				<input type="text" value="${evento.nome}" path="nome" name="nome" class="form-control" required="true"/>
			</div>
			
			<div class="form-group">
				<label class="text-light">Categoria</label>
				<select name="categoria.id" class="form-control">
					<optgroup label="categoria" class="form-control">
						<c:forEach items="${categorias}" var="categoria" varStatus="status">
							 <option value="${categoria.id}">${categoria.nome}</option>
						</c:forEach>
					</optgroup>
				</select>
			</div>
			<input type="hidden" name="categoria.nome" value="${categoria.nome }"/>
			
			<div class="form-group">
				<label class="text-light">Valor da entrada</label>
				<input type="text" value="${evento.valor}"  name="valor" class="form-control" />
			</div>
			<div class="form-group">
				<label class="text-light">Data do evento</label>
				<input type="text" value="${dataEvento}" name="data" class="form-control" />
			</div>
			<div class="form-group">
				<label class="text-light">Horario do evento</label>
				<input type="text" value="${evento.hora}"  name="hora" class="form-control" />
			</div>
		
			<div class="form-check rounded border border-info">
				<input class="form-check-input"  type="radio" name="enderecoId" id="enderecoIdPrincipal" value="${endereco.idEndereco}" checked/>
				<label class="form-check-label text-light" for="enderecoIdPrincipal">
				${endereco.rua}, ${endereco.numero} - ${endereco.bairro}<br/>
				${endereco.cidade} - ${endereco.estado} - ${endereco.cep}
				</label>
			</div>
			
			<c:forEach items="${enderecosAlternativos}" var="enderecoAlternativo" varStatus="status">
				<div class="form-check rounded border border-info" style="margin-top:10px;">
					<input class="form-check-input"  type="radio" name="enderecoId" id="enderecoIdPrincipal${status}" value="${enderecoAlternativo.idEndereco}"/>
					<label class="form-check-label text-light" for="enderecoIdPrincipal${status}">
					${enderecoAlternativo.rua}, ${enderecoAlternativo.numero} - ${enderecoAlternativo.bairro}<br/>
					${enderecoAlternativo.cidade} - ${enderecoAlternativo.estado} - ${enderecoAlternativo.cep}
					</label>
				</div>
			</c:forEach>
	
				
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>  
			<button type="submit" class="btn btn-primary">Atualizar</button>
		</form>
	</div>
</tags:pageTemplate>