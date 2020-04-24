package com.med.disease.tracking.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.med.disease.tracking.app.model.User;
import com.med.disease.tracking.app.repository.IUserRepository;

@SpringBootApplication
public class CovidAppApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserRepository userRepository;

	@Override
    public void run(String...args) throws Exception {
        logger.info("Inserting -> {}", userRepository.insert(new User("sunil", "saibol", true)));
        logger.info("Inserting -> {}", userRepository.insert(new User("tanmay", "saibol", false)));
        logger.info("Inserting -> {}", userRepository.insert(new User("ananya", "anya", true)));
        logger.info("User sunil -> {}", userRepository.findByUserName("sunil"));
        logger.info("Update ananya -> {}", userRepository.update(new User("ananya","ananya", true)));
        userRepository.delete("ananya");

        logger.info("All users -> {}", userRepository.findAll());
    }

	public static void main(String[] args) {
		SpringApplication.run(CovidAppApplication.class, args);
	}

}
