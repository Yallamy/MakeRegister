package br.com.registerApi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.registerApi.EntityGenericUtil;
import br.com.registerApi.entity.HistoricoPessoa;
import br.com.registerApi.entity.Pessoa;
import br.com.registerApi.exception.CustomException;
import br.com.registerApi.repository.HistoricoPessoaRepository;

/**
 * Classe de teste que representa os cenários de testes da classe {@link HistoricoPessoaServiceImpl}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@SpringBootTest
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

	@SuppressWarnings("unchecked")
	@BeforeEach
	public void setup() throws CustomException {
		
		this.historico = HistoricoPessoa.builder()
				.id(EntityGenericUtil.getLong())
				.dtAlteracao(EntityGenericUtil.getDate())
				.descAlteracao(EntityGenericUtil.getString())
				.pessoa(this.pessoa)
				.build();
        
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

		assertNotNull(response);
		assertNotNull(response.getId());
	}
	
	@Test()
	public void createHistoricoNullTest() throws CustomException {

		this.historicoPessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.historicoPessoaServiceImpl.create(null);
		});
	}
	
	@Test()
	public void createDtAlteracaoNullTest() throws CustomException {
		
		HistoricoPessoa request = HistoricoPessoa.builder()
				.descAlteracao(EntityGenericUtil.getString())
				.pessoa(this.pessoa)
				.build();

		this.historicoPessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.historicoPessoaServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createDescAlteracaoNullTest() throws CustomException {
		
		HistoricoPessoa request = HistoricoPessoa.builder()
				.dtAlteracao(EntityGenericUtil.getDate())
				.pessoa(this.pessoa)
				.build();

		this.historicoPessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.historicoPessoaServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createPessoaNullTest() throws CustomException {
		
		HistoricoPessoa request = HistoricoPessoa.builder()
				.dtAlteracao(EntityGenericUtil.getDate())
				.descAlteracao(EntityGenericUtil.getString())
				.build();

		this.historicoPessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.historicoPessoaServiceImpl.create(request);
		});
	}
	
	//retrieve
	@Test
	public void retrieveTest() throws CustomException {
		
		HistoricoPessoa response = this.historicoPessoaServiceImpl.retrieve(EntityGenericUtil.getLong());

		assertNotNull(response);
		assertNotNull(response.getId());
		assertEquals(this.historico, response);
	}
	
	@Test()
	public void retrieveNotFoundTest() throws CustomException {
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);
		
		assertThrows(CustomException.class, () -> {
			this.historicoPessoaServiceImpl.retrieve(EntityGenericUtil.getLong());
		});

	}
	
	@Test()
	public void retrieveComNullTest() throws CustomException {
		
		Long request = null;
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenReturn(null);
		
		assertThrows(CustomException.class, () -> {
			this.historicoPessoaServiceImpl.retrieve(request);
		});
	}
	
	//retrieve por pessoa
	@Test
	public void retrieveByPessoaTest() throws CustomException {

		List<HistoricoPessoa> response = this.historicoPessoaServiceImpl.retrieve(this.pessoa);

		assertNotNull(response);
		assertTrue(response.size() == 1);
	}

	@Test()
	public void retrieveByPessoaNotFoundTest() throws CustomException {

		Mockito.when(this.repository.findAllByPessoa(
				Mockito.any(Pessoa.class))).thenReturn(null);
		
		assertThrows(CustomException.class, () -> {
			this.historicoPessoaServiceImpl.retrieve(this.pessoa);
		});

	}

	@Test()
	public void retrieveByPessoaComNullTest() throws CustomException {

		Pessoa request = null;
		
		assertThrows(CustomException.class, () -> {
			this.historicoPessoaServiceImpl.retrieve(request);
		});
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

	@Test()
	public void deletePessoamNullTest() throws CustomException {

		this.historicoPessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.historicoPessoaServiceImpl.delete(null);
		});
	}

	@Test()
	public void deletePessoaSemIdTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.build();

		this.historicoPessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.historicoPessoaServiceImpl.delete(request);
		});
	}

	//list
	@Test
	public void listTest() throws CustomException {

		HistoricoPessoa request = HistoricoPessoa.builder()
				.build();

		Page<HistoricoPessoa> response = this.historicoPessoaServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}

	@Test
	public void listComPessoaNullTest() throws CustomException {

		Page<HistoricoPessoa> response = this.historicoPessoaServiceImpl.list(null, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}

	@Test()
	public void listComPageableNullTest() throws CustomException {

		HistoricoPessoa request = HistoricoPessoa.builder()
				.build();
		
		assertThrows(CustomException.class, () -> {
			this.historicoPessoaServiceImpl.list(request, null);
		});
	}

	@Test
	public void listPorIdTest() throws CustomException {

		HistoricoPessoa request = HistoricoPessoa.builder()
				.id(EntityGenericUtil.getLong())
				.build();

		Page<HistoricoPessoa> response = this.historicoPessoaServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorDtAlteracaoTest() throws CustomException {

		HistoricoPessoa request = HistoricoPessoa.builder()
				.dtAlteracao(EntityGenericUtil.getDate())
				.build();

		Page<HistoricoPessoa> response = this.historicoPessoaServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorDescAlteracaoTest() throws CustomException {

		HistoricoPessoa request = HistoricoPessoa.builder()
				.descAlteracao(EntityGenericUtil.getString())
				.build();

		Page<HistoricoPessoa> response = this.historicoPessoaServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorPessoaTest() throws CustomException {

		HistoricoPessoa request = HistoricoPessoa.builder()
				.pessoa(this.pessoa)
				.build();

		Page<HistoricoPessoa> response = this.historicoPessoaServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}

}
