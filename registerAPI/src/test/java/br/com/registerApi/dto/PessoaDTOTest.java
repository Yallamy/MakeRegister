package br.com.registerApi.dto;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasToString;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.registerApi.EntityGenericUtil;
import junit.framework.TestCase;

/**
 * Classe de teste que representa os cenários de testes da classe {@link PessoaDTO}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@RunWith(SpringRunner.class)
public class PessoaDTOTest {
	
	private Validator validator;
	
	private String nome;
	
	private String sexo;
	
	private String email;
	
	private Date dtNascimento;
	
	private String naturalidade;
	
	private String nacionalidade;
	
	private String cpf;

	@Before
	public void setup() {

		this.nome = EntityGenericUtil.getString();
		this.sexo = EntityGenericUtil.getString();
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

		PessoaDTO request = new PessoaDTO(this.nome, this.sexo, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		TestCase.assertNotNull(request);
		TestCase.assertEquals(this.nome, request.getNome());
		TestCase.assertEquals(this.sexo, request.getSexo());
		TestCase.assertEquals(this.email, request.getEmail());
		TestCase.assertEquals(this.dtNascimento, request.getDtNascimento());
		TestCase.assertEquals(this.naturalidade, request.getNaturalidade());
		TestCase.assertEquals(this.nacionalidade, request.getNacionalidade());
		TestCase.assertEquals(this.cpf, request.getCpf());
	}
	
	@Test
	public void getInstanceVaziaTest() {

		PessoaDTO request = new PessoaDTO();

		TestCase.assertNotNull(request);
		TestCase.assertEquals(null, request.getNome());
		TestCase.assertEquals(null, request.getSexo());
		TestCase.assertEquals(null, request.getEmail());
		TestCase.assertEquals(null, request.getDtNascimento());
		TestCase.assertEquals(null, request.getNaturalidade());
		TestCase.assertEquals(null, request.getNacionalidade());
		TestCase.assertEquals(null, request.getCpf());
	}
	
	@Test
	public void getInstanceTestNomeNull() {

		PessoaDTO request = new PessoaDTO(null, this.sexo, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		TestCase.assertNotNull(request);
		Set<ConstraintViolation<PessoaDTO>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("nome"))));
	}
	
	@Test
	public void getInstanceTestDtNascimentoNull() {

		PessoaDTO request = new PessoaDTO(this.nome, this.sexo, this.email, 
				null, this.naturalidade, this.nacionalidade, this.cpf);

		TestCase.assertNotNull(request);
		Set<ConstraintViolation<PessoaDTO>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("dtNascimento"))));
	}
	
	@Test
	public void getInstanceTestCPFNull() {

		PessoaDTO request = new PessoaDTO(this.nome, this.sexo, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, null);

		TestCase.assertNotNull(request);
		Set<ConstraintViolation<PessoaDTO>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("cpf"))));
	}

	@Test
	public void setAndGetAllFieldsTest() {

		PessoaDTO request = new PessoaDTO(EntityGenericUtil.getString(), 
				EntityGenericUtil.getString(), EntityGenericUtil.getString(), 
				EntityGenericUtil.getDate(), EntityGenericUtil.getString(),
				EntityGenericUtil.getString(), EntityGenericUtil.getString());
		
		request.setNome(this.nome);
		request.setSexo(this.sexo);
		request.setEmail(this.email);
		request.setDtNascimento(this.dtNascimento);
		request.setNaturalidade(this.naturalidade);
		request.setNacionalidade(this.nacionalidade);
		request.setCpf(this.cpf);

		TestCase.assertEquals(this.nome, request.getNome());
		TestCase.assertEquals(this.sexo, request.getSexo());
		TestCase.assertEquals(this.email, request.getEmail());
		TestCase.assertEquals(this.dtNascimento, request.getDtNascimento());
		TestCase.assertEquals(this.naturalidade, request.getNaturalidade());
		TestCase.assertEquals(this.nacionalidade, request.getNacionalidade());
		TestCase.assertEquals(this.cpf, request.getCpf());
	}
	
	@Test
	public void getEqualsTest() {

		PessoaDTO request = new PessoaDTO(this.nome, this.sexo, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);
		
		PessoaDTO request2 = new PessoaDTO(this.nome, this.sexo, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		TestCase.assertNotNull(request);
		TestCase.assertNotNull(request2);
		TestCase.assertEquals(request, request2);
	}
	
	@Test
	public void getHashCodeTest() {

		PessoaDTO request = new PessoaDTO(this.nome, this.sexo, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);
		
		PessoaDTO request2 = new PessoaDTO(this.nome, this.sexo, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		TestCase.assertNotNull(request);
		TestCase.assertNotNull(request2);
		TestCase.assertEquals(request.hashCode(), request2.hashCode());
	}
	
	@Test
	public void getToStringTest() {

		PessoaDTO request = new PessoaDTO(this.nome, this.sexo, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		TestCase.assertNotNull(request);
		TestCase.assertNotNull(request.toString());
	}
}
