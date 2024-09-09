package com.desafio.uol3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductUpdateDTO {

    private String name;
    private Integer stock;
    private Boolean status;
    private Double price;
}
