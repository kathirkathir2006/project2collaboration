package com.colloboration.chatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class initialController
{


	@RequestMapping(value = { "/", "/index" })
	public String showMessage() {
		System.out.println("in controller");

		return "index";
	}


}
