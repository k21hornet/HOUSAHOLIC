package com.project.housaholic.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomAuthenticationProvider customAuthenticationProvider;

    public SecurityConfig(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(customAuthenticationProvider)
        .authorizeHttpRequests(authz -> authz   //URLごとの認可設定
                // '/css/**'などはOK
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/login","/signup").permitAll()
                .anyRequest().authenticated()
        ).formLogin(form -> form
                .usernameParameter("email")     //usernameの値を"email"から取得するよう設定する
                .passwordParameter("password")
                .loginProcessingUrl("/login")   // ログイン処理のURLを指定(フロントがログインボタン実行時にPOSTする場所)
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=failure")
        ).logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
        );

        return http.build();
    }
}
