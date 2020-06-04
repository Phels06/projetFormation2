package formation.sopra.projetFormation.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import formation.sopra.projetFormation.service.AuthService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	// @Autowired
	// private DataSource dataSource;

	@Autowired
	private AuthService authService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers(HttpMethod.OPTIONS).anonymous()
			.and()
			.csrf().disable()
			.authorizeRequests().antMatchers("/rest/email/**","/rest/login","/rest/personne","/rest/personne/","/rest/personne/login/**","/rest/personne/mail/**","/rest/personne/","/rest/login","/rest/login/**","/rest/connexion").permitAll()
			.and()
			.authorizeRequests().antMatchers(HttpMethod.POST,"/rest/inscription").permitAll()
			.and()
			.authorizeRequests().antMatchers(HttpMethod.POST,"/rest/inscription/").permitAll()
			.and()
			.authorizeRequests().antMatchers(HttpMethod.DELETE,"/rest/personne/delete/**").authenticated()
			.anyRequest().authenticated().and().httpBasic();
	//		.and()
	//		.authorizeRequests().antMatchers("/rest/**").authenticated().and().httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
