package org.sinqia.daos;


import org.sinqia.models.Categoria;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class CategoriaDao {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Categoria> listarCategorias(){
		String jpql = "select c from Categoria c";
		System.out.println("dao de categoria listando");
		return manager.createQuery(jpql, Categoria.class).getResultList();
	}
}
