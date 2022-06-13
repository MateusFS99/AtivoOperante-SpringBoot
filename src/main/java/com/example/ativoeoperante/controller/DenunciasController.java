package com.example.ativoeoperante.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.example.ativoeoperante.DAL.DenunciaDAL;
import com.example.ativoeoperante.model.Denuncia;
import com.example.ativoeoperante.util.Mensagem;

@Controller
public class DenunciasController {

	private Denuncia novo = new Denuncia();
	private Mensagem mensagem = new Mensagem("","");
	private String filtro = "";
	
	@GetMapping("/denuncias")
	public ModelAndView listar() {

		String query = "";
		ModelAndView mav = new ModelAndView("listaDenuncias");
		
		mav.addObject("novo",novo);
		if (!filtro.isEmpty())
			query = "UPPER(den_titulo) LIKE '%" + filtro.toUpperCase() + "%'";
		mav.addObject("denuncias", new DenunciaDAL().get(query));
		mav.addObject("mensagem", mensagem);
		mav.addObject("filtro", filtro);
		
		return mav;
	}
	
	@GetMapping("/fildenuncias")
	public String Filtrar(String filtro) {
		
		this.filtro = filtro;
		return "redirect:/denuncias";
	}
	
	@PostMapping("/savedenuncias")
	public String salvar(Denuncia d) {
		
		DenunciaDAL dal = new DenunciaDAL();
		
		if (d.getId() == 0) {
			
			if (!dal.salvar(d)) {
				
				mensagem.setTitulo("Erro");
				mensagem.setDescricao("Erro ao inserir nova denuncia, confira os dados");
				mensagem.setCor("red");
			} 
			else
				mensagem = new Mensagem("", "");
		}
		
		return "redirect:/denuncias";
	}

	@GetMapping("/excdenuncias")
	public String excluir(int id) {
		
		DenunciaDAL dal = new DenunciaDAL();
		
		dal.alterar(id);
		dal.apagar(id);
		
		return "redirect:/denuncias";
	}
}