package com.example.ativoeoperante.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.ativoeoperante.model.Tipo;
import com.example.ativoeoperante.util.Conexao;

public class TipoDAL {

	public boolean salvar(Tipo o) {
		
		Conexao con = new Conexao();
		boolean flag;
		String sql = "INSERT INTO Tipos VALUES (default,'#1','#2')";
		
		sql = sql.replace("#1", "" + o.getNome());
		sql = sql.replace("#2", "" + o.getNivel());
		
		flag = con.manipular(sql);
		con.desconectar();

		return flag;
	}
	
	public boolean alterar(Tipo o) {
		
		Conexao con = new Conexao();
		boolean flag;
		String sql = "UPDATE Tipos SET tp_nome = '#1', tp_nivel = '#2' WHERE tp_id=" + o.getId();
		
		sql = sql.replace("#1", "" + o.getNome());
		sql = sql.replace("#2", "" + o.getNivel());
		
		flag = con.manipular(sql);
		con.desconectar();

		return flag;
	}
	
	public boolean apagar(int id) {
		
		Conexao con = new Conexao();
		boolean flag;
		String sql = "DELETE FROM Tipos WHERE tp_id=" + id;
		
		flag = con.manipular(sql);
		con.desconectar();

		return flag;
	}
	
	public List<Tipo> get(String filtro) {
		
		List<Tipo> tipos = new ArrayList<>();
		
		String sql = "SELECT * FROM Tipos";
		Conexao con = new Conexao();
		
		if (!filtro.isEmpty())
			sql += " WHERE " + filtro;
		
		ResultSet rs = con.consultar(sql);
		
		try {
			
			while (rs.next())
				tipos.add(new Tipo(rs.getInt("tp_id"),rs.getString("tp_nome"),rs.getString("tp_nivel")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		con.desconectar();
		
		return tipos;
	}
	
	public Tipo get(int id) {
		
		Tipo tipo = null;
		String sql = "SELECT * FROM Tipos WHERE tp_id = " + id;
		Conexao con = new Conexao();
		ResultSet rs = con.consultar(sql);
		
		try {
			if (rs.next()) {
				tipo = new Tipo(rs.getInt("tp_id"),rs.getString("tp_nome"),rs.getString("tp_nivel"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		con.desconectar();
		
		return tipo;
	}
}