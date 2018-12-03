package com.mpoznyak.web.config;

import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.web.LogoutSuccess;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }

    @Configuration
    @Order(1)
    public class ApiWebSecurity extends WebSecurityConfigurerAdapter {


        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
        }


        @Autowired
        private AuthenticationSuccessHandler successHandler;

        @Autowired
        private LogoutSuccess logoutSuccess;

        @Override
        @Loggable
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().and().authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers("/api/**").access("hasRole('ADMIN')")
                    .antMatchers("/", "/login", "/home", "/static/**").permitAll()
                    .antMatchers("/management/**").access("hasRole('MANAGER') OR hasRole('ADMIN')")
                    .antMatchers("/driver/**").access("hasRole('DRIVER') OR hasRole('ADMIN')")
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("login")
                    .passwordParameter("password")
                    .successHandler(successHandler)
                    .failureUrl("/login?error=true")
                    .and()
                    .logout()
                    //.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                    //.logoutSuccessUrl("redirect:home").permitAll()
                    //.logoutSuccessHandler(logoutSuccess)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                    .and().exceptionHandling().accessDeniedPage("/access-denied")
                    .and()
                    .httpBasic()
                    .authenticationEntryPoint(authenticationEntryPoint())
                    .and()
                    .csrf()
                    .disable()


            ;
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
            configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
            configuration.setMaxAge(3600L);
            configuration.setAllowedOrigins(Arrays.asList("*"));
            configuration.setAllowedHeaders(Arrays.asList("*"));
            configuration.addAllowedMethod("GET");
            configuration.addAllowedMethod("PUT");
            configuration.addAllowedMethod("POST");
            configuration.addAllowedMethod("DELETE");
            configuration.addAllowedMethod("OPTIONS");
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }
    }

    /*
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



    }
    */


}
