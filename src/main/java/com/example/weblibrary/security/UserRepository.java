package com.example.weblibrary.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AuthUser, Long> {

    // Создаем метод findByUsername
    // для поиска пользователя по имени пользователя
    AuthUser findByUsername(String username);
}