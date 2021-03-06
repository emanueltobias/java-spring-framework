package com.emanueltobias.drinks.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emanueltobias.drinks.model.Cidade;
import com.emanueltobias.drinks.repository.Cidades;
import com.emanueltobias.drinks.service.excepition.CidadeJaCadastradaException;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private Cidades cidades;
	
	
	@Transactional
	public void salvar(Cidade cidade) {
			Optional<Cidade> cidadeExistente = cidades.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
			if(cidadeExistente.isPresent()) {
				throw new CidadeJaCadastradaException("Cidade já cadastrada para esse estado !");
			}
		
		cidades.save(cidade);

	}

}
