package com.desafio.uol3.service;

import com.desafio.uol3.dto.ProductUpdateDTO;
import com.desafio.uol3.model.entity.Product;
import com.desafio.uol3.repository.OrderProductRepository;
import com.desafio.uol3.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    @Transactional(readOnly = true)
    public List<Product> buscarTodos() {
        return productRepository.findAll();
    }

    public Product salvar(Product product) {
        return productRepository.save(product);
    }

    public ResponseEntity<?> buscarPorId(Long id) {
        try {
            return ResponseEntity.ok(productRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

    public ResponseEntity<?> deletarPorId(Long id) {
        try {
            //Se existir uma ordem para esse produto não é permitido deletar o produto
            if (!orderProductRepository.existsById(id)) {
                productRepository.deleteById(id);
            } else {
                throw new RuntimeException("Existe ordem para este produto " + id);
            }
            return ResponseEntity.ok("Produto removido com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

    public Product updateProduct(Long id, ProductUpdateDTO productUpdateDTO) {
        // Busca o produto existente
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));

        // Atualiza apenas os campos fornecidos no DTO
        if (productUpdateDTO.getName() != null) {
            existingProduct.setName(productUpdateDTO.getName());
        }
        if (productUpdateDTO.getStock() != null) {
            existingProduct.setStock(productUpdateDTO.getStock());
        }
        if (productUpdateDTO.getStatus() != null) {
            existingProduct.setStatus(productUpdateDTO.getStatus());
        }
        if (productUpdateDTO.getPrice() != null) {
            existingProduct.setPrice(productUpdateDTO.getPrice());
        }

        // Salva o produto atualizado no banco de dados
        return productRepository.save(existingProduct);
    }
}
