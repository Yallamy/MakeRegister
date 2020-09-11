package br.com.registerApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.registerApi.entity.HistoricoPessoa;

/**
 * Repositório da entidade {@link HistoricoPessoa}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@Repository
public interface HistoricoPessoaRepository extends JpaRepository<HistoricoPessoa, Long> {

}
