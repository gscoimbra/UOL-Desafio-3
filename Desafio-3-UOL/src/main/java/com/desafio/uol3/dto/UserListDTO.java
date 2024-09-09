package com.desafio.uol3.dto;

import com.desafio.uol3.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserListDTO {

    private String name;
    private User.Role role;
}
