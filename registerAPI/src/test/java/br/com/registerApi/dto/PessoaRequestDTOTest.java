package br.com.registerApi.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.registerApi.EntityGenericUtil;

/**
 * Classe de teste que representa os cenários de testes da classe {@link PessoaRequestDTO}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
public class PessoaRequestDTOTest {
	
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

		PessoaRequestDTO request = new PessoaRequestDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		assertNotNull(request);
		assertEquals(this.id, request.getId());
		assertEquals(this.nome, request.getNome());
		assertEquals(this.genero, request.getGenero());
		assertEquals(this.email, request.getEmail());
		assertEquals(this.dtNascimento, request.getDtNascimento());
		assertEquals(this.naturalidade, request.getNaturalidade());
		assertEquals(this.nacionalidade, request.getNacionalidade());
		assertEquals(this.cpf, request.getCpf());
	}
	
	@Test
	public void getInstanceBuilderTest() {

		PessoaRequestDTO request = PessoaRequestDTO.builder()
				.id(this.id)
				.nome(this.nome)
				.genero(this.genero)
				.email(this.email)
				.dtNascimento(this.dtNascimento)
				.naturalidade(this.naturalidade)
				.nacionalidade(this.nacionalidade)
				.cpf(this.cpf)
				.build();

		assertNotNull(request);
		assertEquals(this.id, request.getId());
		assertEquals(this.nome, request.getNome());
		assertEquals(this.genero, request.getGenero());
		assertEquals(this.email, request.getEmail());
		assertEquals(this.dtNascimento, request.getDtNascimento());
		assertEquals(this.naturalidade, request.getNaturalidade());
		assertEquals(this.nacionalidade, request.getNacionalidade());
		assertEquals(this.cpf, request.getCpf());
	}
	
	@Test
	public void getInstanceVaziaTest() {

		PessoaRequestDTO request = new PessoaRequestDTO();

		assertNotNull(request);
		assertEquals(null, request.getId());
		assertEquals(null, request.getNome());
		assertEquals(null, request.getGenero());
		assertEquals(null, request.getEmail());
		assertEquals(null, request.getDtNascimento());
		assertEquals(null, request.getNaturalidade());
		assertEquals(null, request.getNacionalidade());
		assertEquals(null, request.getCpf());
	}

	@Test
	public void setAndGetAllFieldsTest() {

		PessoaRequestDTO request = new PessoaRequestDTO(
				EntityGenericUtil.getLong(), EntityGenericUtil.getString(), 
				EntityGenericUtil.getString(), EntityGenericUtil.getString(), 
				EntityGenericUtil.getDate(), EntityGenericUtil.getString(),
				EntityGenericUtil.getString(), EntityGenericUtil.getString());
		
		request.setId(this.id);
		request.setNome(this.nome);
		request.setGenero(this.genero);
		request.setEmail(this.email);
		request.setDtNascimento(this.dtNascimento);
		request.setNaturalidade(this.naturalidade);
		request.setNacionalidade(this.nacionalidade);
		request.setCpf(this.cpf);

		assertEquals(this.id, request.getId());
		assertEquals(this.nome, request.getNome());
		assertEquals(this.genero, request.getGenero());
		assertEquals(this.email, request.getEmail());
		assertEquals(this.dtNascimento, request.getDtNascimento());
		assertEquals(this.naturalidade, request.getNaturalidade());
		assertEquals(this.nacionalidade, request.getNacionalidade());
		assertEquals(this.cpf, request.getCpf());
	}
	
	@Test
	public void getEqualsTest() {

		PessoaRequestDTO request = new PessoaRequestDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);
		
		PessoaRequestDTO request2 = new PessoaRequestDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		assertNotNull(request);
		assertNotNull(request2);
		assertEquals(request, request2);
	}
	
	@Test
	public void getHashCodeTest() {

		PessoaRequestDTO request = new PessoaRequestDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);
		
		PessoaRequestDTO request2 = new PessoaRequestDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		assertNotNull(request);
		assertNotNull(request2);
		assertEquals(request.hashCode(), request2.hashCode());
	}
	
	@Test
	public void getToStringTest() {

		PessoaRequestDTO request = new PessoaRequestDTO(this.id, this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		assertNotNull(request);
		assertNotNull(request.toString());
	}

}
