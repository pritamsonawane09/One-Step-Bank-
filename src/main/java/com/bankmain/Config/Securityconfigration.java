package com.bankmain.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bankmain.UserEntityService.UserEntityServiceIMPL;
import com.bankmain.fillter.JWTfillter;


@Configuration
@EnableWebSecurity
public class Securityconfigration extends WebSecurityConfigurerAdapter{

	@Autowired
    private UserEntityServiceIMPL UserEntityServiceIMPL;
	
	@Autowired
	private JWTfillter JWTfillter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(UserEntityServiceIMPL);
	}
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/v1/login","/api/v1/user/save","/api/v1/TransactionStatement","/v1/api/Transaction/DateToDate")
                .permitAll()
                //.antMatchers("/reg","/log","/addUser")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(JWTfillter, UsernamePasswordAuthenticationFilter.class);

    }
	
}
