package com.app.controllers;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.pojos.Customer;
import com.app.service.CustomerService;

@Controller
@RequestMapping("/cust")
public class CustomerController {
	@Autowired
	private CustomerService service;

	@PostConstruct
	public void init() {
		System.out.println("in init " + service);
	}

	@RequestMapping("/login")
	public String showLoginForm(Model map) {
		System.out.println("in show login form " + map);
		map.addAttribute(new Customer());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processLoginForm(Model map, Customer c1) {
		System.out.println("in process login form " + map);
		// System.out.println(c1);

		// invoke service layer's method
		Customer validCust = service.validateCustomer(c1.getEmail(), c1.getPass());
		if (validCust == null) {
			map.addAttribute("mesg", "Invalid Login ,pls Retry...");
			return "login";
		}
		// successful login
		map.addAttribute("mesg", "Successful Login");
		map.addAttribute("cust_dtls", validCust);
		return "success";
	}

	@RequestMapping("/register")
	public String showRegForm(Customer c) {
		System.out.println("in show reg form " + c);
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegForm(Model map, Customer c1,HttpSession hs) {
		System.out.println("in process reg form " +c1);
			// invoke service layer's method
		String mesg1 = service.registerCustomer(c1);
		if (mesg1.contains("Failed")) {
			map.addAttribute("mesg", mesg1);
			return "register";
		}
		// successful reg
		hs.setAttribute("mesg",mesg1.concat("Proceed to Login"));
		return "redirect:/cust/login";
	}

}
