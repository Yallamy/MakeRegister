package br.com.registerApi.service.impl;

import java.math.BigDecimal;
import java.util.List;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.registerApi.entity.HistoricoPessoa;
import br.com.registerApi.entity.Pessoa;
import br.com.registerApi.exception.CustomException;
import br.com.registerApi.exception.ServiceWsValidacao;
import br.com.registerApi.repository.HistoricoPessoaRepository;
import br.com.registerApi.service.HistoricoPessoaService;
import br.com.twsoftware.alfred.object.Objeto;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe que implementa os métodos do serviço para manter um histórico de uma pessoa.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
@Service
@Slf4j
public class HistoricoPessoaServiceImpl implements HistoricoPessoaService {

	@Autowired
	private HistoricoPessoaRepository repository;
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private Validator validator;
	
	@PostConstruct
	public void init() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
        
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.registerApi.service.HistoricoPessoaService#create(br.com.registerApi.entity.HistoricoPessoa)
	 */
	@Override
	public HistoricoPessoa create(HistoricoPessoa historico) throws CustomException {

		if(Objeto.isBlank(historico)) {
			throw new CustomException(ServiceWsValidacao.BAD_REQUEST);
		}
		
		Set<ConstraintViolation<HistoricoPessoa>> violations = validator.validate(historico);
		
		if(violations.size() > BigDecimal.ZERO.intValue()) {
			throw new CustomException(ServiceWsValidacao.BAD_REQUEST);
		}
		
		return repository.save(historico);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.registerApi.service.HistoricoPessoaService#retrieve(java.lang.Long)
	 */
	@Override
	public HistoricoPessoa retrieve(Long id) throws CustomException {
		
		if(Objeto.isBlank(id)) {
			throw new CustomException(ServiceWsValidacao.BAD_REQUEST);
		}

		try {

			return repository.findById(id).get();

		} catch(NoSuchElementException ex) {
			throw new CustomException(ServiceWsValidacao.HISTORICO_NAO_ENCONTRADO);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.registerApi.service.HistoricoPessoaService#retrieve(br.com.registerApi.entity.Pessoa)
	 */
	@Override
	public List<HistoricoPessoa> retrieve(Pessoa pessoa) throws CustomException {
		
		if(Objeto.isBlank(pessoa) || Objeto.isBlank(pessoa.getId())) {
			throw new CustomException(ServiceWsValidacao.BAD_REQUEST);
		}

		List<HistoricoPessoa> historicos = repository.findAllByPessoa(pessoa);
		
		if(Objeto.isBlank(historicos)) {
			throw new CustomException(ServiceWsValidacao.HISTORICO_NAO_ENCONTRADO);
		} 
		
		return historicos;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.registerApi.service.HistoricoPessoaService#delete(br.com.registerApi.entity.Pessoa)
	 */
	@Override
	public void delete(Pessoa pessoa) throws CustomException {
		
		if(Objeto.isBlank(pessoa) || Objeto.isBlank(pessoa.getId())) {
			throw new CustomException(ServiceWsValidacao.BAD_REQUEST);
		}
		
		log.info("Deletando o historico da pessoa de id: " + pessoa.getId());
		
		List<HistoricoPessoa> historico = repository.findAllByPessoa(pessoa);
		
		if(Objeto.notBlank(historico)) {
			
			Long registrosDeletados = repository.deleteAllByPessoa(pessoa);
			
			log.info("Quantidade de registros deletados: " + registrosDeletados);
			
			historico.forEach(hist -> {
				log.info("historico: " + gson.toJson(hist));
			});
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.registerApi.service.HistoricoPessoaService#list(br.com.registerApi.entity.HistoricoPessoa, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<HistoricoPessoa> list(HistoricoPessoa historico, Pageable pageable) throws CustomException {

		if(Objeto.isBlank(pageable)) {
			throw new CustomException(ServiceWsValidacao.BAD_REQUEST);
		}
		
		if(Objeto.isBlank(historico)) {
			historico = HistoricoPessoa.builder().build();
		}

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<HistoricoPessoa> example = Example.of(historico, matcher);
		
		return repository.findAll(example, pageable);
	}
}
