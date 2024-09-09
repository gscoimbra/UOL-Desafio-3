package com.desafio.uol3.service;

import com.desafio.uol3.dto.CustomerDTO;
import com.desafio.uol3.model.entity.Customer;
import com.desafio.uol3.model.entity.User;
import com.desafio.uol3.repository.CustomerRepository;
import com.desafio.uol3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public List<Customer> buscarTodos() {
        return customerRepository.findAll();
    }

    public Customer buscarPorId(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Customer não encontrado"));
    }

    public Customer saveOrder(CustomerDTO customerDTO) {
        User user = userRepository.findById(customerDTO.getUserId()).orElseThrow(() -> new NoSuchElementException("Customer não encontrado"));

        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setEmail(customerDTO.getEmail());
        customer.setUser(user);

        return customerRepository.save(customer);
    }

}
