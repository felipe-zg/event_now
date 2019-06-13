package org.sinqia.extras;

import org.sinqia.models.UserLogin;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioLogado {
	
	
	public UserLogin getUsuarioLogado() {
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserLogin usuario;
		if (usuarioLogado instanceof UserDetails ) { 
			usuario = (UserLogin)usuarioLogado;
		}else { 
			usuario = (UserLogin)usuarioLogado;
		}
		return usuario;
	}

}
