package com.example.ativoeoperante.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.ativoeoperante.model.Login;
import com.example.ativoeoperante.util.Conexao;

public class LoginDAL {

	public boolean salvar(Login l) {
		
		Conexao con = new Conexao();
		boolean flag;
		String sql = "INSERT INTO Login VALUES (default,'#1', '#2', '#3')";
		
		sql = sql.replace("#1", "" + l.getCpf());
		sql = sql.replace("#2", "" + l.getEmail());
		sql = sql.replace("#3", "" + l.getSenha());
		
		flag = con.manipular(sql);
		con.desconectar();

		return flag;
	}
	
	public boolean alterar(Login l) {
		
		Conexao con = new Conexao();
		boolean flag;
		String sql = "UPDATE Login SET log_cpf = '#1', log_email = '#2', log_senha = '#3' WHERE log_id=" + l.getId();
		
		sql = sql.replace("#1", "" + l.getCpf());
		sql = sql.replace("#2", "" + l.getEmail());
		sql = sql.replace("#3", "" + l.getSenha());
		
		flag = con.manipular(sql);
		con.desconectar();

		return flag;
	}
	
	public boolean apagar(int id) {
		
		Conexao con = new Conexao();
		boolean flag;
		String sql = "DELETE FROM Login WHERE log_id=" + id;
		
		flag = con.manipular(sql);
		con.desconectar();

		return flag;
	}
	
	public List<Login> get(String filtro) {
		
		List<Login> logins = new ArrayList<>();
		
		String sql = "SELECT * FROM Login";
		Conexao con = new Conexao();
		
		if (!filtro.isEmpty())
			sql += " WHERE " + filtro;
		
		ResultSet rs = con.consultar(sql);
		
		try {
			
			while (rs.next())
				logins.add(new Login(rs.getInt("log_id"),rs.getString("log_cpf"),rs.getString("log_email"),rs.getString("log_senha")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		con.desconectar();
		
		return logins;
	}
	
	public Login get(int id) {
		
		Login login = null;
		String sql = "SELECT * FROM Login WHERE log_id = " + id;
		Conexao con = new Conexao();
		ResultSet rs = con.consultar(sql);
		
		try {
			if (rs.next()) {
				login = new Login(rs.getInt("log_id"),rs.getString("log_cpf"),rs.getString("log_email"),rs.getString("log_senha"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		con.desconectar();
		
		return login;
	}
}