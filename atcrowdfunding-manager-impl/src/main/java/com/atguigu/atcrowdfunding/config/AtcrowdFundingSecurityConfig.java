package com.atguigu.atcrowdfunding.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AtcrowdFundingSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    /**
     * 1、自定义请求授权访问规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/static/**","/welcome.jsp").permitAll()
                .anyRequest().authenticated();//剩下都需要认证
        // /login.jsp==POST  用户登陆请求发给Security
        http.formLogin().loginPage("/login")
                .usernameParameter("loginacct").passwordParameter("userpswd")
                .defaultSuccessUrl("/main").permitAll();
        http.csrf().disable();
        http.logout().logoutSuccessUrl("/login");
        http.rememberMe();
        //http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

}
