package com.desafio.uol3.service;

import com.desafio.uol3.dto.OrderDTO;
import com.desafio.uol3.dto.OrderProductDTO;
import com.desafio.uol3.model.entity.Customer;
import com.desafio.uol3.model.entity.Order;
import com.desafio.uol3.model.entity.OrderProduct;
import com.desafio.uol3.model.entity.Product;
import com.desafio.uol3.repository.CustomerRepository;
import com.desafio.uol3.repository.OrderProductRepository;
import com.desafio.uol3.repository.OrderRepository;
import com.desafio.uol3.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    public List<Order> buscarTodos() {
        return orderRepository.findAll();
    }

    public Order buscarPorId(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));
    }

    public Order saveOrder(OrderDTO orderDTO) {
        // Busca o cliente pelo ID fornecido
        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado"));

        // Cria uma nova ordem com os dados fornecidos
        Order order = new Order();
        order.setDate(orderDTO.getDate());
        order.setCustomer(customer);

        // Salva a nova ordem no banco de dados
        Order savedOrder = orderRepository.save(order);

        // Processa os produtos e cria a relação many-to-many em OrderProduct
        for (OrderProductDTO productOrder : orderDTO.getProducts()) {
            // Busca cada produto pelo ID fornecido
            Product product = productRepository.findById(productOrder.getProductId())
                    .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));

            OrderProduct orderProduct = new OrderProduct();
            //Vai usar o order criado e salvo logo acima
            orderProduct.setOrder(savedOrder);
            orderProduct.setProduct(product);
            orderProduct.setQuantity(productOrder.getQuantity());

            // Salva a relação many-to-many
            orderProductRepository.save(orderProduct);
        }

        return savedOrder;
    }
}
