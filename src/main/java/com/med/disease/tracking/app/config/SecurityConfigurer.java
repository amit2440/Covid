//package com.med.disease.tracking.app.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity
//public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
//	/**
//	* By overriding this configure method, we are getting hold to {@AuthenticationManagerBuilder},
//	* which we then used to provide list of allowed users. 
//	*/
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// Setting the configuration on it using in memory authentication 
//		auth.inMemoryAuthentication()
//		.withUser("sunil")
//		.password("sunil")
//		.roles("USER")
//		.and()
//		.withUser("andrea")
//		.password("andrea")
//		.roles("ADMIN");
//	}
//	
//	/**
//	* This method indicates which password encoding is used. 
//	* With NoOpPasswordEncoder, there is no encoding used i.e. plain String text is returned 
//	* @returns {@PasswordEncoder}
//	*/
//	@Bean
//	public PasswordEncoder getPasswordEncoder(){
//		return NoOpPasswordEncoder.getInstance();	
//	}
//	
//	/**
//	* By overriding this configure method, we are getting hold to {@HttpSecurity},
//	* which we then used to restrict users based on their roles. 
//	* Here while setting antMatchers, we set access from most restricted to less restrictive
//	*/
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers("/admin").hasAnyRole("ADMIN")
//		.antMatchers("/user").hasAnyRole("USER","ADMIN")
//		.antMatchers("/").permitAll()
//		.and().formLogin();
//	}
//}
