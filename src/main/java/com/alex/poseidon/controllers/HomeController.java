package com.alex.poseidon.controllers;

import com.alex.poseidon.interfaces.HomeControllerInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController implements HomeControllerInterface {
	@Override
	@RequestMapping("/")
	public String home(Model model)
	{
		return "home";
	}

	@Override
	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/bidList/list";
	}


}
