package org.sinqia.controllers;


import java.text.ParseException;

import java.util.List;

import org.sinqia.daos.CategoriaDao;
import org.sinqia.daos.EmpresaDao;
import org.sinqia.daos.EventoDao;
import org.sinqia.models.Categoria;
import org.sinqia.models.Empresa;
import org.sinqia.models.Endereco;
import org.sinqia.models.Evento;
import org.sinqia.models.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/evento")
public class EventoController {
	@Autowired
	private EventoDao eventoDao;
	@Autowired
	private EmpresaDao empresaDao;
	@Autowired
	private CategoriaDao categoriaDao;

	
	@RequestMapping(value="/cadastrar", method=RequestMethod.GET)
	public ModelAndView formEvento(Evento evento) {
		ModelAndView MEV = new ModelAndView("evento/form");
		List<Categoria> categorias = categoriaDao.listarCategorias();
		
		
		UserLogin usuario = getUsuarioLogado();
		Empresa empresa = empresaDao.find(usuario.getId());
		Endereco endereco = empresaDao.findEnderecoPrincipal(usuario.getId());
		List<Endereco> enderecosalternativos = empresa.getEnderecosAlternativos();
		MEV.addObject("endereco", endereco);
		MEV.addObject("enderecosAlternativos", enderecosalternativos);
		
		MEV.addObject("categorias", categorias);
		return MEV;
	}

	
	@RequestMapping(value="/cadastrar", method=RequestMethod.POST)
	public String gravar(Evento evento, String enderecoId) {
		Integer id = Integer.parseInt(enderecoId);
		UserLogin usuario = getUsuarioLogado();
		Empresa empresa = empresaDao.find(usuario.getId());
		Endereco endereco = empresaDao.findEndereco(id);
		evento.setEndereco(endereco);
		evento.setEmpresa(empresa);
		eventoDao.gravar(evento);
		return "redirect:../empresa/meus-eventos";
	}
	
	@RequestMapping("/deletar")
	public String deletaEvento(String id) {
		Integer idEvento = Integer.parseInt(id);
		eventoDao.deletar(idEvento);
		return "redirect:../empresa/meus-eventos";
	}
	
	@RequestMapping("/editar")
	public ModelAndView editar(String id) {
		ModelAndView MEV = new ModelAndView("/evento/formAtualizacao");
		Integer idEvento = Integer.parseInt(id);
		Evento evento = eventoDao.findById(idEvento);
		String dataEvento = evento.dataToString();
		List<Categoria> categorias = categoriaDao.listarCategorias();
		
		UserLogin usuario = getUsuarioLogado();
		Empresa empresa = empresaDao.find(usuario.getId());
		Endereco endereco = empresaDao.findEnderecoPrincipal(usuario.getId());
		List<Endereco> enderecosalternativos = empresa.getEnderecosAlternativos();
	
		MEV.addObject("categorias", categorias);
		MEV.addObject("evento", evento);
		MEV.addObject("endereco", endereco);
		MEV.addObject("enderecosAlternativos", enderecosalternativos);
		MEV.addObject("dataEvento", dataEvento);
		
		return MEV;
	}
	
	@RequestMapping(value="/atualizar", method=RequestMethod.POST)
	public String atualizar(Evento evento, String id, String enderecoId) {
		Integer idEvento = Integer.parseInt(id);
		Integer idEndereco = Integer.parseInt(enderecoId);
		UserLogin usuario = getUsuarioLogado();
		Empresa empresa = empresaDao.find(usuario.getId());
		Endereco endereco = empresaDao.findEndereco(idEndereco);
		evento.setEndereco(endereco);
		evento.setEmpresa(empresa);
		eventoDao.atualizar(evento, idEvento);
		
		return "redirect:../empresa/meus-eventos";
	}
	
	@RequestMapping("/detalhe")
	public ModelAndView detalhe(String idEvento) {
		ModelAndView MEV = new ModelAndView("/evento/detalhe");
		Integer id = Integer.parseInt(idEvento);
		Evento evento = eventoDao.findEvento(id);
		MEV.addObject("evento", evento);
		return MEV;
	}
	
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
	
	@RequestMapping(value="/pesquisa/nome", method=RequestMethod.POST)
	public ModelAndView eventoPorNome(String nome) {
		ModelAndView MEV = new ModelAndView("/evento/eventosPorNome");
		List<Evento> eventos = eventoDao.findByName(nome);
		MEV.addObject("eventos", eventos);
		return MEV;
	}
	
	@RequestMapping(value="/pesquisa", method=RequestMethod.GET)
	public ModelAndView formPesquisaEvento() {
		ModelAndView MEV = new ModelAndView("evento/procurar");
		List<Categoria> categorias = categoriaDao.listarCategorias();
		MEV.addObject("categorias", categorias);
		return MEV;
	}
	@RequestMapping(value="/pesquisa", method=RequestMethod.POST)
	public ModelAndView pesquisaEvento(Evento evento) throws ParseException {
		ModelAndView MEV = new ModelAndView("/evento/searchEventos");
		List<Evento> eventos = eventoDao.searchEvento(evento);
		
		MEV.addObject("eventos", eventos);
		return MEV;
	}

	
}
