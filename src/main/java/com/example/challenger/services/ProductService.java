package com.example.challenger.services;

import com.example.challenger.entities.Product;
import com.example.challenger.exceptions.ProductNotFoundException;
import com.example.challenger.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(final Product product) {
        return productRepository.save(product);
    }

    public Page<Product> listAll(final Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public void deleteById(final Long id) {
        productRepository.deleteById(id);
    }

    public Product update(final Long id, final Product updatedProduct) {
        var existingProduct = findById(id);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setActive(updatedProduct.getActive());

        return productRepository.save(existingProduct);
    }

    public Product findById(final Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("product not found!"));
    }
}
