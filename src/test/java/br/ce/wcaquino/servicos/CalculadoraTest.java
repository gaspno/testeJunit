package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.exceptions.ZeroDivisorException;

public class CalculadoraTest {
	
	private Calculadora calc;
	@Before
	public void init() {
		calc=new Calculadora();
	}

	@Test
	public void somar() {
		//
		int a=5;
		int b=3;
		
		
		//
		int s=calc.somar(a,b);
		
		
		
		//
		Assert.assertEquals(8, s);
	}
	@Test
	public void subtrair() {
		//
		int a=5;
		int b=3;
		
		
		//
		int s=calc.subtrair(a,b);
		
		
		
		//
		Assert.assertEquals(2, s);
	}
	@Test
	public void dividir() throws Exception {
		//
		int a=6;
		int b=3;
		
		
		//
		int s=calc.dividir(a,b);
		
		
		
		//
		Assert.assertEquals(2, s);
	}
	@Test( expected =  ZeroDivisorException.class)
	public void DivisionForZeroException() throws Exception {
		//
		int a=11;
		int b=0;
		Calculadora calc=new Calculadora();
		
		//
		int s=calc.dividir(a,b);
		
		
		
		//
		Assert.assertEquals(8, s);
	}
}
