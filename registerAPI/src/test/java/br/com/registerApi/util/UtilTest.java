package br.com.registerApi.util;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.registerApi.EntityGenericUtil;
import br.com.registerApi.dto.PessoaDTO;
import br.com.registerApi.entity.Pessoa;
import junit.framework.TestCase;

/**
 * Classe de teste que representa os cenários de testes da classe {@link Util}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@RunWith(SpringRunner.class)
public class UtilTest {
	
	@InjectMocks
	private Util util;
	
	@SuppressWarnings("static-access")
	@Test
	public void convertModelMapper() {
		
		PessoaDTO source = new PessoaDTO(EntityGenericUtil.getString(), 
				EntityGenericUtil.getString(), EntityGenericUtil.getString(), 
				EntityGenericUtil.getDate(), EntityGenericUtil.getString(),
				EntityGenericUtil.getString(), EntityGenericUtil.getString());
		
		Pessoa pessoa = util.convertModelMapper(source, Pessoa.class);
		
		PessoaDTO character = util.convertModelMapper(pessoa, PessoaDTO.class);

		TestCase.assertNotNull(pessoa);
		TestCase.assertNotNull(character);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void convertModelMapperSourceNull() {
		
		Pessoa pessoa = util.convertModelMapper(null, Pessoa.class);

		TestCase.assertNull(pessoa);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void convertModelMapperDestinationNull() {
		
		PessoaDTO source = new PessoaDTO(EntityGenericUtil.getString(), 
				EntityGenericUtil.getString(), EntityGenericUtil.getString(), 
				EntityGenericUtil.getDate(), EntityGenericUtil.getString(),
				EntityGenericUtil.getString(), EntityGenericUtil.getString());
		
		Pessoa pessoa = util.convertModelMapper(source, null);

		TestCase.assertNull(pessoa);
	}

	@SuppressWarnings("static-access")
	@Test
	public void getDataInicial() {
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.set(Calendar.getInstance().get(Calendar.YEAR) - Constantes.PERIODO_VALIDADE, 
				Calendar.JANUARY, 1);
	
		TestCase.assertEquals(dataInicial, util.getDataInicial());
	}

}
