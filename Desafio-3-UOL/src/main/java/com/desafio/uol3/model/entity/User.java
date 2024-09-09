package com.desafio.uol3.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_username", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "user_password", nullable = false, length = 200)
    private String password;

    @Enumerated(EnumType.STRING)//ele transforma o nome da constante em uma string para salvar no banco de dados, Sem essa anotação, o enum seria armazenado como um ordinal.
    @Column(name = "user_role", nullable = false, length = 25)
    private Role role = Role.ROLE_CLIENTE;

    public enum Role {
        ROLE_ADMIN, ROLE_CLIENTE
    }
}
