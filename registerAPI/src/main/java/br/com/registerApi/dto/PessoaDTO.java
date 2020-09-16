package br.com.registerApi.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.registerApi.util.Constantes;
import br.com.registerApi.util.Mensagem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que encapsula os dados da pessoa para transferência dos objetos pelo REST. 
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = Constantes.PESSOA)
public class PessoaDTO implements Serializable {
	
	private static final long serialVersionUID = 2824483527406999645L;

	@ApiModelProperty(value = Constantes.PESSOA_NOME, position = 1)
	@NotEmpty(message = Mensagem.NAME_REQUIRED)
	private String nome;
	
	@ApiModelProperty(value = Constantes.PESSOA_GENERO, position = 2)
	private String genero;
	
	@ApiModelProperty(value = Constantes.PESSOA_EMAIL, position = 3)
	private String email;
	
	@ApiModelProperty(value = Constantes.PESSOA_DATA_NASCIMENTO, position = 4)
	@NotNull(message = Mensagem.DATE_REQUIRED)
	private Date dtNascimento;
	
	@ApiModelProperty(value = Constantes.PESSOA_NATURALIDADE, position = 5)
	private String naturalidade;
	
	@ApiModelProperty(value = Constantes.PESSOA_NACIONALIDADE, position = 6)
	private String nacionalidade;
	
	@ApiModelProperty(value = Constantes.PESSOA_CPF, position = 7)
	@NotEmpty(message = Mensagem.CPF_REQUIRED)
	private String cpf;
}
