package com.example;

import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloController {
	@Autowired
	private AnotherRestWebService calculadora;
	
    @RequestMapping(method=RequestMethod.GET,path="/greetings")
    public String index(@RequestParam(value="name", defaultValue="World") String name) {
    	System.out.println(Calendar.getInstance().getTimeInMillis()+" Estoy en método greetings");
    	
        return "Greetings from "+name+"!"+" y mi estado actual es "+calculadora.getStatus();
    }
    
}