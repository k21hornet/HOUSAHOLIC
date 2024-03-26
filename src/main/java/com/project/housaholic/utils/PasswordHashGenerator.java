package com.project.housaholic.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * パスワードをハッシュ化した値を得るためのクラス
 */
public class PasswordHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "smithsmith";
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println(hashedPassword);
    }
}
