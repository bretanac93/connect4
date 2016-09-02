///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.bretana.auth.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//
//
///**
// *
// * @author user
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//  @Autowired
//  private EntryPointUnauthorizedHandler unauthorizedHandler;
//
//  @Autowired
//  private UserDetailsService userDetailsService;
//
//  @Autowired
//  private SecurityService securityService;
//
//  @Autowired
//  public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//    authenticationManagerBuilder
//      .userDetailsService(this.userDetailsService)
//        .passwordEncoder(passwordEncoder());
//  }
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Bean
//  @Override
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//    return super.authenticationManagerBean();
//  }
//
//  @Bean
//  public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
//    AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
//    authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
//    return authenticationTokenFilter;
//  }
//
//  @Bean
//  public SecurityService securityService() {
//    return this.securityService;
//  }
//
//  @Override
//  protected void configure(HttpSecurity httpSecurity) throws Exception {
//    httpSecurity
//      .csrf()
//        .disable()
//      .exceptionHandling()
//        .authenticationEntryPoint(this.unauthorizedHandler)
//        .and()
//      .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//      .authorizeRequests()
//        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//        .antMatchers("/auth/**", "/user/**").permitAll()
//        .anyRequest().authenticated();
//
//    // Custom JWT based authentication
//    httpSecurity
//      .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//  }
//
//}
//
