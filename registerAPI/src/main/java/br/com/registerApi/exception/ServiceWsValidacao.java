package br.com.registerApi.exception;

import org.springframework.http.HttpStatus;

/**
 * Classe que implementa as validações genéricas do sistema.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
public enum ServiceWsValidacao implements GenericValidacao{

	PESSOA_NAO_ENCONTRADA("Pessoa não encontrada", HttpStatus.NOT_FOUND),
	BAD_REQUEST("Campos obrigatórios não informados", HttpStatus.BAD_REQUEST),
	CPF_INVALIDO("O CPF informado é inválido", HttpStatus.BAD_REQUEST),
	EMAIL_INVALIDO("O Email informado é inválido", HttpStatus.BAD_REQUEST),
	DATA_INVALIDA("A data informada é inválida", HttpStatus.BAD_REQUEST),
	CPF_JA_CADASTRADO("O CPF  informado já está cadastrado", HttpStatus.BAD_REQUEST);
	
	private String codigoErro;
	
	private HttpStatus httpStatus;

	/**
	 * Construtor privado da classe.
	 * @param codigoErro
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	private ServiceWsValidacao(String codigoErro, HttpStatus httpStatus) {
		this.codigoErro = codigoErro;
		this.httpStatus = httpStatus;
	}

	/**
	 * Método que retorna o código de erro referente a exception.
	 * @return String
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	@Override
	public String getCodigoErro() {
		return codigoErro;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
}
