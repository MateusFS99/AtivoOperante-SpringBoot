package com.example.ativoeoperante.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ativoeoperante.DAL.DenunciaDAL;
import com.example.ativoeoperante.DAL.LoginDAL;
import com.example.ativoeoperante.DAL.OrgaoDAL;
import com.example.ativoeoperante.DAL.TipoDAL;
import com.example.ativoeoperante.model.Denuncia;
import com.example.ativoeoperante.model.Login;
import com.example.ativoeoperante.model.Orgao;
import com.example.ativoeoperante.model.Tipo;

@RestController
public class ServiceController {

	@RequestMapping(value="/validalogin")	
	public ResponseEntity <Object>listarLogins(@RequestParam(value="email") String email, @RequestParam(value="senha") String senha) {
		
		Map<String, Login> mapLogin = new HashMap<>();
		List<Login> todos = new LoginDAL().get("log_email='" + email + "' AND log_senha='" + senha + "'");
		
		for(Login l : todos)
			mapLogin.put("" + l.getId(), l);
		
		return new ResponseEntity<>(mapLogin.values(),HttpStatus.OK);		
	}
	
	@RequestMapping(value="/todosorgaos")	
	public ResponseEntity<Object> listarOrgaos() {
		
		Map<String, Orgao> mapOrgao = new HashMap<>();
		List<Orgao> todos = new OrgaoDAL().get("");
		
		for(Orgao o : todos)
			mapOrgao.put("" + o.getId(), o);
		
		return new ResponseEntity<>(mapOrgao.values(),HttpStatus.OK);		
	}
	
	@RequestMapping(value="/todostipos")	
	public ResponseEntity <Object>listarTipos() {
		
		Map<String, Tipo> mapTipo = new HashMap<>();
		List<Tipo> todos = new TipoDAL().get("");
		
		for(Tipo t : todos)
			mapTipo.put("" + t.getId(), t);
		
		return new ResponseEntity<>(mapTipo.values(),HttpStatus.OK);		
	}
	
	@RequestMapping(value="/receberregistro")
	public void recebeRegistro(@RequestParam(value="cpf") String cpf, @RequestParam(value="email") String email,
								@RequestParam(value="senha") String senha) {
		
		Login l = new Login(cpf,email,senha);
		
		new LoginDAL().salvar(l);
	}
	
	@RequestMapping(value="/receberdenuncia")
	public void recebeDenuncia(@RequestParam(value="titulo") String titulo, @RequestParam(value="descricao") String descricao,
								@RequestParam(value="urgencia") String urgencia, @RequestParam(value="orgao") String orgao, 
								@RequestParam(value="tipo") String tipo, @RequestParam(value="login") String login) {
		
		Denuncia d = new Denuncia(Integer.parseInt(urgencia), titulo, descricao, new OrgaoDAL().get(Integer.parseInt(orgao)), 
				new TipoDAL().get(Integer.parseInt(tipo)), new LoginDAL().get(Integer.parseInt(login)));
		
		new DenunciaDAL().salvar(d);
	}
}