package com.example.ativoeoperante.util;

public class Mensagem {
	
	String titulo, descricao, cor;

	public Mensagem(String titulo, String descricao) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.cor = "white";
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return (titulo.isEmpty())? "": titulo+": "+descricao;
	}	
}