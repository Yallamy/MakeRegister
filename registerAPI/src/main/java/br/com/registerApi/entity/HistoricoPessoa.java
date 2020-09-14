package br.com.registerApi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import br.com.registerApi.util.Mensagem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Classe que representa a entidade HistoricoPessoa.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@Table(name = "HistoricoPessoa")
@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
public class HistoricoPessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_HistPessoa")
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "dtAlteracao", nullable = false)
	@NotNull(message = Mensagem.DT_ALTERACAO_REQUIRED)
	private Date dtAlteracao;
	
	@Column(name = "descAlteracao", nullable = false)
	@NotEmpty(message = Mensagem.DESC_ALTERACAO_REQUIRED)
	private String descAlteracao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idPessoa", nullable = false)
	@NotNull(message = Mensagem.PESSOA_REQUIRED)
	private Pessoa pessoa;
}
