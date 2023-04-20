package mk.ukim.finki.emt.lab.config;
//package mk.ukim.finki.emt.lab.config;
//import mk.ukim.finki.emt.lab.config.filters.JWTAuthenticationFilter;
//import mk.ukim.finki.emt.lab.config.filters.JWTAuthorizationFilter;
//import mk.ukim.finki.emt.lab.service.UserService;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final JWTAuthenticationFilter authenticationFilter;
//    private final JWTAuthorizationFilter authorizationFilter;
//
//    public SecurityConfig(PasswordEncoder passwordEncoder, UserService userService) throws Exception {
//        authenticationFilter=new JWTAuthenticationFilter(authenticationManager(), userService, passwordEncoder);
//        authorizationFilter=new JWTAuthorizationFilter(authenticationManager());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilter(authenticationFilter)
//                .addFilter(authorizationFilter)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//
//}

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/**").permitAll();
    }
}