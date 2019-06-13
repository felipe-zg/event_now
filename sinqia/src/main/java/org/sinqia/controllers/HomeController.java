package org.sinqia.controllers;

import java.util.List;

import org.sinqia.daos.EventoDao;
import org.sinqia.models.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@Autowired
	private EventoDao eventoDao;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView MEV = new ModelAndView("index");
		List<Evento> eventos = eventoDao.eventosDeHoje();
		MEV.addObject("eventos", eventos);
		return MEV;
	}
}
