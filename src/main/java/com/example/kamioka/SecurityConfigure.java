package com.example.kamioka;

import com.example.kamioka.auth.MyAuthenticationUserDetailsService;
import com.example.kamioka.auth.MyPreAuthenticatedProcessingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("-----------------------------------------");
        System.out.println("SecurityConfigure.configure(HttpSecurity)");
        System.out.println("-----------------------------------------");
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/hello", "/hello/*").permitAll()
//                // イマイチ（URL毎ではなく、METHOD毎に決めたい
//                .antMatchers("/api*/books").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/api*/books").hasRole("USER")
                .mvcMatchers(HttpMethod.POST, "/api*/books").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.addFilter(preAuthenticatedProcessingFilter());
    }

    @Bean
    public AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> authenticationUserDetailsService() {
        System.out.println("----------------------------------------------------");
        System.out.println("SecurityConfigure.authenticationUserDetailsService()");
        System.out.println("----------------------------------------------------");
        return new MyAuthenticationUserDetailsService();
    }

    @Bean
    public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
        System.out.println("----------------------------------------------------------");
        System.out.println("SecurityConfigure.preAuthenticatedAuthenticationProvider()");
        System.out.println("----------------------------------------------------------");
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(authenticationUserDetailsService());
        provider.setUserDetailsChecker(new AccountStatusUserDetailsChecker());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("---------------------------------------------------------");
        System.out.println("SecurityConfigure.configure(AuthenticationManagerBuilder)");
        System.out.println("---------------------------------------------------------");
        auth.authenticationProvider(preAuthenticatedAuthenticationProvider());

        auth.authenticationEventPublisher(new AuthenticationEventPublisher() {
            @Override
            public void publishAuthenticationSuccess(Authentication authentication) {
            }

            @Override
            public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
                System.out.println("------------------------------------------------");
                System.out.println("SecurityConfigure.publishAuthenticationFailure()");
                System.out.println("exception = " + exception);
                System.out.println("------------------------------------------------");
            }
        });
    }

    @Bean
    public AbstractPreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter() throws Exception {
        System.out.println("----------------------------------------------------");
        System.out.println("SecurityConfigure.preAuthenticatedProcessingFilter()");
        System.out.println("----------------------------------------------------");
        MyPreAuthenticatedProcessingFilter filter = new MyPreAuthenticatedProcessingFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }
}
