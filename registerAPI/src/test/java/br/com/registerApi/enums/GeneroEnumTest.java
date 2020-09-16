package br.com.registerApi.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.TestCase;

/**
 * Classe de teste que representa os cenários de testes da classe {@link GeneroEnum}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@RunWith(SpringRunner.class)
public class GeneroEnumTest {
	
	@Test
	public void generoMasculinoTest() {
		
		TestCase.assertEquals(GeneroEnum.MASCULINO, GeneroEnum.obterGenero(GeneroEnum.MASCULINO.getGenero()));
	}
	
	@Test
	public void generoFemininoTest() {
		
		TestCase.assertEquals(GeneroEnum.FEMININO, GeneroEnum.obterGenero(GeneroEnum.FEMININO.getGenero()));
	}
	
	@Test
	public void generoOutrosTest() {
		
		TestCase.assertEquals(GeneroEnum.OUTROS, GeneroEnum.obterGenero(GeneroEnum.OUTROS.getGenero()));
	}
	
	@Test
	public void generoNaoInformadoTest() {
		
		TestCase.assertEquals(GeneroEnum.NAO_INFORMADO, GeneroEnum.obterGenero(GeneroEnum.NAO_INFORMADO.getGenero()));
	}
	
	@Test
	public void generoNullTest() {
		
		TestCase.assertEquals(GeneroEnum.NAO_INFORMADO, GeneroEnum.obterGenero(null));
	}

}
