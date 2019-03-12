package org.szhang.personal.podscraper;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .anonymous().and()
        .authorizeRequests()
        .antMatchers("/", "/api/search/*", "/api/featured", "/**/*.{js,html,css}").permitAll()
        .anyRequest().authenticated();
  }
}
