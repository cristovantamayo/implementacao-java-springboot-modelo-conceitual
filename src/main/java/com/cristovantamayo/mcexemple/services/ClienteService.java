package com.cristovantamayo.mcexemple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristovantamayo.mcexemple.domain.Cliente;
import com.cristovantamayo.mcexemple.repositories.ClienteRepository;
import com.cristovantamayo.mcexemple.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		if(obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return obj.get();
	}
}
