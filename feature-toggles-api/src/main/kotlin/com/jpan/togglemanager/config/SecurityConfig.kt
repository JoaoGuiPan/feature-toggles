package com.jpan.togglemanager.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
class SecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS)
            .permitAll()
            .and()
            .authorizeRequests()
            .antMatchers( "/v2/api-docs", "/swagger-ui.html", "/swagger-ui.html/**", "/webjars/**")
            .permitAll()

        http
            .authorizeRequests()
            .antMatchers("/toggles", "/auth")
            .authenticated()
            .and()
            .httpBasic()
            .and()
            .csrf().disable()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService? {

        val pm: UserDetails = User
                .withUsername("product")
                .password(getPasswordEncoder().encode("manager"))
                .roles("PM")
                .build()

        return InMemoryUserDetailsManager(pm)
    }

    fun getPasswordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }
}
