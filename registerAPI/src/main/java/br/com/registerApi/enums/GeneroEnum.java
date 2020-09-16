package br.com.registerApi.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Classe que representa os tipos de gênero.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GeneroEnum {

	MASCULINO("Masculino"),
	
	FEMININO("Feminino"),
	
	OUTROS("Outros"),
	
	NAO_INFORMADO("Não informado");
	
	private String genero;
	
	/**
	 * Método que retorna o genero da pessoa
	 * @param genero
	 * @return GeneroEnum
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	public static GeneroEnum obterGenero(String genero) {
		
		return Arrays.asList(values()).stream().filter(
				tr -> tr.genero.equals(genero)).findFirst().orElse(NAO_INFORMADO);
	}
}
