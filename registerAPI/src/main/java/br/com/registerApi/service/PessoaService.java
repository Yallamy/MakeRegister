package br.com.registerApi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.registerApi.entity.Pessoa;
import br.com.registerApi.exception.CustomException;

/**
 * Interface que define os métodos do serviço para manter uma pessoa.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
public interface PessoaService {
	
	/**
	 * Método que cria uma pessoa.
	 * @param pessoa - pessoa a ser criada
	 * @return Pessoa - pessoa criada
	 * @throws CustomException 
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	public Pessoa create(Pessoa pessoa) throws CustomException;
	
	/**
	 * Método que atualiza uma pessoa.
	 * @param pessoa - pessoa a ser atualizado
	 * @throws CustomException 
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	public void update(Pessoa pessoa) throws CustomException;
	
	/**
	 * Método que recupera uma pessoa
	 * @param id - id da pessoa
	 * @return Pessoa - pessoa recuperada
	 * @throws CustomException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	public Pessoa retrieve(Long id) throws CustomException;
	
	/**
	 * Método que deleta uma pessoa.
	 * @param pessoa - pessoa a ser deletada
	 * @throws CustomException 
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	public void delete(Pessoa pessoa) throws CustomException;
	
	/**
	 * Método que lista as pessoas com base nos filtros.
	 * @param pessoa - filtros a serem aplicados
	 * @param pageable - paginação
	 * @return Page<Pessoa> - lista de pessoas
	 * @throws CustomException 
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	public Page<Pessoa> list(Pessoa pessoa, Pageable pageable) throws CustomException;

}
