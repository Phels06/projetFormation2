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

	// regles acces aux url
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers(HttpMethod.OPTIONS).anonymous()
			.and()
			.csrf().disable()
			.authorizeRequests().antMatchers("/rest/login").permitAll()
			.and()
			.authorizeRequests().antMatchers("/rest/**").authenticated().and().httpBasic()
			.and()
			.authorizeRequests().anyRequest().permitAll();
//		http.authorizeRequests()
//			.antMatchers("/").permitAll()
//			.antMatchers("/bootstrap/**").permitAll()
//			.antMatchers("/matiere","/matiere/**").hasAnyRole("ADMIN")
//			.antMatchers("/admin","/admin/**").authenticated()
//			.antMatchers("/**").authenticated().and()
//			.formLogin()
//				.loginPage("/login")
//				//.loginProcessingUrl("/perform")//page qui apparait pendant l'authentification
//				.defaultSuccessUrl("/home")
//				.failureUrl("/login?error=true").permitAll()
//			.and()
//			.logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
		// @formatter:on
	}

	// methode d'authentification
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
		//auth.inMemoryAuthentication()
		//	.withUser("admin").password("{noop}admin").roles("ADMIN")
		//	.and()
		//	.withUser("user").password("{noop}user").roles("USER");
		
		//auth.jdbcAuthentication().dataSource(dataSource)
		//	.usersByUsernameQuery("select username,password,enable from login where username=?")
		//	.authoritiesByUsernameQuery("select username,role from login_role where username=?");
		
		auth.userDetailsService(authService);
		
		// @formatter:on
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
