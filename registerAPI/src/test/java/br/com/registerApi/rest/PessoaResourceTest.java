package br.com.registerApi.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.registerApi.EntityGenericUtil;
import br.com.registerApi.RegisterAPI;
import br.com.registerApi.dto.PessoaDTO;
import br.com.registerApi.dto.PessoaRequestDTO;
import br.com.registerApi.util.Constantes;

/**
 * Classe de teste que representa os cenários de testes da classe {@link PessoaResource}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegisterAPI.class)
public class PessoaResourceTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private PessoaResource pessoaResource;
	
	@Autowired
	private Gson gson;
	
	private String urlBase = Constantes.PATH_PESSOA + "/";
	
	private PessoaDTO request;

	@Before
	public void setup() {
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(pessoaResource)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
		
		this.request = new PessoaDTO(EntityGenericUtil.getString(), 
				EntityGenericUtil.getGenero().getGenero(), EntityGenericUtil.getEmail(), 
				EntityGenericUtil.getDate(), EntityGenericUtil.getString(),
				EntityGenericUtil.getString(), EntityGenericUtil.getCPF());
		
		this.gson = new GsonBuilder()
				   .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
	}
	
	//create
	@Test
	public void createTest() throws Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void createPessoaSemNomeTest() throws Exception {

		this.request.setNome(null);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void createPessoaSemDtNascimentoTest() throws Exception {

		this.request.setDtNascimento(null);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	public void createPessoaSemCpfTest() throws Exception {

		this.request.setCpf(null);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void createPessoaCpfInvalidoTest() throws Exception {

		this.request.setCpf(EntityGenericUtil.getString());
		
		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void createPessoaEmailInvalidoTest() throws Exception {

		this.request.setEmail(EntityGenericUtil.getString());
		
		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	//update
	@Test
	public void updateTest() throws Exception {
		
		this.request.setCpf(EntityGenericUtil.getCPF());
		
		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		this.request.setNome(EntityGenericUtil.getString());

		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void updateNotFoundTest() throws Exception {
		
		this.request.setNome(EntityGenericUtil.getString());

		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 15)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void updatePessoaSemNomeTest() throws Exception {

		this.request.setNome(null);
		
		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void updatePessoaSemDtNascimentoTest() throws Exception {

		this.request.setDtNascimento(null);
		
		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void updatePessoaSemCpfTest() throws Exception {

		this.request.setCpf(null);
		
		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void updatePessoaCpfInvalidoTest() throws Exception {

		this.request.setCpf(EntityGenericUtil.getString());
		
		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void updatePessoaEmailInvalidoTest() throws Exception {

		this.request.setEmail(EntityGenericUtil.getString());
		
		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	//retrieve
	@Test
	public void retrieveTest() throws Exception {
		
		this.request.setCpf(EntityGenericUtil.getCPF());
		
		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
        		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase + 1)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void retrieveNotFoundTest() throws Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase + 15)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	//list
	@Test
	public void listTest() throws Exception {
		
		PessoaRequestDTO filtro = new PessoaRequestDTO(
				EntityGenericUtil.getLong(), EntityGenericUtil.getString(), 
				EntityGenericUtil.getString(), EntityGenericUtil.getString(), 
				EntityGenericUtil.getDate(), EntityGenericUtil.getString(),
				EntityGenericUtil.getString(), EntityGenericUtil.getString());

		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void listPorIdTest() throws Exception {

		PessoaRequestDTO filtro = PessoaRequestDTO.builder().id(1l).build();
		
		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void listPorNomeTest() throws Exception {

		PessoaRequestDTO filtro = PessoaRequestDTO.builder()
				.nome(request.getNome()).build();
		
		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void listPorGeneroTest() throws Exception {

		PessoaRequestDTO filtro = PessoaRequestDTO.builder()
				.genero(request.getGenero()).build();
		
		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void listPorEmailTest() throws Exception {

		PessoaRequestDTO filtro = PessoaRequestDTO.builder()
				.email(request.getEmail()).build();
		
		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void listPorDtNascimentoTest() throws Exception {

		PessoaRequestDTO filtro = PessoaRequestDTO.builder()
				.dtNascimento(request.getDtNascimento()).build();
		
		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void listPorNaturalidadeTest() throws Exception {

		PessoaRequestDTO filtro = PessoaRequestDTO.builder()
				.naturalidade(request.getNaturalidade()).build();
		
		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void listPorNacionalidadeTest() throws Exception {

		PessoaRequestDTO filtro = PessoaRequestDTO.builder()
				.nacionalidade(request.getNacionalidade()).build();
		
		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void listPorCpfTest() throws Exception {

		PessoaRequestDTO filtro = PessoaRequestDTO.builder()
				.cpf(request.getCpf()).build();
		
		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	//delete
	@Test
	public void deleteTest() throws Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete(this.urlBase + 1)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deletePessoaNullTest() throws Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete(this.urlBase + 15)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
