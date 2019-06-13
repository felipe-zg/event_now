package org.sinqia.daos;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.sinqia.models.Evento;
import org.sinqia.models.Role;
import org.sinqia.models.UserLogin;
import org.sinqia.models.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class UsuarioDao {
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Usuario usuario) {
		manager.persist(usuario);
	}
	
	public void criaLogin(String email, String senha, String id) {
		String senhaBcrypt = new BCryptPasswordEncoder().encode(senha);
		UserLogin userLogin = new UserLogin(email, senhaBcrypt, id);
		userLogin.adicionaRole(new Role("ROLE_USUARIO"));
		
		manager.persist(userLogin);
	}
	
	public Usuario findById(String id) {
		String jpql = "select u from Usuario u where u.cpf = :id";
		return manager.createQuery(jpql, Usuario.class).setParameter("id", id).getSingleResult();
	}
	
	//FAZ O MERGE DO USUÁRIO QUE ACABA DE ADICIONAR  OU  DELETAR UM  EVENTO NA SUA LISTA DE EVENTOS
	public void mergeEvento(Usuario user) {
		manager.merge(user);
	}

	
	public List<Evento> listaEventos(Usuario user) {
		if(user.getEventos() != null)
			return user.getEventos();
		else
			return null;
	}


}
