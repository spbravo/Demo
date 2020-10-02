package com.example;


import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@RestController
public class AnotherRestWebService {
	@Autowired
	HashOperations<String, Integer, String> hashOperations;
	@Autowired
	RedisTemplate<String, Integer> redisTemplate;
	
	String status=null;
	 Cache<String, DataObject> cache;
	@RequestMapping(method=RequestMethod.GET,path="/suma")
    public String Suma(@RequestParam(value="operator1", defaultValue="World") int operator1, @RequestParam(value="operator2", defaultValue="World") int operator2) {
        int resultado=operator1+operator2;
		return "El resultado es: "+resultado;
    }
	@RequestMapping(method=RequestMethod.PUT,path="/status")
    public String Status(@RequestParam(value="status", defaultValue="nulo") String estado) {
		System.out.println(Calendar.getInstance().getTimeInMillis()+" Estoy en método Status");
		this.status= estado;
		
		if (cache ==null) cache=Caffeine.newBuilder()
       		  .expireAfterWrite(1, TimeUnit.MINUTES)
       		  .maximumSize(100) 
       		  .build();
       String key = "A";
       DataObject dataObject = cache.getIfPresent(key);
       if (dataObject==null) dataObject =new DataObject("Primer objeto");
       cache.put(key, dataObject);
       dataObject = cache.getIfPresent(key);
       System.out.println("Escribiendo el dataObject "+ dataObject.getData());
		

       
       
////////////////Redis/////////////////////////////////////
       
       this.hashOperations.put("USER",123, "unodostres");

       System.out.println("Escribiendo el redis "+this.hashOperations.get("USER",123));
		
		
		return estado;
    }
	@RequestMapping(method=RequestMethod.POST,path="/json")
    public void Payload(@RequestBody String json) {
 
		System.out.println("Esto es lo que viene en el Body: "+ json);
    }
	public String getStatus(){
		try{
			Thread.sleep(2000);
		}catch(Exception e){};
		return status;
	}

}
