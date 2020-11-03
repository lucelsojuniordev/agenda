package com.agenda.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.agenda.modelo.Agendamento;
import com.agenda.repositorio.AgendamentoRepositorio;

@Controller
public class AgendamentoControle {

	@Autowired
	AgendamentoRepositorio repositporio;

	@RequestMapping("/")
	public ModelAndView inicio() {

		ModelAndView mv = new ModelAndView("home.html");
		Iterable<Agendamento> eventos = repositporio.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String salvar() {

		return "cadastrar.html";
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String salvar(Agendamento agenamento) {

		repositporio.save(agenamento);
		return "redirect:/";
	}

	@RequestMapping("/deletar")
	public String deletar(Long codigo) {

		Agendamento agendamento = repositporio.findByCodigo(codigo);
		repositporio.delete(agendamento);
		return "redirect:/";
	}

	@RequestMapping("/buscarData")
	public ModelAndView buscarData() {
		ModelAndView mv = new ModelAndView("buscar.html");
		return mv;
	}
	
	@RequestMapping(value = "/buscarData" , method =RequestMethod.POST )
	public ModelAndView buscarData(String data) {

		ModelAndView mv = new ModelAndView("buscar.html");
		Iterable<Agendamento> eventos = repositporio.findAllByData(data);
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	@RequestMapping("/editar")
	public ModelAndView editar(Long codigo) {
		ModelAndView mv = new ModelAndView("editar.html");
		Agendamento evento = repositporio.findByCodigo(codigo);
		mv.addObject("evento", evento);
		return mv;
	}
	
	@RequestMapping(value = "/editarEvento", method = RequestMethod.POST)
	public String editarEvento(Agendamento evento) {
	
		repositporio.save(evento);
		return "redirect:/";
		
	}
	


}
