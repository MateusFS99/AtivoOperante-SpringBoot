package com.example.ativoeoperante.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.ativoeoperante.DAL.TipoDAL;
import com.example.ativoeoperante.model.Tipo;
import com.example.ativoeoperante.util.Mensagem;

@Controller
public class TiposController {

	private Tipo novo = new Tipo();
	private Mensagem mensagem = new Mensagem("","");
	private String filtro = "";
	
	@GetMapping("/tipos")
	public ModelAndView listar() {
		
		String query = "";
		ModelAndView mav = new ModelAndView("listaTipos");
		
		mav.addObject("novo",novo);
		if (!filtro.isEmpty())
			query = "UPPER(tp_nome) LIKE '%" + filtro.toUpperCase() + "%'";
		mav.addObject("tipos", new TipoDAL().get(query));
		mav.addObject("mensagem", mensagem);
		mav.addObject("filtro", filtro);
		
		return mav;
	}
	
	@GetMapping("/filtipos")
	public String Filtrar(String filtro) {
		
		this.filtro = filtro;
		return "redirect:/tipos";
	}
	
	@PostMapping("/savetipos")
	public String salvar(Tipo t) {
		
		TipoDAL dal = new TipoDAL();
		
		if (t.getId() == 0) {
			
			if (!dal.salvar(t)) {
				
				mensagem.setTitulo("Erro");
				mensagem.setDescricao("Erro ao inserir novo tipo de problema, confira os dados");
				mensagem.setCor("red");
			} 
			else
				mensagem = new Mensagem("", "");
		}
		else {   
			
			t.setId(novo.getId());
			if (!dal.alterar(t)) {
				
				mensagem.setTitulo("Erro");
				mensagem.setDescricao("Erro ao inserir novo tipo de problema, confira os dados");
				mensagem.setCor("red");
			} 
			else
				mensagem = new Mensagem("", "");
		}
		
		return "redirect:/tipos";
	}

	@GetMapping("/exctipos")
	public String excluir(int id) {
		
		new TipoDAL().apagar(id);
		return "redirect:/tipos";
	}
	
	@GetMapping("/alttipos")
	public String alterar(int id) {
		
		novo = new TipoDAL().get(id);
		return "redirect:/orgaos";
	}
}