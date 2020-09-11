package br.com.registerApi.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.registerApi.dto.PessoaDTO;
import br.com.registerApi.dto.PessoaRequestDTO;
import br.com.registerApi.dto.PessoaResponseDTO;
import br.com.registerApi.entity.Pessoa;
import br.com.registerApi.exception.CustomException;
import br.com.registerApi.service.PessoaService;
import br.com.registerApi.util.Constantes;
import br.com.registerApi.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Classe que disponibiliza os serviços para manter a pessoa.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 5 de set de 2020
 */
@RestController
@RequestMapping(value=Constantes.PATH_PESSOA, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = Constantes.PATH_PESSOA, produces = MediaType.APPLICATION_JSON_VALUE, tags = { Constantes.TAG_PESSOA })
public class PessoaResource {
	
	@Autowired
	private PessoaService service;
	
	/**
	 * Método REST que cria uma pessoa.
	 * @param pessoaDTO - pessoa
	 * @return ResponseEntity<?> - pessoa criada ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws CustomException 
	 * @since 11 de set de 2020
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiOperation(value = Constantes.CREATE_PESSOA, 
		notes = Constantes.CREATE_PESSOA_NOTES, response = PessoaResponseDTO.class)
	public @ResponseBody ResponseEntity<?> create(@Valid @RequestBody PessoaDTO pessoaDTO) throws CustomException {
		
		Pessoa pessoa = Util.convertModelMapper(pessoaDTO, Pessoa.class);
		pessoa = this.service.create(pessoa);
		PessoaResponseDTO response = Util.convertModelMapper(pessoa, PessoaResponseDTO.class);
		
		return new ResponseEntity<PessoaResponseDTO>(response, HttpStatus.OK);
	}
	
	/**
	 * Método REST que altera uma pessoa.
	 * @param id - id da pessoa
	 * @param pessoaDTO - pessoa
	 * @return ResponseEntity<?> - pessoa alterada ou código de erro HTTP
	 * @throws CustomException 
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = Constantes.UPDATE_PESSOA, 
		notes = Constantes.UPDATE_PESSOA_NOTES, response = PessoaResponseDTO.class)
	public @ResponseBody ResponseEntity<?> update(
			@PathVariable("id") Long id,
			@Valid @RequestBody PessoaDTO pessoaDTO) throws CustomException {
		
		Pessoa pessoa = Util.convertModelMapper(pessoaDTO, Pessoa.class);
		pessoa.setId(id);
		
		this.service.update(pessoa);
		PessoaResponseDTO response = Util.convertModelMapper(pessoa, PessoaResponseDTO.class);
		
		return new ResponseEntity<PessoaResponseDTO>(response, HttpStatus.OK);
	}
	
	/**
	 * Método REST que recupera uma pessoa.
	 * @param id - id da pessoa
	 * @return ResponseEntity<?> - pessoa recuperada ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws CustomException 
	 * @since 11 de set de 2020
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = Constantes.RETRIEVE_PESSOA, 
		notes = Constantes.RETRIEVE_PESSOA_NOTES, response = PessoaResponseDTO.class)
	public @ResponseBody ResponseEntity<?> retrieve(
			@PathVariable("id") Long id) throws CustomException {
		
		Pessoa pessoa = this.service.retrieve(id);
		PessoaResponseDTO response = Util.convertModelMapper(pessoa, PessoaResponseDTO.class);
		
		return new ResponseEntity<PessoaResponseDTO>(response, HttpStatus.OK);
	}
	
	/**
	 * Método REST que deleta uma pessoa.
	 * @param id - id da pessoa
	 * @return ResponseEntity<?> - pessoa deletada ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws CustomException 
	 * @since 11 de set de 2020
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = Constantes.DELETE_PESSOA, 
		notes = Constantes.DELETE_PESSOA_NOTES)
	public @ResponseBody ResponseEntity<?> delete(@PathVariable("id") Long id) throws CustomException {
		
		Pessoa pessoa = Pessoa.builder().id(id).build();
		this.service.delete(pessoa);
		
		return new ResponseEntity<PessoaResponseDTO>(HttpStatus.OK);
	}
	
	/**
	 * Método REST que lista as pessoas de acordo com os filtros informados.
	 * @param pessoaDTO - filtros da pessoa
	 * @return ResponseEntity<?> - lista de pessoas ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws CustomException 
	 * @since 11 de set de 2020
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = Constantes.LIST_PESSOA, 
		notes = Constantes.LIST_PESSOA_NOTES, response = PessoaResponseDTO.class)
	public @ResponseBody ResponseEntity<Page<?>> list(
			@PageableDefault(value = 30, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
			@RequestBody PessoaRequestDTO pessoaDTO) throws CustomException {
		
		Pessoa pessoa = Util.convertModelMapper(pessoaDTO, Pessoa.class);
		Page<Pessoa> response = this.service.list(pessoa, pageable);
		
		return ResponseEntity.ok(response);
	}

}
