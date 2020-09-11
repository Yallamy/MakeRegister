package br.com.registerApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.registerApi.entity.Pessoa;

/**
 * Repositório da entidade {@link Pessoa}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	/**
	 * Método que recupera uma pessoa por CPF
	 * @param pessoa
	 * @return Pessoa
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	public Pessoa findByCpf(Pessoa pessoa);

}
