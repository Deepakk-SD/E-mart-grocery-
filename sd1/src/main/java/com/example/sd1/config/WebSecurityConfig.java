package com.example.sd1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.sd1.UserDetails.CustomSuccessHandler;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return  provider;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests().antMatchers("/login","/h2-console","/h2-console/**").permitAll()
			.antMatchers("/adduser","/confirmation","/aitem").hasAnyAuthority("admin")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login").permitAll().successHandler(customSuccessHandler)
			.and()
			.logout().invalidateHttpSession(true)
	   		.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login").permitAll();
		http.headers().frameOptions().disable();
	}

	
	
	
}
