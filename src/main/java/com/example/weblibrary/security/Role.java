package com.example.weblibrary.security;

import com.example.weblibrary.model.Position;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    // Создаем поле id-идентификатор.
    @Id
    // Используем AUTO-генерацию идентификаторов.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String role;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "auth_user_id")
//    private AuthUser user;
}
