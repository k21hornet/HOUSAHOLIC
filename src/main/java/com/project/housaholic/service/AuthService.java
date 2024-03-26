package com.project.housaholic.service;

import com.project.housaholic.entity.AppUser;
import com.project.housaholic.input.SignupInput;
import com.project.housaholic.repository.AuthRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser getUserByEmail(String email){
        return authRepository.findByEmail(email);
    }

    // ログイン中のアカウント
    public AppUser getLoginUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByEmail(name);
    }

    // ユーザー新規登録
    @Transactional
    public void createUser(SignupInput signupInput) throws Exception{
        // その他のエラーチェック
        if(authRepository.findByEmail(signupInput.getEmail()) != null){
            throw new Exception("メールアドレスは既に登録されています。");
        }else if(!signupInput.getPassword().equals(signupInput.getPasswordConfirm())){
            throw new Exception("パスワードが一致しません。");
        }

        // Repositoryに渡すユーザーオブジェクト生成
        AppUser user = new AppUser();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(signupInput.getUsername());
        user.setEmail(signupInput.getEmail());
        user.setPasswordHash(passwordEncoder.encode(signupInput.getPassword())); // hash化して保存
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        authRepository.saveAndFlush(user);
    }
}
