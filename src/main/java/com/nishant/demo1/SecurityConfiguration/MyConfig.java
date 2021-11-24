package com.nishant.demo1.SecurityConfiguration;

import com.nishant.demo1.services.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .cors().and()
            .authorizeRequests()
            .antMatchers("/generate-token","/create-user").permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
    }
    
    
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http
    //     .authorizeRequests()
    //     .antMatchers("/home/**").hasAnyRole("USER","MOD","ADMIN")
    //     .antMatchers("/updateProfileForm/**").hasAnyRole("MOD","ADMIN")
    //     .antMatchers("/addProfileForm/**","/deleteProfile/**").hasAnyRole("ADMIN")
    //     .antMatchers("/signin**","/register").permitAll()
    //     .and()
    //     .formLogin().loginPage("/signin")
    //     .defaultSuccessUrl("/home")
    //     .successHandler(new AuthenticationSuccessHandler() {
    //         @Override
    //         public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    //                 throws IOException, ServletException {

    //             UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    //             String username = userDetails.getUsername();                    
    //             log.info("The user " + username + " has logged in successfully");
    //             response.sendRedirect(request.getContextPath());
    //         }
    //     })
    //     .and()
    //     .logout()
    //     .logoutSuccessHandler(new LogoutSuccessHandler() {
 
    //             @Override
    //             public void onLogoutSuccess(HttpServletRequest request,
    //                         HttpServletResponse response, Authentication authentication)
    //                     throws IOException, ServletException {
    //                 UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    //                 String username = userDetails.getUsername();  
    //                 log.info("The user " + username + " has logged out successfully");
    //                 response.sendRedirect("signin?logout");
    //             }
    //         })
    //     .and()
    //     .exceptionHandling().accessDeniedPage("/home?notauthorized")
    //     .and()
    //     .cors().disable()
    //     .csrf().disable();
    // }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public UserDetailsService geUserDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(geUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

}