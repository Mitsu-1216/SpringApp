package com.example.demo2.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * ユーザー情報 リクエストデータ
 * Lombokの@Dataを使用してgetterとsetterを書かないようにシンプルな作り
 */
@Data
public class UserRequest implements Serializable {

    /**
     * 名前
     */
    @NotEmpty(message = "名前を入力してください")
    @Size(max = 100, message = "名前は100桁以内で入力してください")
    private String name;

    /**
     * 住所
     */
    private String address;

    /**
     * 電話番号
     */
    private String phone;
}
