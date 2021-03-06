package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;


public class LocacaoService {
	
	protected String vPublico; 
	
	public Locacao alugarFilme(Usuario usuario,List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
				if(usuario==null) {
			throw new LocadoraException("Usuario vazio");
		}
		
		if(filmes==null||filmes.isEmpty()) {
			throw new LocadoraException("Filme vazio");
		}
		for(Filme filme:filmes) {
		if(filme.getEstoque()==0) {
			throw new FilmeSemEstoqueException();
		}
		}
		
		Locacao locacao = new Locacao();
		locacao.setFilme(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(0.0);
		for(int i=0;i<filmes.size();i++) {
			 if(i>=5) {
					locacao.setValor(locacao.getValor()+filmes.get(i).getPrecoLocacao()*0.10);
				}
		else if(i==4) {
				locacao.setValor(locacao.getValor()+filmes.get(i).getPrecoLocacao()*0.25);
			}
		else if(i==3) {
				locacao.setValor(locacao.getValor()+filmes.get(i).getPrecoLocacao()*0.50);
			}
		else if(i==2) {
		locacao.setValor(locacao.getValor()+filmes.get(i).getPrecoLocacao()*0.75);
			}else {
				locacao.setValor(locacao.getValor()+filmes.get(i).getPrecoLocacao());
			
			}
		
		}
		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)) {
			dataEntrega=DataUtils.adicionarDias(dataEntrega, 1);
		}
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
	
		return locacao;
	}


}