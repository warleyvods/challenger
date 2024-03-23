package com.example.challenger.controller.dtos;


public record ProductRequestDTO(
        Long id,
        String name,
        String description,
        Double price,
        Boolean active
) {
}
