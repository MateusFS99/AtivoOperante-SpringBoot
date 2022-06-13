package com.example.ativoeoperante.DAL;

import com.example.ativoeoperante.model.Denuncia;
import com.example.ativoeoperante.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DenunciaDAL {
	
	public boolean salvar(Denuncia d) {
		
		Conexao con = new Conexao();
		boolean flag;
		String sql = "INSERT INTO Denuncias VALUES (default,'#1','#2', #3, #4, #5, #6)";
		
		sql = sql.replace("#1", "" + d.getTitulo());
		sql = sql.replace("#2", "" + d.getDescricao());
		sql = sql.replace("#3", "" + d.getUrgencia());
		sql = sql.replace("#4", "" + d.getOrgao().getId());
		sql = sql.replace("#5", "" + d.getTipo().getId());
		sql = sql.replace("#6", "" + d.getLogin().getId());

		flag = con.manipular(sql);
		con.desconectar();

		return flag;
	}
	
	public boolean alterar(int id) {
		
		Conexao con = new Conexao();
		boolean flag;
		String sql = "UPDATE Denuncias SET org_id = NULL, tp_id = NULL, log_id = NULL WHERE den_id=" + id;
		
		flag = con.manipular(sql);
		con.desconectar();

		return flag;
	}
	
	public boolean apagar(int id) {
		
		Conexao con = new Conexao();
		boolean flag;
		String sql = "DELETE FROM Denuncias WHERE den_id=" + id;
		
		flag = con.manipular(sql);
		con.desconectar();

		return flag;
	}
	
	public List<Denuncia> get(String filtro) {
		
		List<Denuncia> denuncias = new ArrayList<>();
		String sql = "SELECT * FROM Denuncias";
		Conexao con = new Conexao();
		
		if (!filtro.isEmpty())
			sql += " WHERE " + filtro;
		
		ResultSet rs = con.consultar(sql);
		
		try {
			
			while (rs.next())
				denuncias.add(new Denuncia(rs.getInt("den_id"),rs.getInt("den_urgencia"), rs.getString("den_titulo"), rs.getString("den_descricao"),
						new OrgaoDAL().get(rs.getInt("org_id")), new TipoDAL().get(rs.getInt("tp_id")), 
						new LoginDAL().get(rs.getInt("log_id"))));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		con.desconectar();
		
		return denuncias;
	}
	
	public Denuncia get(int id) {
		
		Denuncia denuncia = null;
		String sql = "SELECT * FROM Denuncias WHERE den_id = " + id;
		Conexao con = new Conexao();
		ResultSet rs = con.consultar(sql);
		
		try {
			if (rs.next()) {
				denuncia = new Denuncia(rs.getInt("den_id"),rs.getInt("den_urgencia"), rs.getString("den_titulo"), rs.getString("den_descricao"),
						new OrgaoDAL().get(rs.getInt("org_id")), new TipoDAL().get(rs.getInt("tp_id")), 
						new LoginDAL().get(rs.getInt("log_id")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		con.desconectar();
		
		return denuncia;
	}
}