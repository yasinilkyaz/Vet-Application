package com.example.veterinarymanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeterinaryManagementSystemApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(VeterinaryManagementSystemApplication.class, args);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

	}

}
