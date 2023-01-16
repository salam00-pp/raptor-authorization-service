package com.bhn.raptorauthorizationservice;

import com.bhn.raptorauthorizationservice.jwt.AuthEntryPointJwt;
import com.bhn.raptorauthorizationservice.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Objects;

// TODO: 8/20/2022 Replace the WebSecurityConfigurerAdapter and extend WebSecurityConfigurer

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthEntryPointJwt authEntryPointJwt; //unauthorizedHandler

	@Autowired
	private SecurityProperties securityProperties;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	// TODO:  check if cors need to removed
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] unRestrictedPatterns = (Objects.isNull(securityProperties.getUnRestricted()))? new String[]{} :
				( (securityProperties.getUnRestricted().contains(","))? securityProperties.getUnRestricted().
						split(","): new String[]{securityProperties.getUnRestricted()});
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/api/v1/auth/**").permitAll()
			.antMatchers(unRestrictedPatterns).permitAll()
			.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
