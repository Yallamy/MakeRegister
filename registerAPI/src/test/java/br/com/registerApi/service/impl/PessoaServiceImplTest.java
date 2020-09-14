package br.com.registerApi.service.impl;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasToString;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import br.com.registerApi.EntityGenericUtil;
import br.com.registerApi.entity.HistoricoPessoa;
import br.com.registerApi.entity.Pessoa;
import br.com.registerApi.exception.CustomException;
import br.com.registerApi.repository.PessoaRepository;
import br.com.registerApi.service.HistoricoPessoaService;
import junit.framework.TestCase;

/**
 * Classe de teste que representa os cenários de testes da classe {@link PessoaServiceImpl}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@RunWith(SpringRunner.class)
public class PessoaServiceImplTest {

	@InjectMocks
	private PessoaServiceImpl pessoaServiceImpl;
	
	@Mock
	private PessoaRepository repository;
	
	@Mock
	private HistoricoPessoaService historicoPessoaService;
	
	@Mock
	private Pageable pageable;
	
	@Mock
	private Page<Pessoa> page;
	
	@Mock
	private HistoricoPessoa historico;
	
	private Optional<Pessoa> pessoaResponse;
	
	private Pessoa pessoa;
	
	private Validator validator;
	
	private Date dtNascimento;

	@SuppressWarnings("unchecked")
	@Before
	public void setup() throws CustomException {
		
		this.pessoa = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getString())
				.build();
		
		this.pessoaResponse = Optional.of(this.pessoa);
		this.pessoaServiceImpl.setGson(new Gson());
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.set(1950, Calendar.JANUARY, 1);
		
		this.dtNascimento = dataInicial.getTime();
		
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();

		Mockito.when(this.repository.save(
				Mockito.any(Pessoa.class))).thenReturn(this.pessoa);
		Mockito.when(this.repository.findByCpf(
				Mockito.any(Pessoa.class))).thenReturn(null);
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenReturn(this.pessoaResponse);
		Mockito.when(this.repository.findAll(
				Mockito.any(Example.class), Mockito.any(Pageable.class))).thenReturn(this.page);
		Mockito.when(this.historicoPessoaService.create(
				Mockito.any(HistoricoPessoa.class))).thenReturn(this.historico);
	}
	
	//create
	@Test
	public void createTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		Pessoa response = this.pessoaServiceImpl.create(request);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getId());
	}
	
	@Test(expected=CustomException.class)
	public void createPessoaNullTest() throws CustomException {

		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.create(null);
	}
	
	@Test(expected=CustomException.class)
	public void createPessoaJaCadastradaTest() throws CustomException {
		
		Mockito.when(this.repository.findByCpf(
				Mockito.any(Pessoa.class))).thenReturn(this.pessoa);

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.create(request);
	}
	
	@Test(expected=CustomException.class)
	public void createPessoaSemNomeTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getString())
				.build();
		
		this.pessoaServiceImpl.init();
		Pessoa response = this.pessoaServiceImpl.create(request);

		TestCase.assertNotNull(response);
		Set<ConstraintViolation<Pessoa>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("nome"))));
	}
	
	@Test(expected=CustomException.class)
	public void createPessoaSemDtNascimentoTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		Pessoa response = this.pessoaServiceImpl.create(request);

		TestCase.assertNotNull(response);
		Set<ConstraintViolation<Pessoa>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("dtNascimento"))));
	}
	
	@Test(expected=CustomException.class)
	public void createPessoaSemCPFTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.build();
		
		this.pessoaServiceImpl.init();
		Pessoa response = this.pessoaServiceImpl.create(request);

		TestCase.assertNotNull(response);
		Set<ConstraintViolation<Pessoa>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("cpf"))));
	}
	
	@Test(expected=CustomException.class)
	public void createPessoaComEmailInvalidoTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getString())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getString())
				.build();
		
		this.pessoaServiceImpl.init();
		Pessoa response = this.pessoaServiceImpl.create(request);

		TestCase.assertNotNull(response);
		Set<ConstraintViolation<Pessoa>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("email"))));
	}
	
	@Test(expected=CustomException.class)
	public void createPessoaComCPFInvalidoTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getString())
				.build();
		
		this.pessoaServiceImpl.init();
		Pessoa response = this.pessoaServiceImpl.create(request);

		TestCase.assertNotNull(response);
		Set<ConstraintViolation<Pessoa>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("cpf"))));
	}
	
	@Test(expected=CustomException.class)
	public void createDtInvalidaTest() throws CustomException {
		
		Calendar dataInvalida = Calendar.getInstance();
		dataInvalida.set(1850, Calendar.JANUARY, 1);

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(dataInvalida.getTime())
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.create(request);
	}
	
	@Test(expected=CustomException.class)
	public void createDtInvalidaFuturaTest() throws CustomException {
		
		Calendar dataInvalida = Calendar.getInstance();
		dataInvalida.set(2050, Calendar.JANUARY, 1);

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(dataInvalida.getTime())
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.create(request);
	}
	
	//update
	@Test
	public void updateTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.update(request);
	}
	
	@Test(expected=CustomException.class)
	public void updatePessoaNullTest() throws CustomException {
		
		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.update(null);
	}
	
	@Test(expected=CustomException.class)
	public void updatePessoaJaCadastradaTest() throws CustomException {
		
		Mockito.when(this.repository.findByCpf(
				Mockito.any(Pessoa.class))).thenReturn(this.pessoa);

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.update(request);
	}
	
	@Test(expected=CustomException.class)
	public void updatePessoaNotFoundTest() throws CustomException {
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.update(request);
	}
	
	@Test(expected=CustomException.class)
	public void updatePessoaSemNomeTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.update(request);

		Set<ConstraintViolation<Pessoa>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("nome"))));
	}
	
	@Test(expected=CustomException.class)
	public void updatePessoaSemDtNascimentoTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.update(request);

		Set<ConstraintViolation<Pessoa>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("dtNascimento"))));
	}
	
	@Test(expected=CustomException.class)
	public void updatePessoaSemCPFTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.build();
		
		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.update(request);

		Set<ConstraintViolation<Pessoa>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("cpf"))));
	}
	
	@Test(expected=CustomException.class)
	public void updatePessoaComEmailInvalidoTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getString())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.update(request);

		Set<ConstraintViolation<Pessoa>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("email"))));
	}
	
	@Test(expected=CustomException.class)
	public void updatePessoaComCpfInvalidoTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getString())
				.build();
		
		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.update(request);

		Set<ConstraintViolation<Pessoa>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("cpf"))));
	}
	
	//retrieve
	@Test
	public void retrieveTest() throws CustomException {
		
		Pessoa response = this.pessoaServiceImpl.retrieve(EntityGenericUtil.getLong());

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getId());
		TestCase.assertEquals(this.pessoa, response);
	}
	
	@Test(expected=CustomException.class)
	public void retrieveNotFoundTest() throws CustomException {
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);
		
		this.pessoaServiceImpl.retrieve(EntityGenericUtil.getLong());
	}
	
	@Test(expected=CustomException.class)
	public void retrieveComNullTest() throws CustomException {
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenReturn(null);
		
		this.pessoaServiceImpl.retrieve(null);
	}
	
	//delete
	@Test
	public void deleteTest() throws CustomException {
	
		this.pessoaServiceImpl.delete(this.pessoa);
	}
	
	@Test(expected=CustomException.class)
	public void deleteNotFoundTest() throws CustomException {
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);
		
		this.pessoaServiceImpl.delete(this.pessoa);
	}
	
	@Test(expected=CustomException.class)
	public void deletePessoamNullTest() throws CustomException {
		
		this.pessoaServiceImpl.delete(null);
	}
	
	@Test(expected=CustomException.class)
	public void deletePessoaSemIdTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.sexo(EntityGenericUtil.getString())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.delete(request);
	}
	
	//list
	@Test
	public void listTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listComPessoaNullTest() throws CustomException {
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(null, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}
	
	@Test(expected=CustomException.class)
	public void listComPageableNullTest() throws CustomException {
		
		Pessoa request = Pessoa.builder()
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, null);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorIdTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorNomeTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorEmailTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.email(EntityGenericUtil.getString())
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}
	
	
	@Test
	public void listPorDtNascimentoTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.dtNascimento(this.dtNascimento)
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}
	
	
	@Test
	public void listPorNaturalidadeTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.naturalidade(EntityGenericUtil.getString())
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorNacionalidadeTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nacionalidade(EntityGenericUtil.getString())
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorCPFTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.cpf(EntityGenericUtil.getString())
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}
}
