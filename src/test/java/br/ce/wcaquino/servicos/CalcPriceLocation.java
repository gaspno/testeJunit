package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

@RunWith(Parameterized.class)
public class CalcPriceLocation {
	
	@Parameter()
	public List<Filme> filmes;
	
	@Parameter(value = 1)
	public Double valorLocacao;
	
	@Parameter(value =2)
	public String testeDescricao;
	
	
	private LocacaoService service;
	
	
	private Usuario usuario;
	private static Filme filme1=new Filme("Filme 1",1,4.0);
	private static Filme filme2=new Filme("Filme 2",1,4.0);
	private static Filme filme3=new Filme("Filme 3",1,4.0);
	private static Filme filme4=new Filme("Filme 4",1,4.0);
	private static Filme filme5=new Filme("Filme 5",1,5.0);
	private static Filme filme6=new Filme("Filme 5",1,5.0);
	private static Filme filme7=new Filme("James bond",1,100.0);
	
	@Parameters(name = "Teste {index}={2}")
	public static Collection<Object[]> getPàrameters(){
		
		return Arrays.asList(new Object[][]{
			
				{Arrays.asList(filme1,filme2,filme3),11.00,"3 filmes 25%"},
				{Arrays.asList(filme1,filme2,filme3,filme4),13.00,"4 filmes 50%"},
				{Arrays.asList(filme1,filme2,filme3,filme4,filme5),14.25,"5 ou mais filmes 90%"},
				{Arrays.asList(filme1,filme2,filme3,filme4,filme5,filme6),14.75,"5 ou mais filmes 90%"},
				{Arrays.asList(filme1,filme2,filme3,filme4,filme5,filme6,filme7),24.75,"5 filmes 90%"}
				
		});
		
	}
	
	@Before
	public void init() {
	
		usuario=new Usuario("Usuario 1");
		service = new LocacaoService();
	}
	@Test
	public void testeDesconto() throws FilmeSemEstoqueException, LocadoraException {
		
	
		
		Locacao locacao= service.alugarFilme(usuario, filmes);
		
		Assert.assertThat(locacao.getValor(),is( valorLocacao));
		
	}

}
