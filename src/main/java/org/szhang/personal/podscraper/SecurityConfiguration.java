package org.szhang.personal.podscraper;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/**/*.{js,html,css}").permitAll()
        .antMatchers("/", "/featured", "/id/*", "/search/*", "/api/*").permitAll()
        .anyRequest().authenticated();
  }
}
