package com.codeUrjc.daw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
    public RepositoryUserDetailsService userDetailService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authenticationProvider(authenticationProvider());

		http
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/css/**","/fonts/**","/img/**","/js/**","/scss/**").permitAll()
					// PUBLIC PAGES
					.requestMatchers("/").permitAll()
					// PRIVATE PAGES
					.requestMatchers("/profile").hasAnyRole("USER")
					.requestMatchers("/dashboard").hasAnyRole("ADMIN")
			)
			.formLogin(formLogin -> formLogin
					.loginPage("/login")
					.failureUrl("/error")
					.defaultSuccessUrl("/profile")
					.permitAll()
			)
			.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.permitAll()
			);

		// Disable CSRF at the moment
		http.csrf(csrf -> csrf.disable());


		return http.build();
	}

}
