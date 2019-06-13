package org.sinqia.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.sinqia.models.Endereco;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public class enderecoDao {
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Endereco endereco) {
		manager.persist(endereco);
		System.out.println("método gravar de endereco dao");
	}
	
	public Endereco find(Integer id) {
		String jpql = "select e from Endereco e where e.idEndereco = :id";
		return manager.createQuery(jpql, Endereco.class).setParameter("id", id).getSingleResult();
	}

	public void atualizar(Endereco endereco, Integer idEndereco) {
		endereco.setIdEndereco(idEndereco);
		manager.merge(endereco);
	}
	
	public void deletar(Integer idEndereco) {
		String jpql = "select e from Endereco e where e.idEndereco = :id";
		Endereco endereco = manager.createQuery(jpql, Endereco.class).setParameter("id", idEndereco).getSingleResult();
		manager.remove(endereco);
	}

}
