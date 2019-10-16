package com.wipro;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class CustomerApplication {
    public static void main(String args[]){
        SpringApplication.run(CustomerApplication.class,args);


    }


}

