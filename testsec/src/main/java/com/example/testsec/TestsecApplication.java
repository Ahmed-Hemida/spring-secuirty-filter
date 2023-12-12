package com.example.testsec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@SpringBootApplication
@RestController
// @EnableWebSecurity(debug = true)
public class TestsecApplication {


	
	public static void main(String[] args) {
		SpringApplication.run(TestsecApplication.class, args);
	}


		@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//  http.cors(AbstractHttpConfigurer::disable);
    // http.csrf(AbstractHttpConfigurer::disable);
		http
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/login").permitAll()
				.anyRequest().permitAll()
			);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User
			.withUsername("user")
			.password("user")
			// .roles("USER")
			.build();

		return new InMemoryUserDetailsManager(userDetails);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {
		System.out.println("adwsawda");
		authenticationManager( userDetailsService(),
			 passwordEncoder()).authenticate( UsernamePasswordAuthenticationToken.unauthenticated(request.username, request.password));
		
		return SecurityContextHolder.getContext().getAuthentication().toString();
	}
	@GetMapping("/get/login")
	public String login( ) {
	 
		return "hello";
	}
/**
 * LoginRequest
String username,String password */
public record LoginRequest(String username,String password) {
}

}
