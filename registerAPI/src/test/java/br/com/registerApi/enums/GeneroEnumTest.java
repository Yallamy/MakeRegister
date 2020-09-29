package br.com.registerApi.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Classe de teste que representa os cenários de testes da classe {@link GeneroEnum}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
public class GeneroEnumTest {
	
	@Test
	public void generoMasculinoTest() {
		
		assertEquals(GeneroEnum.MASCULINO, GeneroEnum.obterGenero(GeneroEnum.MASCULINO.getGenero()));
	}
	
	@Test
	public void generoFemininoTest() {
		
		assertEquals(GeneroEnum.FEMININO, GeneroEnum.obterGenero(GeneroEnum.FEMININO.getGenero()));
	}
	
	@Test
	public void generoOutrosTest() {
		
		assertEquals(GeneroEnum.OUTROS, GeneroEnum.obterGenero(GeneroEnum.OUTROS.getGenero()));
	}
	
	@Test
	public void generoNaoInformadoTest() {
		
		assertEquals(GeneroEnum.NAO_INFORMADO, GeneroEnum.obterGenero(GeneroEnum.NAO_INFORMADO.getGenero()));
	}
	
	@Test
	public void generoNullTest() {
		
		assertEquals(GeneroEnum.NAO_INFORMADO, GeneroEnum.obterGenero(null));
	}

}
