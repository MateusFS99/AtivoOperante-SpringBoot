package com.example.ativoeoperante.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.ativoeoperante.model.Orgao;
import com.example.ativoeoperante.util.Conexao;

public class OrgaoDAL {

	public boolean salvar(Orgao o) {
		
		Conexao con = new Conexao();
		boolean flag;
		String sql = "INSERT INTO Orgaos VALUES (default,'#1')";
		
		sql = sql.replace("#1", "" + o.getNome());
		
		flag = con.manipular(sql);
		con.desconectar();

		return flag;
	}
	
	public boolean alterar(Orgao o) {
		
		Conexao con = new Conexao();
		boolean flag;
		String sql = "UPDATE Orgaos SET org_nome = '#1' WHERE org_id=" + o.getId();
		
		sql = sql.replace("#1", "" + o.getNome());
		
		flag = con.manipular(sql);
		con.desconectar();

		return flag;
	}
	
	public boolean apagar(int id) {
		
		Conexao con = new Conexao();
		boolean flag;
		String sql = "DELETE FROM Orgaos WHERE org_id=" + id;
		
		flag = con.manipular(sql);
		con.desconectar();

		return flag;
	}
	
	public List<Orgao> get(String filtro) {
		
		List<Orgao> orgaos = new ArrayList<>();
		
		String sql = "SELECT * FROM Orgaos";
		Conexao con = new Conexao();
		
		if (!filtro.isEmpty())
			sql += " WHERE " + filtro;
		
		ResultSet rs = con.consultar(sql);
		
		try {
			
			while (rs.next())
				orgaos.add(new Orgao(rs.getInt("org_id"),rs.getString("org_nome")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		con.desconectar();
		
		return orgaos;
	}
	
	public Orgao get(int id) {
		
		Orgao orgao = null;
		String sql = "SELECT * FROM Orgaos WHERE org_id = " + id;
		Conexao con = new Conexao();
		ResultSet rs = con.consultar(sql);
		
		try {
			if (rs.next()) {
				orgao = new Orgao(rs.getInt("org_id"),rs.getString("org_nome"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		con.desconectar();
		
		return orgao;
	}
}