package cinema1.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.sql.DataSource;

import static cinema1.model.Credentials.ADMIN_ROLE;

@Configuration
@EnableWebSecurity
public class AuthConfiguration implements WebMvcConfigurer {

	 @Autowired
	    private DataSource dataSource;

	 @Bean
	    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
	        return (request, response, authentication) -> {
	            // Reindirizza sempre al controller intermedio
	            response.sendRedirect(request.getContextPath() + "/post-login");
	        };
	    }
	 
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return NoOpPasswordEncoder.getInstance();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(authorize -> authorize
	                // 1. Allow public access to home page, films, and public API endpoints
	            	.requestMatchers("/api/prenotazioni/nuova").hasAnyAuthority("DEFAULT", "ADMIN")
	            	.requestMatchers("/admin/**").hasAuthority("ADMIN")
	                .requestMatchers(HttpMethod.GET, "/", "/home", "/register", "/css/**", "/images/**", "/favicon.ico", "/film/**", "/api/spettacoli/**").permitAll()
	                .requestMatchers(HttpMethod.POST, "/register", "/login").permitAll()
	                
	                // 2. Protect the admin area
	                .requestMatchers("/admin/**").hasAnyAuthority(ADMIN_ROLE)
	                
	                // 3. All other requests must be authenticated
	                .anyRequest().authenticated()
	            )
	            .formLogin(formLogin -> formLogin
	                .loginPage("/login")
	                .permitAll()
	                .successHandler(customAuthenticationSuccessHandler())
	                .failureUrl("/login?error=true")
	            )
	            .logout(logout -> logout
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/login")
	                .invalidateHttpSession(true)
	                .deleteCookies("JSESSIONID")
	                .clearAuthentication(true).permitAll()
	            )
	            .csrf(csrf -> csrf.disable());

	        http.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));

	        return http.build();
	    }
}