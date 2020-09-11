package br.com.registerApi.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.registerApi.util.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que encapsula os dados da pessoa para transferência dos objetos pelo REST 
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = Constantes.PESSOA)
public class PessoaResponseDTO implements Serializable {

	private static final long serialVersionUID = 5657969328853750910L;

	@ApiModelProperty(value = Constantes.PESSOA_ID, position = 1)
	private Long id;

	@ApiModelProperty(value = Constantes.PESSOA_NOME, position = 2)
	private String nome;
	
	@ApiModelProperty(value = Constantes.PESSOA_SEXO, position = 3)
	private String sexo;
	
	@ApiModelProperty(value = Constantes.PESSOA_EMAIL, position = 4)
	private String email;
	
	@ApiModelProperty(value = Constantes.PESSOA_DATA_NASCIMENTO, position = 5)
	private Date dtNascimento;
	
	@ApiModelProperty(value = Constantes.PESSOA_NATURALIDADE, position = 6)
	private String naturalidade;
	
	@ApiModelProperty(value = Constantes.PESSOA_NACIONALIDADE, position = 7)
	private String nacionalidade;
	
	@ApiModelProperty(value = Constantes.PESSOA_CPF, position = 8)
	private String cpf;
}
