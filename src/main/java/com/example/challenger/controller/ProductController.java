package com.example.challenger.controller;


import com.example.challenger.controller.dtos.ProductRequestDTO;
import com.example.challenger.controller.dtos.ProductResponseDTO;
import com.example.challenger.controller.mapper.ProductMapper;
import com.example.challenger.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @ResponseStatus(CREATED)
    @PostMapping
    public ProductResponseDTO save(@RequestBody final ProductRequestDTO request) {
        final var entity = productMapper.toEntity(request);
        return productMapper.toDTO(productService.save(entity));
    }

    @ResponseStatus(OK)
    @GetMapping("{id}")
    public ProductResponseDTO findById(@PathVariable final Long id) {
        return productMapper.toDTO(productService.findById(id));
    }

    @ResponseStatus(OK)
    @GetMapping
    public Page<ProductResponseDTO> listAll(Pageable pageable) {
        return productService.listAll(pageable).map(productMapper::toDTO);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable final Long id) {
        productService.deleteById(id);
    }

    @ResponseStatus(OK)
    @PutMapping
    public ProductResponseDTO update(@RequestBody final ProductRequestDTO request) {
        final var entity = productMapper.toEntity(request);
        final var updatedProduct = productService.update(request.id(), entity);
        return productMapper.toDTO(updatedProduct);
    }
}
