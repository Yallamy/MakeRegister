package br.com.registerApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.registerApi.entity.HistoricoPessoa;
import br.com.registerApi.entity.Pessoa;

/**
 * Repositório da entidade {@link HistoricoPessoa}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@Repository
public interface HistoricoPessoaRepository extends JpaRepository<HistoricoPessoa, Long> {
	
	/**
	 * Método que recupera todo o histórico de uma pessoa.
	 * @param pessoa - pessoa a ser recuperada o histórico
	 * @return List<HistoricoPessoa> - lista de histórico
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 13 de set de 2020
	 */
	public List<HistoricoPessoa> findAllByPessoa(Pessoa pessoa);
	
	/**
	 * Método que rdeleta todo o histórico de uma pessoa.
	 * @param pessoa - pessoa a ter o histórico deletado
	 * @return long - quantidade de registros deletados
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 13 de set de 2020
	 */
	public long deleteAllByPessoa(Pessoa pessoa);

}
