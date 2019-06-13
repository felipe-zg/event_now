<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<tags:pageTemplate titulo="Login da empresa" bodyClass="bg-dark">
	<div class="container">
		<form action="/sinqia/login"  method="POST">
			<div class="form-group">
				<label class="text-light">E-mail</label>
				<input type="text"  name="username" class="form-control" required="true"/>
			</div>
			<div class="form-group">
				<label class="text-light">Senha</label>
				<input type="password" name="password"class="form-control" />
			</div>
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
			
			<button type="submit" class="btn btn-primary">Entrar</button>
		</form>
	</div>
</tags:pageTemplate>