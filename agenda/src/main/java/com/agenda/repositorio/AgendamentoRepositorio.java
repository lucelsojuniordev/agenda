package com.agenda.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.agenda.modelo.Agendamento;

public interface AgendamentoRepositorio extends CrudRepository<Agendamento, String>{
	
	public Agendamento findByCodigo(long codigo);
	public Iterable<Agendamento> findAllByData(String data);

}
