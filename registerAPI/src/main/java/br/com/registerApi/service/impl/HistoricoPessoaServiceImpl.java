package br.com.registerApi.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.registerApi.entity.HistoricoPessoa;
import br.com.registerApi.exception.CustomException;
import br.com.registerApi.service.HistoricoPessoaService;

/**
 * Classe que implementa os métodos do serviço para manter um histórico de uma pessoa.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
public class HistoricoPessoaServiceImpl implements HistoricoPessoaService {

	/*
	 * (non-Javadoc)
	 * @see br.com.registerApi.service.HistoricoPessoaService#create(br.com.registerApi.entity.HistoricoPessoa)
	 */
	@Override
	public HistoricoPessoa create(HistoricoPessoa historico) throws CustomException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.registerApi.service.HistoricoPessoaService#retrieve(java.lang.Long)
	 */
	@Override
	public HistoricoPessoa retrieve(Long id) throws CustomException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.registerApi.service.HistoricoPessoaService#list(br.com.registerApi.entity.HistoricoPessoa, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<HistoricoPessoa> list(HistoricoPessoa historico, Pageable pageable) throws CustomException {
		// TODO Auto-generated method stub
		return null;
	}
}
