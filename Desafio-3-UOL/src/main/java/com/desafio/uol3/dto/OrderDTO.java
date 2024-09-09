package com.desafio.uol3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    private Date date;
    private Long customerId;
    //É uma lista, pois você pode ter muitos produtos na mesma ordem
    private List<OrderProductDTO> products;
}
