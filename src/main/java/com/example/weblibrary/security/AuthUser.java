package com.example.weblibrary.security;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "auth_user")
public class AuthUser {

    // Создаем поле id для хранения идентификатора пользователя.
    @Id
    // Используем AUTO-генерацию идентификаторов.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Создаем поле username для хранения имени пользователя.
    // Устанавливаем ограничение на уникальность значения в колонке
    // и запрет на NULL.
    @Column(nullable = false, unique = true)
    private String username;

    // Создаем поле password для хранения пароля пользователя
    private String password;


}