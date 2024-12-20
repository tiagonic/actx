package br.com.actx.back_end_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(authz -> authz
				.requestMatchers("/usuarios/**").authenticated()
				.requestMatchers("/**").permitAll()
				)
		.formLogin(form -> form
				.loginPage("/login")
				.permitAll()
				//.successHandler(successHandler()) // Adiciona o handler de sucesso
				)
		.logout(logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)    // Invalida a sessão HTTP
				.deleteCookies("JSESSIONID")    // Remove o cookie JSESSIONID
				.permitAll());
		return http.build();
	}

	// Handler de sucesso personalizado
	/*@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new SimpleUrlAuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(
					HttpServletRequest request, 
					HttpServletResponse response, 
					Authentication authentication) 
							throws IOException, ServletException {
				if (authentication != null && authentication.isAuthenticated()) {
					getRedirectStrategy().sendRedirect(request, response, "/");
				} else {
					super.onAuthenticationSuccess(request, response, authentication);
				}
			}
		};
	}*/

}
