package com.example.challenger.controller.dtos;


public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        Double price,
        Boolean active
) {
}
