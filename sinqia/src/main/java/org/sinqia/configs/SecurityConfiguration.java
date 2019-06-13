package org.sinqia.configs;

import org.sinqia.daos.EmpresaDao;
import org.sinqia.daos.UserLoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserLoginDao ELDao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST,"/evento/cadastrar").hasRole("EMPRESA")
			.antMatchers(HttpMethod.GET,"/evento/cadastrar").hasRole("EMPRESA")
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/").permitAll()
			.antMatchers("/empresa/login").permitAll()
			.antMatchers("/empresa/form").permitAll()
			.antMatchers("/empresa/endereco").permitAll()
			.antMatchers(HttpMethod.POST, "/empresa").permitAll()
			.antMatchers(HttpMethod.GET, "/empresa").permitAll()
			.antMatchers("/sucesso").permitAll()
			.antMatchers("/usuario/login").permitAll()
			.antMatchers("/usuario").permitAll()
			.antMatchers("/evento/pesquisa/**").permitAll()
			.antMatchers("/evento/detalhe").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/login").permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(ELDao).passwordEncoder(new BCryptPasswordEncoder());
	}

}
