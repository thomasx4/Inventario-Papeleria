package com.inventario.papeleria.service;

import java.util.ArrayList;
import java.util.List;

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

//----------------------------------------------------------------------------------------------
    //Get categories


    public List<CategoriesResponseDTO> getCategories(){
        List<Categories> categories = categoriesRepository.findAll();
        List<CategoriesResponseDTO> listCategories = new ArrayList<>();

        for (Categories categorie: categories){
            CategoriesResponseDTO categoriesResponseDTO = new CategoriesResponseDTO();
            categoriesResponseDTO.setId(categorie.getId());
            categoriesResponseDTO.setName(categorie.getName());
            categoriesResponseDTO.setDescription(categorie.getDescription());

            listCategories.add(categoriesResponseDTO);
        }
        return listCategories;

    }


}
