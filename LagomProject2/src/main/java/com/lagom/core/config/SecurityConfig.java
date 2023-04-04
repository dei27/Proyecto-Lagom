package com.lagom.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.lagom.core.service.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	
	 @Autowired
	 private MyAuthenticationSuccessHandler successHandler;

	 private AuthenticationManagerBuilder builder; 
	
	 @Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
		http
			.csrf()
	        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	        .and()
			.authorizeHttpRequests(authConfig -> {
			authConfig.requestMatchers("/usuario/**").authenticated();
			authConfig.requestMatchers("/admin/**").authenticated();
			authConfig.requestMatchers(HttpMethod.POST).permitAll();
			
		})
			.formLogin()
			.loginPage("/login")
			.usernameParameter("email")
            .successHandler(successHandler)
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout");
            

		return http.build();
	}
	
	@Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    AuthenticationManager authentiationManager(AuthenticationConfiguration authentication)throws Exception{
        return authentication.getAuthenticationManager();
    }
	
	@Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/style.css", "/webjars/bootstrap/**", "/imgs/**","/js/**");
    }

}
