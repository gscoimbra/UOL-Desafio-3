package com.desafio.uol3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderProductDTO {

    private Long productId;
    private Integer quantity;
}
