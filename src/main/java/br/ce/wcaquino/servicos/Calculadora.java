package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.ZeroDivisorException;

public class Calculadora {
	
	public int somar(int a,int b) {
		return a+b;
	}

	public int subtrair(int a, int b) {
		// TODO Auto-generated method stub
		return a-b;
	}

	public int dividir(int a, int b) throws Exception {
		// TODO Auto-generated method stub
		if(b==0) {
			throw new ZeroDivisorException();
		}
		return a/b;
	}
	

}
