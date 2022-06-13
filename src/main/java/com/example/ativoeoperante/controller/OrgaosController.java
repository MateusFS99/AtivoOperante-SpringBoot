package com.example.ativoeoperante.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.ativoeoperante.DAL.OrgaoDAL;
import com.example.ativoeoperante.model.Orgao;
import com.example.ativoeoperante.util.Mensagem;

@Controller
public class OrgaosController {

	private Orgao novo = new Orgao();
	private Mensagem mensagem = new Mensagem("","");
	private String filtro = "";
	
	@GetMapping("/orgaos")
	public ModelAndView listar() {
		
		String query = "";
		ModelAndView mav = new ModelAndView("listaOrgaos");
		
		mav.addObject("novo",novo);
		if (!filtro.isEmpty())
			query = "UPPER(org_nome) LIKE '%" + filtro.toUpperCase() + "%'";
		mav.addObject("orgaos", new OrgaoDAL().get(query));
		mav.addObject("mensagem", mensagem);
		mav.addObject("filtro", filtro);
		
		return mav;
	}
	
	@GetMapping("/filorgaos")
	public String Filtrar(String filtro) {
		
		this.filtro = filtro;
		return "redirect:/orgaos";
	}
	
	@PostMapping("/saveorgaos")
	public String salvar(Orgao o) {
		
		OrgaoDAL dal = new OrgaoDAL();
		
		if (o.getId() == 0) {
			
			if (!dal.salvar(o)) {
				
				mensagem.setTitulo("Erro");
				mensagem.setDescricao("Erro ao inserir novo órgão, confira os dados");
				mensagem.setCor("red");
			} 
			else
				mensagem = new Mensagem("", "");
		}
		else {   
			
			o.setId(novo.getId());
			if (!dal.alterar(o)) {
				
				mensagem.setTitulo("Erro");
				mensagem.setDescricao("Erro ao inserir novo órgão, confira os dados");
				mensagem.setCor("red");
			} 
			else
				mensagem = new Mensagem("", "");
		}
		
		return "redirect:/orgaos";
	}

	@GetMapping("/excorgaos")
	public String excluir(int id) {
		
		new OrgaoDAL().apagar(id);
		return "redirect:/orgaos";
	}
	
	@GetMapping("/altorgaos")
	public String alterar(int id) {
		
		novo = new OrgaoDAL().get(id);
		return "redirect:/orgaos";
	}
}