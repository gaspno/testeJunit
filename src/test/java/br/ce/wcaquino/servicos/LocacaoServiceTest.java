package br.ce.wcaquino.servicos;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import static br.ce.wcaquino.matchers.MatchersProject.dayMonday;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import br.ce.wcaquino.builders.FilmeBuilder;
import br.ce.wcaquino.builders.UsuarioBuilder;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

import br.ce.wcaquino.matchers.MatchersProject;
import br.ce.wcaquino.utils.DataUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocacaoServiceTest {
	
	private LocacaoService service;
	private Usuario usuario;
	private List<Filme> filme;
	
	@Rule
	public ErrorCollector errorCollector=new ErrorCollector();

	@Rule
	public ExpectedException exc=ExpectedException.none();
	
	@Before
	public void setup() {
		service = new LocacaoService();
		usuario = UsuarioBuilder.umUsuario().agora();
		filme = Arrays.asList(FilmeBuilder.umFilme().agora());
	}
	
	@Test
	public void teste1() throws Exception {
		//cenario
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		filme = Arrays.asList(new Filme("Filme 1",2, 5.0));
		
		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);
			//verificacao
			errorCollector.checkThat( locacao.getValor(),is((equalTo(5.0))));
			errorCollector.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),is(true));
			errorCollector.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),is(true));
			errorCollector.checkThat(locacao.getDataRetorno(),MatchersProject.nextDay());
			
		
	}
	
	
	
	@Test(expected = FilmeSemEstoqueException.class)
	public void teste2() throws Exception {
		
			
		//acao
		service.alugarFilme(usuario, filme);
	}
	
	
	@Test
	public void teste3() throws FilmeSemEstoqueException {
		
			
		//acao
		try {
			service.alugarFilme(usuario, null);
			Assert.fail();
		}catch (LocadoraException e) {
			// TODO Auto-generated catch block
			Assert.assertThat(e.getMessage(), is("Filme vazio"));
		}
	}
	
	
	@Test
	public void teste4() throws FilmeSemEstoqueException {
				
		//acao
		try {
			 service.alugarFilme(null, filme);
			Assert.fail();
		}catch (LocadoraException e) {
			// TODO Auto-generated catch block
			Assert.assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}
	
	@Test
	public void teste25desconto() throws FilmeSemEstoqueException, LocadoraException {
		
		filme=Arrays.asList(new Filme("Filme 1",2,4.0),new Filme("Filme 2",2,4.0),new Filme("Filme 3",2,4.0));
		
		Locacao locacao= service.alugarFilme(usuario, filme);
		
		Assert.assertThat(locacao.getValor(),is( 11.0));
		
	}
	@Test
	public void teste50desconto() throws FilmeSemEstoqueException, LocadoraException {
		
		filme=Arrays.asList(new Filme("Filme 1",2,4.0),new Filme("Filme 2",2,4.0),new Filme("Filme 3",2,4.0),new Filme("Filme 4",2,4.0));
		
		Locacao locacao= service.alugarFilme(usuario, filme);
		
		Assert.assertThat(locacao.getValor(),is( 13.0));
		
	}
	@Test
	public void teste75desconto() throws FilmeSemEstoqueException, LocadoraException {
		
		filme=Arrays.asList(new Filme("Filme 1",2,4.0),new Filme("Filme 2",2,4.0),
				new Filme("Filme 3",2,4.0),new Filme("Filme 4",2,4.0),
				new Filme("Filme 5",2,5.0));
		
		Locacao locacao= service.alugarFilme(usuario, filme);
		
		Assert.assertThat(locacao.getValor(),is( 14.25));
		
	}
	@Test
	public void teste90desconto() throws FilmeSemEstoqueException, LocadoraException {
		
		filme=new ArrayList<>();
		
		filme.addAll(Arrays.asList(new Filme("Filme 1",2,4.0),new Filme("Filme 2",2,4.0),
				new Filme("Filme 3",2,4.0),new Filme("Filme 4",2,4.0),
				new Filme("Filme 5",2,5.0),new Filme("Filme 6",2,5.0)));
		
		Locacao locacao= service.alugarFilme(usuario, filme);
		Filme f=new Filme("James Bond",1,100.00);
		filme.add(f);
		Locacao locacao2=service.alugarFilme(usuario, filme);
		Assert.assertThat(locacao.getValor(),is( 14.75));
		Assert.assertThat(locacao2.getValor(),is( 24.75));
	}
	@Test
	
	public void testenotsunsayDevolution() throws FilmeSemEstoqueException, LocadoraException {
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		filme = Arrays.asList(new Filme("Filme 1",2, 5.0));
		Locacao locacao= service.alugarFilme(usuario, filme);
		
		
	
	//	Assert.assertThat(DataUtils.verificarDiaSemana(locacao.getDataRetorno(),Calendar.MONDAY),is(true));
	//	Assert.assertThat(locacao.getDataRetorno(), new DayWeekMatcher(Calendar.MONDAY));
	//	Assert.assertThat(locacao.getDataRetorno(),dayWeek(Calendar.MONDAY));
		Assert.assertThat(locacao.getDataRetorno(),dayMonday());
	}
	
	
	
	
	
}
