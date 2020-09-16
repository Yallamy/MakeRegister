package br.com.registerApi.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import br.com.registerApi.enums.GeneroEnum;
import br.com.registerApi.util.Mensagem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Classe que representa a entidade pessoa.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@Table(name = "Pessoa")
@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_pessoa")
	@Column(name = "id", nullable = false)
	@Expose
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 100)
	@NotEmpty(message = Mensagem.NAME_REQUIRED)
	@Expose
	private String nome;
	
	@Column(name = "genero")
	@Enumerated(EnumType.STRING)
	@Expose
	private GeneroEnum genero;
	
	@Column(name = "email", length = 100)
	@Expose
	private String email;
	
	@Column(name = "dtNascimento", nullable = false)
	@NotNull(message = Mensagem.DATE_REQUIRED)
	@Expose
	private Date dtNascimento;
	
	@Column(name = "naturalidade", length = 100)
	@Expose
	private String naturalidade;
	
	@Column(name = "nacionalidade", length = 100)
	@Expose
	private String nacionalidade;
	
	@Column(name = "cpf", nullable = false, unique = true) 
	@NotEmpty(message = Mensagem.CPF_REQUIRED)
	@Expose
	private String cpf;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pessoa")
	private List<HistoricoPessoa> historico;
	
}
