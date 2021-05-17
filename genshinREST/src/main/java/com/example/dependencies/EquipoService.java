package com.example.dependencies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* Este servicio se usará para incluir la funcionalidad que sea 
 * usada desde el BookRestController y el BookWebController
 */
@Service
public class EquipoService {

	@Autowired
	private EquipoRepository repository;

	public Optional<Equipo> findOne(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Equipo> findAll() {
		return repository.findAll();
	}

	public void save(Equipo equipo) {
		repository.save(equipo);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
	

}
