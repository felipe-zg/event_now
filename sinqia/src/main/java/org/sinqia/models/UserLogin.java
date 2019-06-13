package org.sinqia.models;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class UserLogin implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String email;
	private String senha;
	@NotNull
	private String id;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();

	public UserLogin() {
		
	}
	public UserLogin(String email, String senhaBcrypt, String id) {
		this.email = email;
		this.senha = senhaBcrypt;
		this.id = id;
	}
	
	public void adicionaRole(Role role) {
		this.roles.add(role);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	public String getId() {
		return id;
	}

	
}
