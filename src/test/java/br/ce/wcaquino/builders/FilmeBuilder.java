package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Filme;

public class FilmeBuilder {
	
	private Filme filme;
	
	private FilmeBuilder() {
		
	}
	
	public static FilmeBuilder umFilme() {
		FilmeBuilder builder=new FilmeBuilder();
		builder.filme=new Filme();
		builder.filme.setEstoque(0);
		builder.filme.setNome("Filme 1");
		builder.filme.setPrecoLocacao(5.0);
		return builder;
	}
	
	public FilmeBuilder semEstoque() {
		this.filme.setEstoque(0);
		return this;
	}
	
	public FilmeBuilder comValor(Double valor) {
		this.filme.setPrecoLocacao(valor);
		return this;
	}
	
	
	public Filme agora() {
		return filme;
	}

}
