package br.com.registerApi.service.impl;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasToString;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;
import java.util.List;
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

import br.com.registerApi.EntityGenericUtil;
import br.com.registerApi.entity.HistoricoPessoa;
import br.com.registerApi.entity.Pessoa;
import br.com.registerApi.exception.CustomException;
import br.com.registerApi.repository.HistoricoPessoaRepository;
import junit.framework.TestCase;

/**
 * Classe de teste que representa os cenários de testes da classe {@link HistoricoPessoaServiceImpl}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@RunWith(SpringRunner.class)
public class HistoricoPessoaServiceImplTest {
	
	@InjectMocks
	private HistoricoPessoaServiceImpl historicoPessoaServiceImpl;
	
	@Mock
	private HistoricoPessoaRepository repository;
	
	@Mock
	private Pageable pageable;
	
	@Mock
	private Page<HistoricoPessoa> page;
	
	@Mock
	private Pessoa pessoa;
	
	private List<HistoricoPessoa> listaHistorico;
	
	private Optional<HistoricoPessoa> historicoResponse;
	
	private HistoricoPessoa historico;
	
	private Validator validator;

	@SuppressWarnings("unchecked")
	@Before
	public void setup() throws CustomException {
		
		this.historico = HistoricoPessoa.builder()
				.id(EntityGenericUtil.getLong())
				.dtAlteracao(EntityGenericUtil.getDate())
				.descAlteracao(EntityGenericUtil.getString())
				.pessoa(this.pessoa)
				.build();
		
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
        
        this.historicoResponse = Optional.of(this.historico);
        
        this.listaHistorico = new LinkedList<HistoricoPessoa>();
        this.listaHistorico.add(this.historico);

		Mockito.when(this.repository.save(
				Mockito.any(HistoricoPessoa.class))).thenReturn(this.historico);
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenReturn(this.historicoResponse);
		Mockito.when(this.repository.findAllByPessoa(
				Mockito.any(Pessoa.class))).thenReturn(this.listaHistorico);
		Mockito.when(this.repository.deleteAllByPessoa(
				Mockito.any(Pessoa.class))).thenReturn(new Long(1));
		Mockito.when(this.repository.findAll(
				Mockito.any(Example.class), Mockito.any(Pageable.class))).thenReturn(this.page);
	}
	
	//create
	@Test
	public void createTest() throws CustomException {

		HistoricoPessoa request = HistoricoPessoa.builder()
				.dtAlteracao(EntityGenericUtil.getDate())
				.descAlteracao(EntityGenericUtil.getString())
				.pessoa(this.pessoa)
				.build();
		
		this.historicoPessoaServiceImpl.init();
		HistoricoPessoa response = this.historicoPessoaServiceImpl.create(request);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getId());
	}
	
	@Test(expected=CustomException.class)
	public void createHistoricoNullTest() throws CustomException {

		this.historicoPessoaServiceImpl.init();
		this.historicoPessoaServiceImpl.create(null);
	}
	
	@Test(expected=CustomException.class)
	public void createDtAlteracaoNullTest() throws CustomException {
		
		HistoricoPessoa request = HistoricoPessoa.builder()
				.descAlteracao(EntityGenericUtil.getString())
				.pessoa(this.pessoa)
				.build();

		this.historicoPessoaServiceImpl.init();
		this.historicoPessoaServiceImpl.create(request);
		
		Set<ConstraintViolation<HistoricoPessoa>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("dtAlteracao"))));
	}
	
	@Test(expected=CustomException.class)
	public void createDescAlteracaoNullTest() throws CustomException {
		
		HistoricoPessoa request = HistoricoPessoa.builder()
				.dtAlteracao(EntityGenericUtil.getDate())
				.pessoa(this.pessoa)
				.build();

		this.historicoPessoaServiceImpl.init();
		this.historicoPessoaServiceImpl.create(request);
		
		Set<ConstraintViolation<HistoricoPessoa>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("descAlteracao"))));
	}
	
	@Test(expected=CustomException.class)
	public void createPessoaNullTest() throws CustomException {
		
		HistoricoPessoa request = HistoricoPessoa.builder()
				.dtAlteracao(EntityGenericUtil.getDate())
				.descAlteracao(EntityGenericUtil.getString())
				.build();

		this.historicoPessoaServiceImpl.init();
		this.historicoPessoaServiceImpl.create(request);
		
		Set<ConstraintViolation<HistoricoPessoa>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
		assertThat(violations, contains(hasProperty("propertyPath", hasToString("pessoa"))));
	}
	
	//retrieve
	@Test
	public void retrieveTest() throws CustomException {
		
		HistoricoPessoa response = this.historicoPessoaServiceImpl.retrieve(EntityGenericUtil.getLong());

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getId());
		TestCase.assertEquals(this.historico, response);
	}
	
	@Test(expected=CustomException.class)
	public void retrieveNotFoundTest() throws CustomException {
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);
		
		this.historicoPessoaServiceImpl.retrieve(EntityGenericUtil.getLong());

	}
	
	@Test(expected=CustomException.class)
	public void retrieveComNullTest() throws CustomException {
		
		Long request = null;
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenReturn(null);
		
		this.historicoPessoaServiceImpl.retrieve(request);
	}
	
	//retrieve por pessoa
	@Test
	public void retrieveByPessoaTest() throws CustomException {

		List<HistoricoPessoa> response = this.historicoPessoaServiceImpl.retrieve(this.pessoa);

		TestCase.assertNotNull(response);
		TestCase.assertTrue(response.size() == 1);
	}

	@Test(expected=CustomException.class)
	public void retrieveByPessoaNotFoundTest() throws CustomException {

		Mockito.when(this.repository.findAllByPessoa(
				Mockito.any(Pessoa.class))).thenReturn(null);

		this.historicoPessoaServiceImpl.retrieve(this.pessoa);

	}

	@Test(expected=CustomException.class)
	public void retrieveByPessoaComNullTest() throws CustomException {

		Pessoa request = null;

		this.historicoPessoaServiceImpl.retrieve(request);
	}
	
	//delete
	@Test
	public void deleteTest() throws CustomException {

		this.historicoPessoaServiceImpl.init();
		this.historicoPessoaServiceImpl.delete(this.pessoa);
	}

	@Test
	public void deleteNotFoundTest() throws CustomException {

		Mockito.when(this.repository.deleteAllByPessoa(
				Mockito.any(Pessoa.class))).thenReturn(new Long(0));

		this.historicoPessoaServiceImpl.init();
		this.historicoPessoaServiceImpl.delete(this.pessoa);
	}

	@Test(expected=CustomException.class)
	public void deletePessoamNullTest() throws CustomException {

		this.historicoPessoaServiceImpl.init();
		this.historicoPessoaServiceImpl.delete(null);
	}

	@Test(expected=CustomException.class)
	public void deletePessoaSemIdTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.build();

		this.historicoPessoaServiceImpl.init();
		this.historicoPessoaServiceImpl.delete(request);
	}

	//list
	@Test
	public void listTest() throws CustomException {

		HistoricoPessoa request = HistoricoPessoa.builder()
				.build();

		Page<HistoricoPessoa> response = this.historicoPessoaServiceImpl.list(request, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}

	@Test
	public void listComPessoaNullTest() throws CustomException {

		Page<HistoricoPessoa> response = this.historicoPessoaServiceImpl.list(null, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}

	@Test(expected=CustomException.class)
	public void listComPageableNullTest() throws CustomException {

		HistoricoPessoa request = HistoricoPessoa.builder()
				.build();

		Page<HistoricoPessoa> response = this.historicoPessoaServiceImpl.list(request, null);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}

	@Test
	public void listPorIdTest() throws CustomException {

		HistoricoPessoa request = HistoricoPessoa.builder()
				.id(EntityGenericUtil.getLong())
				.build();

		Page<HistoricoPessoa> response = this.historicoPessoaServiceImpl.list(request, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorDtAlteracaoTest() throws CustomException {

		HistoricoPessoa request = HistoricoPessoa.builder()
				.dtAlteracao(EntityGenericUtil.getDate())
				.build();

		Page<HistoricoPessoa> response = this.historicoPessoaServiceImpl.list(request, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorDescAlteracaoTest() throws CustomException {

		HistoricoPessoa request = HistoricoPessoa.builder()
				.descAlteracao(EntityGenericUtil.getString())
				.build();

		Page<HistoricoPessoa> response = this.historicoPessoaServiceImpl.list(request, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorPessoaTest() throws CustomException {

		HistoricoPessoa request = HistoricoPessoa.builder()
				.pessoa(this.pessoa)
				.build();

		Page<HistoricoPessoa> response = this.historicoPessoaServiceImpl.list(request, pageable);

		TestCase.assertNotNull(response);
		TestCase.assertNotNull(response.getContent().size() == 1);
	}

}
