package br.com.registerApi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.Date;
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
import br.com.registerApi.repository.PessoaRepository;
import br.com.registerApi.service.HistoricoPessoaService;

/**
 * Classe de teste que representa os cenários de testes da classe {@link PessoaServiceImpl}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@SpringBootTest
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
	
	private Date dtNascimento;

	@SuppressWarnings("unchecked")
	@BeforeEach
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
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.set(1950, Calendar.JANUARY, 1);
		
		this.dtNascimento = dataInicial.getTime();

		Mockito.when(this.repository.save(
				Mockito.any(Pessoa.class))).thenReturn(this.pessoa);
		Mockito.when(this.repository.findByCpf(
				Mockito.any(String.class))).thenReturn(null);
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
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		Pessoa response = this.pessoaServiceImpl.create(request);

		assertNotNull(response);
		assertNotNull(response.getId());
	}
	
	@Test()
	public void createPessoaNullTest() throws CustomException {

		this.pessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.create(null);
		});
	}
	
	@Test()
	public void createPessoaJaCadastradaTest() throws CustomException {
		
		Mockito.when(this.repository.findByCpf(
				Mockito.any(String.class))).thenReturn(this.pessoa);

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createPessoaSemNomeTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getString())
				.build();
		
		this.pessoaServiceImpl.init();

		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createPessoaSemDtNascimentoTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();

		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createPessoaSemCPFTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.build();
		
		this.pessoaServiceImpl.init();

		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createPessoaComEmailInvalidoTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getString())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getString())
				.build();
		
		this.pessoaServiceImpl.init();

		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createPessoaComCPFInvalidoTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getString())
				.build();
		
		this.pessoaServiceImpl.init();

		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createDtInvalidaTest() throws CustomException {
		
		Calendar dataInvalida = Calendar.getInstance();
		dataInvalida.set(1850, Calendar.JANUARY, 1);

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(dataInvalida.getTime())
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createDtInvalidaFuturaTest() throws CustomException {
		
		Calendar dataInvalida = Calendar.getInstance();
		dataInvalida.set(2050, Calendar.JANUARY, 1);

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(dataInvalida.getTime())
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.create(request);
		});
	}
	
	//update
	@Test
	public void updateTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.update(request);
	}
	
	@Test()
	public void updatePessoaNullTest() throws CustomException {
		
		this.pessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.update(null);
		});
	}
	
	@Test()
	public void updatePessoaJaCadastradaTest() throws CustomException {
		
		Mockito.when(this.repository.findByCpf(
				Mockito.any(String.class))).thenReturn(this.pessoa);

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updatePessoaNotFoundTest() throws CustomException {
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updatePessoaSemNomeTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();

		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updatePessoaSemDtNascimentoTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updatePessoaSemCPFTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.build();
		
		this.pessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updatePessoaComEmailInvalidoTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getString())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		this.pessoaServiceImpl.init();

		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updatePessoaComCpfInvalidoTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getString())
				.build();
		
		this.pessoaServiceImpl.init();

		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.update(request);
		});
	}
	
	//retrieve
	@Test
	public void retrieveTest() throws CustomException {
		
		Pessoa response = this.pessoaServiceImpl.retrieve(EntityGenericUtil.getLong());

		assertNotNull(response);
		assertNotNull(response.getId());
		assertEquals(this.pessoa, response);
	}
	
	@Test()
	public void retrieveNotFoundTest() throws CustomException {
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);
		
		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.retrieve(EntityGenericUtil.getLong());
		});
	}
	
	@Test()
	public void retrieveComNullTest() throws CustomException {
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenReturn(null);
		
		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.retrieve(null);
		});
	}
	
	//delete
	@Test
	public void deleteTest() throws CustomException {
	
		this.pessoaServiceImpl.init();
		this.pessoaServiceImpl.delete(this.pessoa);
	}
	
	@Test()
	public void deleteNotFoundTest() throws CustomException {
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);
		
		this.pessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.delete(this.pessoa);
		});
	}
	
	@Test()
	public void deletePessoamNullTest() throws CustomException {
		
		this.pessoaServiceImpl.init();
		
		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.delete(null);
		});
	}
	
	@Test()
	public void deletePessoaSemIdTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.genero(EntityGenericUtil.getGenero())
				.email(EntityGenericUtil.getEmail())
				.dtNascimento(this.dtNascimento)
				.naturalidade(EntityGenericUtil.getString())
				.nacionalidade(EntityGenericUtil.getString())
				.cpf(EntityGenericUtil.getCPF())
				.build();
		
		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.delete(request);
		});
	}
	
	//list
	@Test
	public void listTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listComPessoaNullTest() throws CustomException {
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(null, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test()
	public void listComPageableNullTest() throws CustomException {
		
		Pessoa request = Pessoa.builder()
				.build();

		assertThrows(CustomException.class, () -> {
			this.pessoaServiceImpl.list(request, null);
		});
	}
	
	@Test
	public void listPorIdTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.id(EntityGenericUtil.getLong())
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorNomeTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nome(EntityGenericUtil.getString())
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorEmailTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.email(EntityGenericUtil.getString())
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	
	@Test
	public void listPorDtNascimentoTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.dtNascimento(this.dtNascimento)
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	
	@Test
	public void listPorNaturalidadeTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.naturalidade(EntityGenericUtil.getString())
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorNacionalidadeTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.nacionalidade(EntityGenericUtil.getString())
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorCPFTest() throws CustomException {

		Pessoa request = Pessoa.builder()
				.cpf(EntityGenericUtil.getString())
				.build();
		
		Page<Pessoa> response = this.pessoaServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
}
