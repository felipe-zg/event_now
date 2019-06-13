<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<nav class="navbar navbar-expand-lg navbar-light bg-dark">
	<a class="navbar-brand text-light" href="#">Logo</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a class="nav-link text-light" href="/sinqia">Home
					<span class="sr-only">(current)</span>
				</a>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle text-light" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Usuário </a>
				<div class="dropdown-menu bg-dark" aria-labelledby="navbarDropdown">
					<a class="dropdown-item text-light" href="/sinqia/login">Entrar</a> 
					<a class="dropdown-item text-light" href="/sinqia/usuario">Cadastrar</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item text-light" href="#">FAQ</a>
				</div>
			</li>
			
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle text-light" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Empresa </a>
				<div class="dropdown-menu bg-dark" aria-labelledby="navbarDropdown">
					<a class="dropdown-item text-light" href="/sinqia/login">Entrar</a> 
					<a class="dropdown-item text-light" href="/sinqia/empresa/form">Cadastrar</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item text-light" href="#">Sobre</a>
				</div>
			</li>
			
			<li class="nav-item text-light">
				<a class="nav-link text-light" href="/sinqia/evento/pesquisa">Procurar evento</a>
			</li>
			<li class="nav-item text-light">
				<a class="nav-link text-light" href="#">Fale conosco</a>
			</li>
			<security:authorize access="hasRole('ROLE_EMPRESA')">
				<li class="nav-item text-light">
					<a class="nav-link text-light" href="/sinqia/evento/cadastrar">Cadastrar Evento</a>
				</li>
				<li class="nav-item text-light">
					<a class="nav-link text-light" href="/sinqia/empresa/meus-eventos">Meus Eventos</a>
				</li>
				<li class="nav-item text-light">
					<a class="nav-link text-light" href="/sinqia/empresa/meus-enderecos">Meus Endereços</a>
				</li>
				<li class="nav-item text-light">
					<security:authentication property="principal" var="usuario"/>
					<a class="nav-link text-light disabled">${usuario.username}</a>
				</li>
				<li class="nav-item text-light">
					<a class="nav-link text-light" href="/sinqia/logout">Sair</a>
				</li>
			</security:authorize>
			
			<security:authorize access="hasRole('ROLE_USUARIO')">
				<li class="nav-item text-light">
					<a class="nav-link text-light" href="">link 1</a>
				</li>
				<li class="nav-item text-light">
					<a class="nav-link text-light" href="/sinqia/usuario/meus-eventos">Meus Eventos</a>
				</li>
				<li class="nav-item text-light">
					<security:authentication property="principal" var="usuario"/>
					<a class="nav-link text-light disabled">${usuario.username}</a>
				</li>
				<li class="nav-item text-light">
					<a class="nav-link text-light" href="/sinqia/logout">Sair</a>
				</li>
			</security:authorize>
			
		</ul>
		<form class="form-inline my-2 my-lg-0" action="/sinqia/evento/pesquisa/nome" method="post">
			<input class="form-control mr-sm-2" type="text"
				placeholder="nome do evento" aria-label="procurar" name="nome">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>  
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Pesquisar</button>
		</form>
	</div>
</nav>