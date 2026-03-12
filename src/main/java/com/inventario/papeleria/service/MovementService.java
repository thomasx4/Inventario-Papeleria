package com.inventario.papeleria.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.inventario.papeleria.dto.MovementRequestDTO;
import com.inventario.papeleria.dto.MovementResponseDTO;
import com.inventario.papeleria.entity.Movement;
import com.inventario.papeleria.repository.MovementRepository;
import com.inventario.papeleria.repository.ProductRepository;
import com.inventario.papeleria.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovementService {
    private final MovementRepository movementRepository;
    private final ProductRepository productRepository;
    private final UsersRepository userRepository;


//----------------------------------------------------------------------------------------------
    // Create movement

    public MovementResponseDTO createMovement(MovementRequestDTO dto) {
        Movement movement = new Movement();
        movement.setType(dto.getType());
        movement.setAmount(dto.getAmount());
        movement.setDescription(dto.getDescription());
        movement.setDate(LocalDateTime.now());

        movement.setProduct(productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
        
        movement.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        movementRepository.save(movement);

        MovementResponseDTO response = new MovementResponseDTO();
        response.setId(movement.getId());
        response.setType(movement.getType());
        response.setAmount(movement.getAmount());
        response.setDate(movement.getDate());
        response.setDescription(movement.getDescription());
        response.setProductId(movement.getProduct().getId());
        response.setProductName(movement.getProduct().getName());
        response.setUserName(movement.getUser().getName());

        return response;
    }
}
