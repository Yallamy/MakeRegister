package br.com.registerApi.util;

/**
 * Classe que posssui as constantes utilizadas no sistema.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
public class Constantes {

	//TAGS
	public static final String TAG_PESSOA = "pessoa";
	
	
	//PATHS
	public static final String PATH_RAIZ = "/api/register";

    public static final String PATH_SWAGGER = "/docs";
    
    //pessoa resource
    public static final String PATH_PESSOA = PATH_RAIZ + "/pessoa";
    
    
	//Swagger
    
    //serviços
    public static final String CREATE_PESSOA = "Criar uma pessoa ";
    
    public static final String CREATE_PESSOA_NOTES = "Cria uma pessoa.";
    
    public static final String UPDATE_PESSOA = "Atualizar uma pessoa ";
    
    public static final String UPDATE_PESSOA_NOTES = "Atualiza uma pessoa.";
    
    public static final String RETRIEVE_PESSOA = "Recuperar uma pessoa";
    
    public static final String RETRIEVE_PESSOA_NOTES = "Recupera uma pessoa.";
    
    public static final String DELETE_PESSOA = "Deletar uma pessoa";
    
    public static final String DELETE_PESSOA_NOTES = "Deleta uma pessoa.";
    
    public static final String LIST_PESSOA = "Listar uma pessoa";
    
    public static final String LIST_PESSOA_NOTES = "Lista uma pessoa.";
    
    
	//DTOs
	public static final String PESSOA = "Armazena os dados da pessoa";
	
	public static final String PESSOA_PERSIST = "Armazena os dados da pessoa";
	
	public static final String PESSOA_ID = "Armazena o id da pessoa";
	
	public static final String PESSOA_NOME = "Nome da pessoa";
	
	public static final String PESSOA_SEXO = "Sexo da pessoa";
	
	public static final String PESSOA_EMAIL = "E-mail da pessoa";
	
	public static final String PESSOA_DATA_NASCIMENTO = "Data de nascimento da pessoa";
	
	public static final String PESSOA_NATURALIDADE = "Naturalidade da pessoa";
	
	public static final String PESSOA_NACIONALIDADE = "Nacionalidade da pessoa";
	
	public static final String PESSOA_CPF = "CPF da pessoa";
	
	

	
	//outras constantes
	public static final String TAG_ERRO = "message";
	
	public static final int PERIODO_VALIDADE = 120;
	
	public static final String MSG_HIST_CRIACAO = "Criação da pessoa";
	
	public static final String MSG_HIST_ALTERACAO = "Alteração da pessoa: ";

}
