package com.lagom.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invitado")
public class InvitadoController {
	
	@GetMapping("/")
	public String index(){
		return "index";
	}
}
