package com.shop.config;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    MemberService memberService;

    @Bean
    public SecurityFilterChain fillterChain(HttpSecurity http) throws Exception{
        http.formLogin()
                .loginPage("/members/login") //로그인 페이지 URL설정
                .defaultSuccessUrl("/")      //로그인 성공시 이동할 URL
                .usernameParameter("email")  //로그인 시 사용할 파라미터 이름 지정
                .failureUrl("/members/login/error") //로그인 실패시 이동할 URL
                .and()                       //
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}