package residentevil.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf()
               .csrfTokenRepository(csrfTokenRepository())
        .and()
               .authorizeRequests()
               .antMatchers("/login", "/register").anonymous()
               .antMatchers("/", "/js/**", "/css/**").permitAll()
               .antMatchers("/admin", "/users/**").hasAuthority("ADMIN")
               .antMatchers("/viruses/show").hasAnyAuthority("ADMIN", "USER", "MODERATOR")
               .antMatchers("/viruses/add", "/virus/edit/**", "/virus/delete/**")
                    .hasAnyAuthority("ADMIN", "MODERATOR")
               .anyRequest().authenticated()
       .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
       .and()
                .logout()
                .logoutSuccessUrl("/")
       ;



    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository =
                new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }

}
