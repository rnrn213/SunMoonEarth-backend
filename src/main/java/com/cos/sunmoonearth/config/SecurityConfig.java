package com.cos.sunmoonearth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.cos.sunmoonearth.config.auth.PrincipalDetails;
import com.cos.sunmoonearth.config.auth.PrincipalDetailsService;
import com.cos.sunmoonearth.config.jwt.JwtAuthenticationFilter;
import com.cos.sunmoonearth.config.jwt.JwtAuthorizationFilter;
import com.cos.sunmoonearth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Autowired
	private final PrincipalDetailsService principalDetailsService;
	private final UserRepository userRepository;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors().configurationSource(corsConfigurationSource());
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.formLogin().disable()
		.httpBasic().disable()
		.addFilter(new JwtAuthenticationFilter(authenticationManager())) // AuthenticationManager
		.addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository)) // AuthenticationManager
		.authorizeRequests()
		.antMatchers("/api/v1/user/**") // ""안의 주소에 매칭되는 url에
		.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // role이 ''인 사람들만 접근할 수 있다.
		.anyRequest().permitAll();
		
		return http.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/api/**", configuration);
		
		return source;
	}
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return new AuthenticationManager() {
			
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				PrincipalDetails principal = (PrincipalDetails) principalDetailsService.loadUserByUsername(authentication.getPrincipal().toString());
				
				if (!passwordEncoder().matches(authentication.getCredentials().toString(), principal.getPassword())) {
					throw new BadCredentialsException("Bad credentials");
				}
				return new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
			}
		};
	}
	
}
