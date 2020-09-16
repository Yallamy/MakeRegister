package br.com.registerApi.dto;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.registerApi.EntityGenericUtil;
import junit.framework.TestCase;

/**
 * Classe de teste que representa os cenários de testes da classe {@link PessoaResponseDTO}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@RunWith(SpringRunner.class)
public class PessoaResponseDTOTest {

	private Long id;
	
	private String nome;
	
	private String genero;
	
	private String email;
	
	private Date dtNascimento;
	
	private String naturalidade;
	
	private String nacionalidade;
	
	private String cpf;
	
	@Before
	public void setup() {

		this.id = EntityGenericUtil.getLong();
		this.nome = EntityGenericUtil.getString();
		this.genero = EntityGenericUtil.getString();
		this.email = EntityGenericUtil.getString();
		this.dtNascimento = EntityGenericUtil.getDate();
		this.naturalidade = EntityGenericUtil.getString();
		this.nacionalidade = EntityGenericUtil.getString();
		this.cpf = EntityGenericUtil.getString();
	}

	@Test
	public void getInstanceTest() {

		PessoaResponseDTO response = new PessoaResponseDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		TestCase.assertNotNull(response);
		TestCase.assertEquals(this.id, response.getId());
		TestCase.assertEquals(this.nome, response.getNome());
		TestCase.assertEquals(this.genero, response.getGenero());
		TestCase.assertEquals(this.email, response.getEmail());
		TestCase.assertEquals(this.dtNascimento, response.getDtNascimento());
		TestCase.assertEquals(this.naturalidade, response.getNaturalidade());
		TestCase.assertEquals(this.nacionalidade, response.getNacionalidade());
		TestCase.assertEquals(this.cpf, response.getCpf());
	}
	
	@Test
	public void getInstanceVaziaTest() {

		PessoaResponseDTO response = new PessoaResponseDTO();

		TestCase.assertNotNull(response);
		TestCase.assertEquals(null, response.getId());
		TestCase.assertEquals(null, response.getNome());
		TestCase.assertEquals(null, response.getGenero());
		TestCase.assertEquals(null, response.getEmail());
		TestCase.assertEquals(null, response.getDtNascimento());
		TestCase.assertEquals(null, response.getNaturalidade());
		TestCase.assertEquals(null, response.getNacionalidade());
		TestCase.assertEquals(null, response.getCpf());
	}

	@Test
	public void setAndGetAllFieldsTest() {

		PessoaResponseDTO response = new PessoaResponseDTO(
				EntityGenericUtil.getLong(), EntityGenericUtil.getString(), 
				EntityGenericUtil.getString(), EntityGenericUtil.getString(), 
				EntityGenericUtil.getDate(), EntityGenericUtil.getString(),
				EntityGenericUtil.getString(), EntityGenericUtil.getString());
		
		response.setId(this.id);
		response.setNome(this.nome);
		response.setGenero(this.genero);
		response.setEmail(this.email);
		response.setDtNascimento(this.dtNascimento);
		response.setNaturalidade(this.naturalidade);
		response.setNacionalidade(this.nacionalidade);
		response.setCpf(this.cpf);

		TestCase.assertEquals(this.id, response.getId());
		TestCase.assertEquals(this.nome, response.getNome());
		TestCase.assertEquals(this.genero, response.getGenero());
		TestCase.assertEquals(this.email, response.getEmail());
		TestCase.assertEquals(this.dtNascimento, response.getDtNascimento());
		TestCase.assertEquals(this.naturalidade, response.getNaturalidade());
		TestCase.assertEquals(this.nacionalidade, response.getNacionalidade());
		TestCase.assertEquals(this.cpf, response.getCpf());
	}
	
	@Test
	public void getEqualsTest() {

		PessoaResponseDTO response = new PessoaResponseDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);
		
		PessoaResponseDTO response2 = new PessoaResponseDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response2);
		TestCase.assertEquals(response, response2);
	}
	
	@Test
	public void getHashCodeTest() {

		PessoaResponseDTO response = new PessoaResponseDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);
		
		PessoaResponseDTO response2 = new PessoaResponseDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response2);
		TestCase.assertEquals(response.hashCode(), response2.hashCode());
	}
	
	@Test
	public void getToStringTest() {

		PessoaResponseDTO response = new PessoaResponseDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.toString());
	}

}
