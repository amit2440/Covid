package com.med.disease.tracking.app.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(DBConfig.class);
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;

	@Bean
	public DataSource configureDevDataSource() {
		DataSource dataSource = null;
		try {
			dataSource = createDataSource(url, username, password);
		} catch (Exception ex) {
			LOGGER.error("Exception while creating datasource" + ex);
		}
		return dataSource;
	}

	private DataSource createDataSource(String url, String username, String password) {
		DataSourceProperties properties = new DataSourceProperties();
		properties.setUrl(url);
		properties.setUsername(username);
		properties.setPassword(password);
		return properties.initializeDataSourceBuilder().build();
	}
}
