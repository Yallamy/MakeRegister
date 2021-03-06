package br.com.registerApi.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.registerApi.EntityGenericUtil;

/**
 * Classe de teste que representa os cenários de testes da classe {@link PessoaResponseDTO}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
public class PessoaResponseDTOTest {

	private Long id;
	
	private String nome;
	
	private String genero;
	
	private String email;
	
	private Date dtNascimento;
	
	private String naturalidade;
	
	private String nacionalidade;
	
	private String cpf;
	
	@BeforeEach
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

		assertNotNull(response);
		assertEquals(this.id, response.getId());
		assertEquals(this.nome, response.getNome());
		assertEquals(this.genero, response.getGenero());
		assertEquals(this.email, response.getEmail());
		assertEquals(this.dtNascimento, response.getDtNascimento());
		assertEquals(this.naturalidade, response.getNaturalidade());
		assertEquals(this.nacionalidade, response.getNacionalidade());
		assertEquals(this.cpf, response.getCpf());
	}
	
	@Test
	public void getInstanceVaziaTest() {

		PessoaResponseDTO response = new PessoaResponseDTO();

		assertNotNull(response);
		assertEquals(null, response.getId());
		assertEquals(null, response.getNome());
		assertEquals(null, response.getGenero());
		assertEquals(null, response.getEmail());
		assertEquals(null, response.getDtNascimento());
		assertEquals(null, response.getNaturalidade());
		assertEquals(null, response.getNacionalidade());
		assertEquals(null, response.getCpf());
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

		assertEquals(this.id, response.getId());
		assertEquals(this.nome, response.getNome());
		assertEquals(this.genero, response.getGenero());
		assertEquals(this.email, response.getEmail());
		assertEquals(this.dtNascimento, response.getDtNascimento());
		assertEquals(this.naturalidade, response.getNaturalidade());
		assertEquals(this.nacionalidade, response.getNacionalidade());
		assertEquals(this.cpf, response.getCpf());
	}
	
	@Test
	public void getEqualsTest() {

		PessoaResponseDTO response = new PessoaResponseDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);
		
		PessoaResponseDTO response2 = new PessoaResponseDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		assertNotNull(response);
		assertNotNull(response2);
		assertEquals(response, response2);
	}
	
	@Test
	public void getHashCodeTest() {

		PessoaResponseDTO response = new PessoaResponseDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);
		
		PessoaResponseDTO response2 = new PessoaResponseDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		assertNotNull(response);
		assertNotNull(response2);
		assertEquals(response.hashCode(), response2.hashCode());
	}
	
	@Test
	public void getToStringTest() {

		PessoaResponseDTO response = new PessoaResponseDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		assertNotNull(response);
		assertNotNull(response.toString());
	}

}
