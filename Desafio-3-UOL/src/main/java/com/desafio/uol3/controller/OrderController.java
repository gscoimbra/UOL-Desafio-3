package com.desafio.uol3.controller;

import com.desafio.uol3.dto.OrderDTO;
import com.desafio.uol3.model.entity.Order;
import com.desafio.uol3.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.buscarTodos();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        try {
            Order buscarOrdem = orderService.buscarPorId(id);
            return ResponseEntity.ok(buscarOrdem);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordem não encontrada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao procurar a ordem");
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            Order savedOrder = orderService.saveOrder(orderDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
        } catch (NoSuchElementException e) {
            //no body será e.getMessage() pois possa ser que não encontre o produto ou o customer e vai retornar uma ou outra mensagem.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a ordem");
        }
    }
}
