package org.sinqia.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.sinqia.daos.EmpresaDao;
import org.sinqia.daos.EventoDao;
import org.sinqia.daos.UserLoginDao;
import org.sinqia.daos.enderecoDao;
import org.sinqia.extras.UsuarioLogado;
import org.sinqia.models.Empresa;
import org.sinqia.models.Endereco;
import org.sinqia.models.Evento;
import org.sinqia.models.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class EmpresaController {
	
	@Autowired
	private EmpresaDao empresaDao;
	@Autowired
	private enderecoDao enderecoDao;
	@Autowired
	private EventoDao eventoDao;


	
	/**
	 * Formulário de cadastro de endereço
	 * @param empresa
	 * @return formulário de cadastro de endereço
	 */
	@RequestMapping("/empresa/form")
	public ModelAndView form(Empresa empresa) {
		ModelAndView MEV = new ModelAndView("empresa/enderecoForm");
		return MEV;
	}
	
	/**
	 * Grava o endereço preenchido no formulkário de cadastro de endereço e redireciona para o formulário de cadastro de empresa
	 * @param endereco
	 * @return
	 */
	
	@RequestMapping(value="/empresa/endereco", method=RequestMethod.POST)
	public ModelAndView gravarEndereco(@Valid Endereco endereco) {
		ModelAndView MEV = new ModelAndView("empresa/empresaForm");
		
		enderecoDao.gravar(endereco);
		Endereco end = enderecoDao.find(endereco.getIdEndereco());
		
		MEV.addObject("endereco", end);
		return MEV;
	}
	
	@RequestMapping(value="/empresa/enderecoAlternativo", method=RequestMethod.GET)
	public ModelAndView formEnderecoAlternativo(HttpServletRequest request,Endereco endereco) {
		ModelAndView MEV = new ModelAndView("empresa/enderecoAlternativoForm");
		String referer = request.getHeader("Referer");
		MEV.addObject("referer", referer);
		return MEV;
	}
	
	@RequestMapping(value="/empresa/enderecoAlternativo", method=RequestMethod.POST)
	public String gravaEnderecoAlternativo(Endereco endereco, String referer) {
		enderecoDao.gravar(endereco);
		UserLogin usuario = getUsuarioLogado();
		Empresa empresa = empresaDao.find(usuario.getId());
		empresa.addEnderecoAlternativo(endereco);
		empresaDao.merge(empresa);
		
		return "redirect:"+ referer;
	}	
	
	/**
	 * Grava os dados da empresa preenchidos no formulário de cadastro de empresa
	 * pendura os dados do endereco cadastrado no passo anterior no formulário para inserção no BD
	 * @param empresa
	 * @param result
	 * @param redAttr
	 * @return retorna para uma página de sucesso no cadastro da empresa
	 */
	
	@RequestMapping(value="/empresa", method=RequestMethod.POST)
	public ModelAndView gravar(@Valid Empresa empresa, BindingResult result, RedirectAttributes redAttr) {
		
		if(result.hasErrors()) {
			System.out.println("erro no formulario" + result.getFieldError());
			return form(empresa);
		}
		
		redAttr.addFlashAttribute("mensagemSucesso", "Empresa cadastrada com sucesso");
		
		empresaDao.gravar(empresa);
		empresaDao.criaLogin(empresa.getEmail(), empresa.getSenha(), empresa.getCnpj());

		return  new ModelAndView("redirect:sucesso");
	}
	
	
	/**
	 * Página de sucesso no cadastro da empresa
	 * @return
	 */
	@RequestMapping(value="/sucesso", method=RequestMethod.GET)
	public ModelAndView sucesso() {
		ModelAndView MEV = new ModelAndView("empresa/sucesso");
		return MEV;
	}
	
	@RequestMapping("/empresa/meus-eventos")
	public ModelAndView meusEventos() {
		ModelAndView MEV = new ModelAndView("/empresa/listaEventos");
		UserLogin usuario = getUsuarioLogado();
		List<Evento> eventos = eventoDao.listarPorId(usuario.getId());
		MEV.addObject("eventos", eventos);
		return MEV;
	}
	
	@RequestMapping("/evento/meu-evento")
	public ModelAndView meuEvento(String id) {
		ModelAndView MEV = new ModelAndView("/empresa/meuEvento");
		Integer idEvento = Integer.parseInt(id);
		Evento evento = eventoDao.findEvento(idEvento);
		MEV.addObject("evento", evento);
		return MEV;
	}
	
	@RequestMapping("/empresa/meus-enderecos")
	public ModelAndView meusEnderecos() {
		ModelAndView MEV = new ModelAndView("/empresa/listaEnderecos");
		UserLogin usuario = getUsuarioLogado();
		List<Endereco> enderecos = empresaDao.listarEnderecosPorId(usuario.getId());
		MEV.addObject("enderecos", enderecos);
		return MEV;
	}
	@RequestMapping(value="/empresa/endereco/editar", method=RequestMethod.GET)
	public ModelAndView editarEndereco(String id) {
		ModelAndView MEV = new ModelAndView("/empresa/formAtualizarEndereco");
		Integer idEndereco = Integer.parseInt(id);
		Endereco endereco = enderecoDao.find(idEndereco);
		MEV.addObject("endereco", endereco);
		return MEV;
	}
	
	@RequestMapping(value="/empresa/endereco/editar", method=RequestMethod.POST)
	public String editaEndereco(Endereco endereco, String id) {
		Integer idEndereco = Integer.parseInt(id);
		enderecoDao.atualizar(endereco, idEndereco);
		return "redirect:../meus-enderecos";
	}
	
	@RequestMapping("/empresa/endereco/deletar")
	public String deletarEndereco(String id) {
		Integer idEndereco = Integer.parseInt(id);
		enderecoDao.deletar(idEndereco);
		return "redirect:../meus-enderecos";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "/empresa/login";
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
}
