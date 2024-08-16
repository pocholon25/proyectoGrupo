package com.idat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/productos")
	public String productos() {
		return "producto";
	}

	@GetMapping("/producto")
	public String producto() {
		return "vista_producto";
	}

}
