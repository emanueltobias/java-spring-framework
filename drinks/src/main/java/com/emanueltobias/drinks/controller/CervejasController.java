/**
 * @author Emanuel
 *
 */
package com.emanueltobias.drinks.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emanueltobias.drinks.model.Cerveja;
import com.emanueltobias.drinks.model.Origem;
import com.emanueltobias.drinks.model.Sabor;
import com.emanueltobias.drinks.repository.Estilos;
import com.emanueltobias.drinks.service.CadastroCervejaService;

@Controller
public class CervejasController {
	
	@Autowired
	private Estilos estilos;
	
	@Autowired
	private CadastroCervejaService cadastroCervejaService;

	@RequestMapping("cervejas/novo")
	public ModelAndView novo(Cerveja cerveja) {
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		return mv;
	}

	@RequestMapping(value = "cervejas/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cerveja);
		}
		
		

		// Salvar no banco de dados...
		System.out.println(">>>> Sku: " + cerveja.getSku());
		System.out.println(">>> sabor: " + cerveja.getSabor());
		System.out.println(">>> origem: " + cerveja.getOrigem());
		System.out.println(">>> estilo: " + cerveja.getEstilo());
		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso !");
		return new ModelAndView("redirect:/cervejas/novo");
	}


}