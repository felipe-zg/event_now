package org.sinqia.controllers;

import java.util.List;

import org.sinqia.daos.EventoDao;
import org.sinqia.daos.UsuarioDao;
import org.sinqia.models.Evento;
import org.sinqia.models.UserLogin;
import org.sinqia.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private EventoDao eventoDao;
	
	/**
	 * Redireciona o usuario para o formulário de cadastro de novo usuário
	 * @return
	 */
	@RequestMapping(value="/usuario", method=RequestMethod.GET)
	public String formCadastro() {
		return "/usuario/usuarioForm";
	}
	/**
	 * CADASTRA UM NOVO USUÁRIO E CRIA O LOGIN COM PERMISSÃO DE USUÁRIO
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value="/usuario", method=RequestMethod.POST)
	public ModelAndView gravar(Usuario usuario) {
		usuarioDao.gravar(usuario);
		usuarioDao.criaLogin(usuario.getEmail(), usuario.getSenha(), usuario.getCpf());
		
		return new ModelAndView("redirect:/");
	}
	
	/**
	 * ADICIONA UM NOVO EVENTO A LISTA DE EVENTOS DO USUÁRIO E O REDIRECIONA PARA A JSP DE LISTA DE EVENTOS
	 * @param idEvento
	 * @return
	 */
	@RequestMapping(value="/usuario/evento/adiciona", method=RequestMethod.GET)
	public ModelAndView adicionaEvento(Integer idEvento) {
		UserLogin usuario = getUsuarioLogado();
		Evento evento = eventoDao.findById(idEvento);
		Usuario user = usuarioDao.findById(usuario.getId());
		
		if(user.addEvento(evento))
			usuarioDao.mergeEvento(user);
		
		return new ModelAndView("redirect:../meus-eventos");
	}
	/**
	 * REMOVE UM NOVO EVENTO A LISTA DE EVENTOS DO USUÁRIO E O REDIRECIONA PARA A JSP DE LISTA DE EVENTOS
	 * @param idEvento
	 * @return
	 */
	@RequestMapping(value="/usuario/evento/deleta", method=RequestMethod.GET)
	public ModelAndView deletaEvento(Integer idEvento) {
		UserLogin usuario = getUsuarioLogado();
		Evento evento = eventoDao.findById(idEvento);
		Usuario user = usuarioDao.findById(usuario.getId());
		user.removeEvento(evento);
		usuarioDao.mergeEvento(user);
		return new ModelAndView("redirect:../meus-eventos");
	}
	
	/**
	 * REDIRECIONA PARA A LISTA DE EVENTOS DO USUÁRIO
	 * @return
	 */
	@RequestMapping("/usuario/meus-eventos")
	public ModelAndView listaEventos() {
		ModelAndView MEV = new ModelAndView("/usuario/meus-eventos");
		UserLogin usuario = getUsuarioLogado();
		Usuario user = usuarioDao.findById(usuario.getId());
		
		List<Evento> eventos = usuarioDao.listaEventos(user);
		MEV.addObject("eventos", eventos);

		return MEV;
	}

	
	//MÉTODO QUE RECUPERA O USUÁRIO QUE ESTÁ LOGADO NO MOMENTO
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
