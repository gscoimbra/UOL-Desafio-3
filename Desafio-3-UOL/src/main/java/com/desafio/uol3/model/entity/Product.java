package com.desafio.uol3.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @NotBlank
    @NotNull(message = "O nome do produto não pode ser nulo.")
    @Column(name = "product_name", nullable = false, length = 200)
    private String name;

    @Column(name = "product_stock", nullable = false)
    private Integer stock;

    @Column(name = "product_status", nullable = false)
    private Boolean status;

    @Positive(message = "O preço do produto deve ser positivo.")
    @Column(name = "product_price", nullable = false)
    private Double price;
}
