// package com.optum.EmployeeManagement.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.builders.WebSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
// import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
// import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
// import org.springframework.security.web.savedrequest.RequestCache;
// import org.springframework.security.web.savedrequest.SavedRequest;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter {

//     @Autowired
//     private UserDetailsService userDetailsService;

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.authorizeRequests()
//             .antMatchers("/h2-console/**").permitAll()
//             .anyRequest().authenticated()
//             .and()
//             .formLogin()
//                 .successHandler(successHandler())
//                 .and()
//             .logout()
//                 .logoutSuccessUrl("/login")
//                 .permitAll()
//                 .and()
//             .csrf().disable()
//             .headers().frameOptions().disable();
//     }

//     @Override
//     public void configure(WebSecurity web) throws Exception {
//         web.ignoring().antMatchers("/resources/**");
//     }

//     public AuthenticationSuccessHandler successHandler() {
//         SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
//         handler.setUseReferer(true);
//         handler.setDefaultTargetUrl("/employees");
//         handler.setRedirectStrategy((request, response, url) -> {
//             RequestCache requestCache = new HttpSessionRequestCache();
//             SavedRequest savedRequest = requestCache.getRequest(request, response);
//             if (savedRequest != null) {
//                 response.sendRedirect(savedRequest.getRedirectUrl());
//             } else {
//                 response.sendRedirect(url);
//             }
//         });
//         return handler;
//     }
    
//     @Override
//     @Bean
//     public UserDetailsService userDetailsService() {
//         return super.userDetailsService();
//     }
// }

package com.optum.EmployeeManagement.config;

import com.optum.EmployeeManagement.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private ServiceImpl userDetailsService;

    @Autowired
    public SecurityConfig(ServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
                .successHandler(successHandler())
                .and()
            .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
            .csrf().disable()
            .headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    public AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
        handler.setUseReferer(true);
        handler.setDefaultTargetUrl("/employees");
        handler.setRedirectStrategy((request, response, url) -> {
            RequestCache requestCache = new HttpSessionRequestCache();
            SavedRequest savedRequest = requestCache.getRequest(request, response);
            if (savedRequest != null) {
                response.sendRedirect(savedRequest.getRedirectUrl());
            } else {
                response.sendRedirect(url);
            }
        });
        return handler;
    }
}
