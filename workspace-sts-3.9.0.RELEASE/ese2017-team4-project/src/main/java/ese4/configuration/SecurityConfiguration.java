package ese4.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 
 * @author ESE04
 * All the security configurations.
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(bCryptPasswordEncoder);
	}

	/**
	 * .authorizeRequests Takes care of permissions for each user.
	 * .formLogin takes care of what to do after a successful login
	 * .logout takes care of what to do after a successful logout
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/home").permitAll()
				.antMatchers("/registration").permitAll() //später nur für ADMIN freigeben
				.antMatchers("/package/**").hasAuthority("LOGISTICIAN")
				.antMatchers("/user/**").hasAuthority("ADMIN")
				.antMatchers("/tour/confirm").hasAuthority("DRIVER")
				.antMatchers("/tour/makeTour").hasAuthority("LOGISTICIAN")
				.antMatchers("/tour/createTour").hasAuthority("LOGISTICIAN")
				.antMatchers("/tour/driverSelection").hasAuthority("LOGISTICIAN")
				.antMatchers("/tour/packageSelection").hasAuthority("LOGISTICIAN")
				.antMatchers("/tour/listAll").hasAuthority("LOGISTICIAN")
				.antMatchers("/tour/listSelectedTour").hasAuthority("LOGISTICIAN")
				.antMatchers("/tour/listMyTour").hasAuthority("DRIVER")
				.anyRequest().authenticated().and().csrf().disable()
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/home")
				.usernameParameter("name")
				.passwordParameter("password")
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").and().exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
	
	/**
	 * Allows the webpage to have acces to certain folders.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**");
	}
	
	/**
	 * Creates the Encoder
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}