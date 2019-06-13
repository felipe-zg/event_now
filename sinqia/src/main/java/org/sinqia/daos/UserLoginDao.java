package org.sinqia.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sinqia.models.Empresa;
import org.sinqia.models.UserLogin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserLoginDao implements UserDetailsService{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		String jpql = "select u from UserLogin u where u.email = :email";
		List<UserLogin> empresas = manager.createQuery(jpql, UserLogin.class).setParameter("email", email).getResultList();
		if(empresas.isEmpty()) {
			throw new RuntimeException();
		}
		return (UserDetails) empresas.get(0);
	}
	
}
