package br.com.registerApi.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.registerApi.entity.HistoricoPessoa;
import br.com.registerApi.entity.Pessoa;
import br.com.registerApi.exception.CustomException;
import br.com.registerApi.exception.ServiceWsValidacao;
import br.com.registerApi.repository.PessoaRepository;
import br.com.registerApi.service.HistoricoPessoaService;
import br.com.registerApi.service.PessoaService;
import br.com.registerApi.util.Util;
import br.com.twsoftware.alfred.cpf.CPF;
import br.com.twsoftware.alfred.email.Email;
import br.com.twsoftware.alfred.object.Objeto;

/**
 * Classe que implementa os métodos do serviço para manter uma pessoa.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@Service
public class PessoaServiceImpl implements PessoaService {
	
	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	private HistoricoPessoaService historicoPessoaService;
	
	@Autowired
	private Validator validator;
	
	@PostConstruct
	public void init() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.registerApi.service.PessoaService#create(br.com.registerApi.entity.Pessoa)
	 */
	@Override
	public Pessoa create(Pessoa pessoa) throws CustomException {
		
		Set<ConstraintViolation<Pessoa>> violations = validator.validate(pessoa);
		
		if(Objeto.isBlank(pessoa) || violations.size() > BigDecimal.ZERO.intValue()) {
			throw new CustomException(ServiceWsValidacao.BAD_REQUEST);
		}
		
		//validações de regras de negócio
		validarRegrasNegocio(pessoa);
		
		Pessoa pessoaCadastrada = repository.findByCpf(pessoa); 
		
		if(Objeto.notBlank(pessoaCadastrada)) {
			throw new CustomException(ServiceWsValidacao.CPF_JA_CADASTRADO);
		}
		
		//salvando
		Pessoa pessoaSalva = repository.save(pessoa);
		
		HistoricoPessoa historico = HistoricoPessoa.builder()
		.dtAlteracao(Calendar.getInstance().getTime())
		.descAlteracao("Criação da pessoa")
		.pessoa(pessoaSalva)
		.build();
		
		historicoPessoaService.create(historico);
		
		return pessoaSalva;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.registerApi.service.PessoaService#update(br.com.registerApi.entity.Pessoa)
	 */
	@Override
	public void update(Pessoa pessoa) throws CustomException {
		
		Set<ConstraintViolation<Pessoa>> violations = validator.validate(pessoa);
		
		if(Objeto.isBlank(pessoa) || violations.size() > BigDecimal.ZERO.intValue()
				|| Objeto.isBlank(pessoa.getId())) {
			
			throw new CustomException(ServiceWsValidacao.BAD_REQUEST);
		}
		
		//validações de regras de negócio
		validarRegrasNegocio(pessoa);
		
		try {
			
			Pessoa pessoaCadastrada = repository.findById(pessoa.getId()).get();
			
			//se houve mudança de CPF, verificar se o CPF já está cadastrado
			if(Objeto.notBlank(pessoaCadastrada) && !pessoa.getCpf().equals(pessoaCadastrada.getCpf())) {
				
				pessoaCadastrada = repository.findByCpf(pessoa); 
				
				if(Objeto.notBlank(pessoaCadastrada)) {
					throw new CustomException(ServiceWsValidacao.CPF_JA_CADASTRADO);
				}
			}
			
		} catch(NoSuchElementException ex) {
			throw new CustomException(ServiceWsValidacao.PESSOA_NAO_ENCONTRADA);
		}
		
		//salvando
		repository.save(pessoa);
		
		HistoricoPessoa historico = HistoricoPessoa.builder()
		.dtAlteracao(Calendar.getInstance().getTime())
		.descAlteracao("Alteração da pessoa")
		.pessoa(pessoa)
		.build();
		
		historicoPessoaService.create(historico);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.registerApi.service.PessoaService#retrieve(java.lang.Long)
	 */
	@Override
	public Pessoa retrieve(Long id) throws CustomException {
		
		if(Objeto.isBlank(id)) {
			throw new CustomException(ServiceWsValidacao.BAD_REQUEST);
		}
		
		try {
			
			return repository.findById(id).get();
			
		} catch(NoSuchElementException ex) {
			throw new CustomException(ServiceWsValidacao.PESSOA_NAO_ENCONTRADA);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.registerApi.service.PessoaService#delete(br.com.registerApi.entity.Pessoa)
	 */
	@Override
	public void delete(Pessoa pessoa) throws CustomException {
		
		if(Objeto.isBlank(pessoa) || Objeto.isBlank(pessoa.getId())) {
			throw new CustomException(ServiceWsValidacao.BAD_REQUEST);
		}
		
		try {
			
			Pessoa pessoaSalva = repository.findById(pessoa.getId()).get(); 
			repository.delete(pessoaSalva);

		} catch(NoSuchElementException ex) {
			throw new CustomException(ServiceWsValidacao.PESSOA_NAO_ENCONTRADA);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.registerApi.service.PessoaService#list(br.com.registerApi.entity.Pessoa, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Pessoa> list(Pessoa pessoa, Pageable pageable) throws CustomException {
		
		if(Objeto.isBlank(pageable)) {
			throw new CustomException(ServiceWsValidacao.BAD_REQUEST);
		}
		
		if(Objeto.isBlank(pessoa)) {
			pessoa = Pessoa.builder().build();
		}

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<Pessoa> example = Example.of(pessoa, matcher);
		
		return repository.findAll(example, pageable); 
	}
	
	/**
	 * Método que valida as regras de negócio.
	 * @param pessoa - objeto a ser validado
	 * @throws CustomException - lança uma exception caso a regra de negócio 
	 * não esteja sendo atendida.
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	private void validarRegrasNegocio(Pessoa pessoa) throws CustomException {
		
		if(!CPF.isValido(pessoa.getCpf())) {
			throw new CustomException(ServiceWsValidacao.CPF_INVALIDO);
		}
		
		if(Objeto.notBlank(pessoa.getEmail()) && !Email.isValido(pessoa.getEmail())) {
			throw new CustomException(ServiceWsValidacao.EMAIL_INVALIDO);
		}
		
		if(Calendar.getInstance().getTime().before(pessoa.getDtNascimento()) 
				|| Util.getDataInicial().after(pessoa.getDtNascimento())) {
			
			throw new CustomException(ServiceWsValidacao.DATA_INVALIDA);
		}
	}
}
