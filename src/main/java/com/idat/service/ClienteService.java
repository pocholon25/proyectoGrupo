package com.idat.service;

import com.idat.model.Cliente;
import com.idat.model.ClienteUpdate;
import com.idat.model.LoginMessage;
import com.idat.model.LoginRequest;

public interface ClienteService extends ICRUD<Cliente>{
	
	LoginMessage loginUsuario(LoginRequest loginRequest);
	
	Cliente obtenerClientePorId(Long id);

    Cliente actualizarCliente(Long id, ClienteUpdate clienteUpdate);

}
