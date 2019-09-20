package com.cristovantamayo.mcexemple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristovantamayo.mcexemple.domain.Pedido;
import com.cristovantamayo.mcexemple.repositories.PedidoRepository;
import com.cristovantamayo.mcexemple.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		if(obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
		}
		return obj.get();
	}
}
