package br.com.registerApi.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import br.com.registerApi.EntityGenericUtil;
import br.com.registerApi.dto.PessoaDTO;
import br.com.registerApi.entity.Pessoa;

/**
 * Classe de teste que representa os cenários de testes da classe {@link Util}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
public class UtilTest {
	
	@InjectMocks
	private Util util;
	
	@SuppressWarnings("static-access")
	@Test
	public void convertModelMapper() {
		
		PessoaDTO source = new PessoaDTO(EntityGenericUtil.getString(), 
				EntityGenericUtil.getGenero().getGenero(), EntityGenericUtil.getString(), 
				EntityGenericUtil.getDate(), EntityGenericUtil.getString(),
				EntityGenericUtil.getString(), EntityGenericUtil.getString());
		
		Pessoa pessoa = util.convertModelMapper(source, Pessoa.class);
		
		PessoaDTO character = util.convertModelMapper(pessoa, PessoaDTO.class);

		assertNotNull(pessoa);
		assertNotNull(character);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void convertModelMapperSourceNull() {
		
		Pessoa pessoa = util.convertModelMapper(null, Pessoa.class);

		assertNull(pessoa);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void convertModelMapperDestinationNull() {
		
		PessoaDTO source = new PessoaDTO(EntityGenericUtil.getString(), 
				EntityGenericUtil.getGenero().getGenero(), EntityGenericUtil.getString(), 
				EntityGenericUtil.getDate(), EntityGenericUtil.getString(),
				EntityGenericUtil.getString(), EntityGenericUtil.getString());
		
		Pessoa pessoa = util.convertModelMapper(source, null);

		assertNull(pessoa);
	}

	@SuppressWarnings("static-access")
	@Test
	public void getDataInicial() {
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.set(Calendar.getInstance().get(Calendar.YEAR) - Constantes.PERIODO_VALIDADE, 
				Calendar.JANUARY, 1);
	
		assertEquals(dataInicial, util.getDataInicial());
	}

}
