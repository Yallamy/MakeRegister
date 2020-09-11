package br.com.registerApi.dto;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.registerApi.EntityGenericUtil;
import junit.framework.TestCase;

/**
 * Classe de teste que representa os cenários de testes da classe {@link PessoaRequestDTO}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@RunWith(SpringRunner.class)
public class PessoaRequestDTOTest {
	
	private Long id;
	
	private String nome;
	
	private String sexo;
	
	private String email;
	
	private Date dtNascimento;
	
	private String naturalidade;
	
	private String nacionalidade;
	
	private String cpf;

	@Before
	public void setup() {

		this.id = EntityGenericUtil.getLong();
		this.nome = EntityGenericUtil.getString();
		this.sexo = EntityGenericUtil.getString();
		this.email = EntityGenericUtil.getString();
		this.dtNascimento = EntityGenericUtil.getDate();
		this.naturalidade = EntityGenericUtil.getString();
		this.nacionalidade = EntityGenericUtil.getString();
		this.cpf = EntityGenericUtil.getString();
	}

	@Test
	public void getInstanceTest() {

		PessoaRequestDTO request = new PessoaRequestDTO(this.id, this.nome, this.sexo, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		TestCase.assertNotNull(request);
		TestCase.assertEquals(this.id, request.getId());
		TestCase.assertEquals(this.nome, request.getNome());
		TestCase.assertEquals(this.sexo, request.getSexo());
		TestCase.assertEquals(this.email, request.getEmail());
		TestCase.assertEquals(this.dtNascimento, request.getDtNascimento());
		TestCase.assertEquals(this.naturalidade, request.getNaturalidade());
		TestCase.assertEquals(this.nacionalidade, request.getNacionalidade());
		TestCase.assertEquals(this.cpf, request.getCpf());
	}
	
	@Test
	public void getInstanceBuilderTest() {

		PessoaRequestDTO request = PessoaRequestDTO.builder()
				.id(this.id)
				.nome(this.nome)
				.sexo(this.sexo)
				.email(this.email)
				.dtNascimento(this.dtNascimento)
				.naturalidade(this.naturalidade)
				.nacionalidade(this.nacionalidade)
				.cpf(this.cpf)
				.build();

		TestCase.assertNotNull(request);
		TestCase.assertEquals(this.id, request.getId());
		TestCase.assertEquals(this.nome, request.getNome());
		TestCase.assertEquals(this.sexo, request.getSexo());
		TestCase.assertEquals(this.email, request.getEmail());
		TestCase.assertEquals(this.dtNascimento, request.getDtNascimento());
		TestCase.assertEquals(this.naturalidade, request.getNaturalidade());
		TestCase.assertEquals(this.nacionalidade, request.getNacionalidade());
		TestCase.assertEquals(this.cpf, request.getCpf());
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
		request.setSexo(this.sexo);
		request.setEmail(this.email);
		request.setDtNascimento(this.dtNascimento);
		request.setNaturalidade(this.naturalidade);
		request.setNacionalidade(this.nacionalidade);
		request.setCpf(this.cpf);

		TestCase.assertEquals(this.id, request.getId());
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

		PessoaRequestDTO request = new PessoaRequestDTO(this.id, this.nome, this.sexo, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);
		
		PessoaRequestDTO request2 = new PessoaRequestDTO(this.id, this.nome, this.sexo, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		TestCase.assertNotNull(request);
		TestCase.assertNotNull(request2);
		TestCase.assertEquals(request, request2);
	}
	
	@Test
	public void getHashCodeTest() {

		PessoaRequestDTO request = new PessoaRequestDTO(this.id, this.nome, this.sexo, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);
		
		PessoaRequestDTO request2 = new PessoaRequestDTO(this.id, this.nome, this.sexo, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		TestCase.assertNotNull(request);
		TestCase.assertNotNull(request2);
		TestCase.assertEquals(request.hashCode(), request2.hashCode());
	}
	
	@Test
	public void getToStringTest() {

		PessoaRequestDTO request = new PessoaRequestDTO(this.id, this.nome, this.sexo, this.email, 
				this.dtNascimento, this.naturalidade, this.nacionalidade, this.cpf);

		TestCase.assertNotNull(request);
		TestCase.assertNotNull(request.toString());
	}

}
