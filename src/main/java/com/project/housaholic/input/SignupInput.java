package com.project.housaholic.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupInput {

    @NotBlank(message = "ユーザー名は必須です")
    @Size(min = 3, max = 255, message = "ユーザー名は3文字以上255文字以下で入力してください")
    private String username;

    @Email(message = "無効なメールアドレスです")
    private String email;

    @NotBlank(message = "パスワードは必須です")
    @Size(min = 6, message = "パスワードは6文字以上で入力してください")
    private String password;

    @NotBlank(message = "パスワード確認は必須です")
    private String passwordConfirm;
}