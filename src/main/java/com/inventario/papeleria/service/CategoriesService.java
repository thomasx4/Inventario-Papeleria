package com.inventario.papeleria.service;

import org.springframework.stereotype.Service;

import com.inventario.papeleria.dto.CategoriesRequestDTO;
import com.inventario.papeleria.dto.CategoriesResponseDTO;
import com.inventario.papeleria.entity.Categories;
import com.inventario.papeleria.repository.CategoriesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    //----------------------------------------------------------------------------------------------
    //Create categories

    private final CategoriesRepository categoriesRepository;

    public CategoriesResponseDTO createCategories(CategoriesRequestDTO categoriesRequestDTO){
        Categories categories = new Categories();
        categories.setName(categoriesRequestDTO.getName());
        categories.setDescription(categoriesRequestDTO.getDescription());

        categoriesRepository.save(categories);
        CategoriesResponseDTO response = new CategoriesResponseDTO();
        response.setId((categories.getId()));
        response.setName(categories.getName());
        response.setDescription(categories.getDescription());

        return response;
    }
}
