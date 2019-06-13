package org.sinqia.daos;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.sinqia.models.Empresa;
import org.sinqia.models.Endereco;
import org.sinqia.models.Evento;
import org.sinqia.models.Role;
import org.sinqia.models.UserLogin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class EmpresaDao  {
	
	@PersistenceContext
	private EntityManager manager;

	public void gravar(Empresa empresa) {
		manager.persist(empresa);
	}
	
	public List<Evento> listarEventos(){
		String jpql = "select e from Evento e";
		return manager.createQuery(jpql, Evento.class).getResultList();
	}
	
	public Empresa find(String cnpj) {
		String jpql = "select e from Empresa e where e.cnpj = :cnpj";
		return (Empresa) manager.createQuery(jpql, Empresa.class).setParameter("cnpj", cnpj).getSingleResult();
	}
	
	public Endereco findEnderecoPrincipal(String cnpj) {
		String jpql = "select e from Empresa e where e.cnpj = :cnpj";
		String jpql2 = "select e from Endereco e where e.idEndereco = :emp_end_id";
		Empresa empresa = manager.createQuery(jpql, Empresa.class).setParameter("cnpj", cnpj).getSingleResult();
		Endereco endereco = (Endereco) manager.createQuery(jpql2, Endereco.class)
				.setParameter("emp_end_id", empresa.getEndereco().getIdEndereco()).getSingleResult();
		
		return endereco;
	}


	public Empresa findEmpresa(String email, String senha) {
		String jpql = "select e from Empresa e where e.email = :email and e.senha = :senha";
		return manager.createQuery(jpql, Empresa.class)
				.setParameter("email", email)
				.setParameter("senha", senha)
				.getSingleResult();
	}
	
	public void criaLogin(String email, String senha, String id) {
		String senhaBcrypt = new BCryptPasswordEncoder().encode(senha);
		UserLogin userLogin = new UserLogin(email, senhaBcrypt, id);
		userLogin.adicionaRole(new Role("ROLE_EMPRESA"));
		
		manager.persist(userLogin);
	}

	public void merge(Empresa empresa) {
		manager.merge(empresa);
	}

	public Endereco findEndereco(Integer enderecoId) {
		String jpql = "select e from Endereco e where e.idEndereco = :id";
		return manager.createQuery(jpql, Endereco.class).setParameter("id", enderecoId).getSingleResult();
	}

	public List<Endereco> listarEnderecosPorId(String id) {
		Empresa empresa = manager.find(Empresa.class, id);
		List<Endereco> enderecos = new ArrayList<Endereco>();
		enderecos.add(empresa.getEndereco());
		for (Endereco endereco : empresa.getEnderecosAlternativos()) {
			enderecos.add(endereco);
		}
		return enderecos;
	}
	

	
}
