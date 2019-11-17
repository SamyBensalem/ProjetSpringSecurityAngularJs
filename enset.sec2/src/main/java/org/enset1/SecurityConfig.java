package org.enset1;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration//35:35
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
//	  @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	    }
//	
	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
	
	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth,DataSource datasource) throws Exception {
	
		/*
		auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN","PROF");
		auth.inMemoryAuthentication().withUser("prof1").password("{noop}123").roles("PROF");
		auth.inMemoryAuthentication().withUser("et1").password("{noop}123").roles("ETUDIANT");// cette possibilité n'est pas ultra secure
		auth.inMemoryAuthentication().withUser("sco1").password("{noop}123").roles("SCOLARITE");
		*/			
		
		auth.jdbcAuthentication()
		.dataSource(datasource)
		.usersByUsernameQuery("select username as principal, password as credentials, true from users where username = ?")
		.authoritiesByUsernameQuery("select user_username as principal, roles_role as role from users_roles where user_username = ?")//youssfi utilisait .groupAuthoritiesByUsername(query) mais il y avait un souci il allait taper dans une table authorities, j'ai du passer cette methode et supprimer l'encodage du password par defaut avec le @bean public static NoOpPasswordEncoder passwordEncoder() {
		.rolePrefix("ROLE_")//on peut personaliser le prefixe mais c'est ajouté pardefaut de toutes facons
		;
		
		//auth.ldapAuthentication();//on pourrait utiliser un annuaire ldap
		
	}//globalConfig
	
	
	protected void configure(HttpSecurity http)throws Exception{
		
		http
		.csrf().disable()    // desactive <input type="hidden" name="_csrf" value="a02c13e7-7b39-49f9-aac0-e71c9bb9436a"> qui est une protection, si on le desactive la reconnaissance se efra uniquement sur le session ID
		.authorizeRequests()
		.antMatchers("/css/**","/js/**","/images/**").permitAll()                   //indique les ressources que l'on veut utiliser ---> on doit theoriquement le faire pour la feuille de style mais moi ca marchait alors....
		    .anyRequest()
		    .authenticated()         //avec toutes ces lignes on indique que toutes les requetes doivent etre authentifiées sauf Login
		    .and()
		    .formLogin()
		    .loginPage("/login")
		    .permitAll()
		    .defaultSuccessUrl("/index.html")
		    .and()
		    .logout()
		    .invalidateHttpSession(true)
		    .logoutUrl("/logout")
		    .permitAll();
		    
		
	}
	
	
	
	

}
