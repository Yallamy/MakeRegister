package br.com.registerApi.util;

import java.util.Calendar;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import br.com.twsoftware.alfred.object.Objeto;

/**
 * Classe que apresenta métodos úteis para todo o projeto.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2020
 */
public class Util {
	
	/**
	 * Método que converte um tipo de classe para outro tipo de classe.
	 * @param source
	 * @param typeDestination
	 * @return Class<E> tipo de classe de destino
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	public static <E, T> E convertModelMapper(T source, Class<E> typeDestination) {
		
        E model = null;
        
        if (Objeto.notBlank(source) && Objeto.notBlank(typeDestination)) {

             ModelMapper modelMapper = new ModelMapper();

             modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
             model = modelMapper.map(source, typeDestination);
        }

        return model;
	}
	
	/**
	 * Método que retorna a data inicial de validade de data de nascimento do sistema.
	 * @return Calendar
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 11 de set de 2020
	 */
	public static Calendar getDataInicial() {
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.set(Calendar.getInstance().get(Calendar.YEAR) - Constantes.PERIODO_VALIDADE, 
				Calendar.JANUARY, 1);
		
		return dataInicial;
	}

}
