package br.com.registerApi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.registerApi.entity.HistoricoPessoa;
import br.com.registerApi.entity.Pessoa;
import br.com.registerApi.exception.CustomException;

/**
 * Interface que define os métodos do serviço para manter o histórico de uma pessoa.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
public interface HistoricoPessoaService {
	
	/**
	 * Método que cria um historico.
	 * @param historico - historico a ser criado
	 * @return HistoricoPessoa - historico criado
	 * @throws CustomException 
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	public HistoricoPessoa create(HistoricoPessoa historico) throws CustomException;
	
	/**
	 * Método que recupera um historico
	 * @param id - id do historico
	 * @return HistoricoPessoa - historico recuperado
	 * @throws CustomException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	public HistoricoPessoa retrieve(Long id) throws CustomException;
	
	/**
	 * Método que recupera um historico a partir de uma Pessoa
	 * @param pessoa - id da pessoa
	 * @return List<HistoricoPessoa> - historico recuperado
	 * @throws CustomException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 13 de set de 2020
	 */
	public List<HistoricoPessoa> retrieve(Pessoa pessoa) throws CustomException;
	
	/**
	 * Método que deleta todo o historico da pessoa.
	 * @param pessoa - pessoa a ter o historico deletado
	 * @throws CustomException 
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	public void delete(Pessoa pessoa) throws CustomException;
	
	/**
	 * Método que lista os historicos com base nos filtros.
	 * @param historico - filtros a serem aplicados
	 * @param pageable - paginação
	 * @return Page<HistoricoPessoa> - lista de historicos
	 * @throws CustomException 
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	public Page<HistoricoPessoa> list(HistoricoPessoa historico, Pageable pageable) throws CustomException;
}
