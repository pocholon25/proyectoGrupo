package com.idat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idat.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long>{
	
	Optional<Cliente> findByEmailAndPassword(String email, String password);

    Optional<Cliente> findByEmail(String email);

}
