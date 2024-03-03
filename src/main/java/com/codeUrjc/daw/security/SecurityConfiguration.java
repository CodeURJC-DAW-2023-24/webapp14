package com.codeUrjc.daw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
					.requestMatchers("/registrar").permitAll()
					.requestMatchers("/error").permitAll()
					// PRIVATE PAGES
					.requestMatchers("/profile").hasAnyRole("USER")
					.requestMatchers("/inscripcion").hasAnyRole("USER")
					.requestMatchers("/dashboard").hasAnyRole("ADMIN")
					.requestMatchers("/NewEvent").hasAnyRole("ADMIN", "USER")
					.requestMatchers("/eventos").hasAnyRole("ADMIN", "USER")
					.requestMatchers("/permisosUsuarios").hasAnyRole("ADMIN")
					.requestMatchers("/editForm").hasAnyRole("ADMIN","USER")
					.requestMatchers("/editEvent").hasAnyRole("ADMIN")
			)
			.formLogin(formLogin -> formLogin
					.loginPage("/login")
					.failureUrl("/loginerror")
					.defaultSuccessUrl("/")
					.permitAll()
			)
			.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.permitAll()
			);



		// Disable CSRF at the moment
		//http.csrf(csrf -> csrf.disable());


		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService(){

		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("pass"))
				.roles("USER")
				.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("adminpass"))
				.roles("USER", "ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, admin);
	}

}
