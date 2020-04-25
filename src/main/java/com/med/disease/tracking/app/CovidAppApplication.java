package com.med.disease.tracking.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class CovidAppApplication implements CommandLineRunner {
	
	@Override
    public void run(String...args) throws Exception {
//	      mybatis code 
//        logger.info("Inserting -> {}", userRepository.insert(new User("sunil", "saibol", true)));
//        logger.info("Inserting -> {}", userRepository.insert(new User("tanmay", "saibol", false)));
//        logger.info("Inserting -> {}", userRepository.insert(new User("ananya", "anya", true)));
//        logger.info("User sunil -> {}", userRepository.findByUserName("sunil"));
//        logger.info("Update ananya -> {}", userRepository.update(new User("ananya","ananya", true)));
//        userRepository.delete("ananya");
//
//        logger.info("All users -> {}", userRepository.findAll());
    }

	public static void main(String[] args) {
		SpringApplication.run(CovidAppApplication.class, args);
	}

}
