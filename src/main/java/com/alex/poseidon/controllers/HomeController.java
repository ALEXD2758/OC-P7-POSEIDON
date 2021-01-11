package com.alex.poseidon.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	/**
	 * Render the view home
	 *
	 * @param model Model Interface
	 * @return a string to the address "home", returning the associated view
	 */
	@GetMapping("/")
	public String home(Model model)	{
		return "home";
	}

	/**
	 * Render the view bidList/list
	 *
	 * @param model Model Interface
	 * @return a string to the address "/bidList/list", returning the associated view
	 */
	@GetMapping("/admin/home")
	public String adminHome(Model model) {
		return "redirect:/bidList/list";
	}
}
