package com.mpoznyak.configuration.security;

import com.mpoznyak.configuration.CustomAuthenticationSuccessHandler;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.service.UserDetailsServiceImpl;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Max Poznyak
 * on 11/14/18  at 00:12
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.mpoznyak.*")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Configuration
    @Order(1)
    public class ApiWebSecurity extends WebSecurityConfigurerAdapter {


        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
        }

        @Override
        @Loggable
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().and().antMatcher("/api/**")
                    .authorizeRequests()
                    .anyRequest()
                    .access("hasRole('ADMIN')")
                    .and()
                    .httpBasic()
                    .authenticationEntryPoint(authenticationEntryPoint()).and().csrf().disable();
        }

        @Bean
        public AuthenticationEntryPoint authenticationEntryPoint() {
            BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
            entryPoint.setRealmName("api realm");
            return entryPoint;
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowCredentials(true);
            configuration.setAllowedOrigins(Arrays.asList("*"));
            configuration.setAllowedHeaders(Arrays.asList("*"));
            configuration.addAllowedMethod("GET");
            configuration.addAllowedMethod("PUT");
            configuration.addAllowedMethod("POST");
            configuration.addAllowedMethod("DELETE");
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean()
                throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        public AuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
            return new LoginUrlAuthenticationEntryPoint("/auth");
        }


    }

    @Configuration
    @Order(2)
    public class FormLoginWebSecurity extends WebSecurityConfigurerAdapter {

        @Autowired
        private AuthenticationSuccessHandler successHandler;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            auth.userDetailsService(userDetailsService).passwordEncoder(encoder);

        }

        @Loggable
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .authorizeRequests()
                    .antMatchers("/", "/login", "/home", "/static/**").permitAll()
                    .antMatchers("/management/**").access("hasRole('MANAGER') OR hasRole('ADMIN')")
                    .antMatchers("/driver/**").access("hasRole('DRIVER') OR hasRole('ADMIN')")
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .usernameParameter("login")
                    .passwordParameter("password")
                    .successHandler(successHandler)
                    .permitAll()
                    .failureUrl("/login?error=true")
                    .and()
                    .csrf().disable()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/home")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                    .and().exceptionHandling().accessDeniedPage("/access-denied");
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean()
                throws Exception {
            return super.authenticationManagerBean();
        }

    }


}
