package com.example.challenger.controller.mapper;

import com.example.challenger.controller.dtos.ProductRequestDTO;
import com.example.challenger.controller.dtos.ProductResponseDTO;
import com.example.challenger.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDTO toDTO(final Product product) {
        return new ProductResponseDTO(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getActive());
    }

    public Product toEntity(final ProductRequestDTO productDTO) {
        return Product.builder()
                .name(productDTO.name())
                .description(productDTO.description())
                .price(productDTO.price())
                .active(productDTO.active())
                .build();
    }
}
