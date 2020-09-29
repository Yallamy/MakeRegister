package br.com.registerApi.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.registerApi.EntityGenericUtil;

/**
 * Classe de teste que representa os cenários de testes da classe {@link PessoaDTO}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
public class PessoaDTOTest {
	
	private Validator validator;
	
	private String nome;
	
	private String genero;
	
	private String email;
	
	private Date dtNascimento;
	
	private String naturalidade;
	
	private String nacionalidade;
	
	private String cpf;

	@BeforeEach
	public void setup() {

		this.nome = EntityGenericUtil.getString();
		this.genero = EntityGenericUtil.getGenero().getGenero();
		this.email = EntityGenericUtil.getString();
		this.dtNascimento = EntityGenericUtil.getDate();
		this.naturalidade = EntityGenericUtil.getString();
		this.nacionalidade = EntityGenericUtil.getString();
		this.cpf = EntityGenericUtil.getString();
		
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
	}

	@Test
	public void getInstanceTest() {

		PessoaDTO request = new PessoaDTO(this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		assertNotNull(request);
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

		PessoaDTO request = new PessoaDTO();

		assertNotNull(request);
		assertEquals(null, request.getNome());
		assertEquals(null, request.getGenero());
		assertEquals(null, request.getEmail());
		assertEquals(null, request.getDtNascimento());
		assertEquals(null, request.getNaturalidade());
		assertEquals(null, request.getNacionalidade());
		assertEquals(null, request.getCpf());
	}
	
	@Test
	public void getInstanceTestNomeNull() {

		PessoaDTO request = new PessoaDTO(null, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		assertNotNull(request);
		Set<ConstraintViolation<PessoaDTO>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
	}
	
	@Test
	public void getInstanceTestDtNascimentoNull() {

		PessoaDTO request = new PessoaDTO(this.nome, this.genero, this.email, 
				null, this.naturalidade, this.nacionalidade, this.cpf);

		assertNotNull(request);
		Set<ConstraintViolation<PessoaDTO>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
	}
	
	@Test
	public void getInstanceTestCPFNull() {

		PessoaDTO request = new PessoaDTO(this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, null);

		assertNotNull(request);
		Set<ConstraintViolation<PessoaDTO>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
	}

	@Test
	public void setAndGetAllFieldsTest() {

		PessoaDTO request = new PessoaDTO(EntityGenericUtil.getString(), 
				EntityGenericUtil.getGenero().getGenero(), EntityGenericUtil.getString(), 
				EntityGenericUtil.getDate(), EntityGenericUtil.getString(),
				EntityGenericUtil.getString(), EntityGenericUtil.getString());
		
		request.setNome(this.nome);
		request.setGenero(this.genero);
		request.setEmail(this.email);
		request.setDtNascimento(this.dtNascimento);
		request.setNaturalidade(this.naturalidade);
		request.setNacionalidade(this.nacionalidade);
		request.setCpf(this.cpf);

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

		PessoaDTO request = new PessoaDTO(this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);
		
		PessoaDTO request2 = new PessoaDTO(this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		assertNotNull(request);
		assertNotNull(request2);
		assertEquals(request, request2);
	}
	
	@Test
	public void getHashCodeTest() {

		PessoaDTO request = new PessoaDTO(this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);
		
		PessoaDTO request2 = new PessoaDTO(this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		assertNotNull(request);
		assertNotNull(request2);
		assertEquals(request.hashCode(), request2.hashCode());
	}
	
	@Test
	public void getToStringTest() {

		PessoaDTO request = new PessoaDTO(this.nome, this.genero, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		assertNotNull(request);
		assertNotNull(request.toString());
	}
}
