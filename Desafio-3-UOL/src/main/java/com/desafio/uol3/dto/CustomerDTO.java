package com.desafio.uol3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {

    private String name;
    private String email;
    private String address;
    private Long userId;
}
