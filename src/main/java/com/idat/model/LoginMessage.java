package com.idat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginMessage {
	
	
	private String message;
	private boolean success;
	private String nombre;
	private String email;
	private String celular;
	private String password;
	private int idcliente;
	
	
	
	
}
	
	
	