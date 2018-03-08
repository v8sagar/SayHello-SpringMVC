package com.app.controllers;

import java.util.Arrays;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	public HelloController() {
		System.out.println("in hello controller constr");
	}
	@PostConstruct
	public void init()
	{
		System.out.println("in init");
	}
	@PreDestroy
	public void myDestroy()
	{
		System.out.println("in dest");
	}
	//req handling method
	@RequestMapping("/hello")
	public String sayHello()
	{
		System.out.println("in say Hello");
		return "welcome";
	}
	//req handling method reting M&V
		@RequestMapping("/hello1")
		public ModelAndView sayHello1()
		{
			System.out.println("in say Hello1");
			return new ModelAndView("welcome", "abc", new Date());
			
		}
		//req handling method reting only logical view name
				@RequestMapping("/hello2")
				public String sayHello2(Model map)
				{
					System.out.println("in say Hello2 "+map);
					map.addAttribute("abc", new Date());
					map.addAttribute(Arrays.asList(10,20,30,40));
					System.out.println(map);
					return "welcome";
					
				}
	
	


}
