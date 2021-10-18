package br.ce.wcaquino.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.servicos.CalcPriceLocation;
import br.ce.wcaquino.servicos.CalculadoraTest;
import br.ce.wcaquino.servicos.LocacaoServiceTest;


@RunWith(org.junit.runners.Suite.class)
@SuiteClasses(value = {
		CalculadoraTest.class,
		CalcPriceLocation.class,
		LocacaoServiceTest.class })
public class Suite {
	

}
