package com.atguigu.atcrowdfunding.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AtcrowdFundingSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    /**
     * 1、自定义请求授权访问规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/static/**","/welcome.jsp","/toLogin").permitAll()
                .anyRequest().authenticated();//剩下都需要认证
        // /login.jsp==POST  用户登陆请求发给Security
        http.formLogin().loginPage("/toLogin")
                .usernameParameter("loginacct")
                .passwordParameter("userpswd")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/main");
        http.csrf().disable();
        http.logout().logoutSuccessUrl("/login");
        http.rememberMe();
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                String type = httpServletRequest.getHeader("X-Requested-With");
                if ("XMLHttpRequest".equals(type)){
                    httpServletResponse.getWriter().print("403");
                }else {
                    httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/error/error403.jsp").forward(httpServletRequest,httpServletResponse);
                }
            }
        });
    }

}
