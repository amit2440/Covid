package com.med.disease.tracking.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.med.disease.tracking.app.config.MyConfiguration;
import com.med.disease.tracking.app.repository.IUserRepository;

@SpringBootApplication
@EnableConfigurationProperties(MyConfiguration.class)
public class CovidAppApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final MyConfiguration configuration;
	
	public CovidAppApplication(MyConfiguration configuration) {
		this.configuration = configuration;
	}
	
	@Autowired
	private IUserRepository userRepository;

	@Override
    public void run(String...args) throws Exception {
		
//		vault code 
	    logger.info("----------------------------------------");
	    logger.info("Configuration properties");
	    logger.info("   example.username is {}", configuration.getUsername());
	    logger.info("   example.password is {}", configuration.getPassword());
	    logger.info("----------------------------------------");
	    
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
