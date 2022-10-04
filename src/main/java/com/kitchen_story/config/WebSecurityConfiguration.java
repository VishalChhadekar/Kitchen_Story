package com.kitchen_story.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.kitchen_story.filters.JWTFilter;
import com.kitchen_story.service.AppUserDetailService;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//	private static final String[] 
//			WHITE_LIST_URLS = { 
//					"/admin/**", "/admin/**","/register"};
	@Autowired
	private AppUserDetailService appUserService;
	
	@Autowired
    private JWTFilter jwtFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(appUserService);
	}
	@Bean
	AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider
        = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return  provider;

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11); // this take Integer strength as input parameter
	}
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().disable()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/").permitAll()
//		.antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
//		.antMatchers("/admin/**").hasAuthority("ADMIN")
		.antMatchers("/user/**").permitAll()
		.antMatchers("/admin/**").permitAll()
		.antMatchers("/register").permitAll()
//		.antMatchers(WHITE_LIST_URLS).permitAll()
		.antMatchers("/authenticate").permitAll()
		.anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	

}
