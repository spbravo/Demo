package com.example;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;;



@SpringBootApplication
@EnableFeignClients
public class DemoApplication {
	
	

	public static void main(String[] args) {
		//SpringApplication.run(DemoApplication.class, args);
		
		ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        
        Cache<String, DataObject> cache = Caffeine.newBuilder()
        		  .expireAfterWrite(1, TimeUnit.MINUTES)
        		  .maximumSize(100) 
        		  .build();
        String key = "A";
        DataObject dataObject = cache.getIfPresent(key);
        if (dataObject==null) dataObject =new DataObject("Primer objeto");
        cache.put(key, dataObject);
        dataObject = cache.getIfPresent(key);
        System.out.println(dataObject.getData());
        
        
        
       
        }
        
        
        
	}

