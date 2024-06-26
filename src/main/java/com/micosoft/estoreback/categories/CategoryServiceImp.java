package com.micosoft.estoreback.categories;

import com.micosoft.estoreback.errors.exceptions.AlreadyExist;
import com.micosoft.estoreback.errors.exceptions.NotFound;
import com.micosoft.estoreback.market.Market;
import com.micosoft.estoreback.market.MarketRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final MarketRepository marketRepository;

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categoriesDb = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : categoriesDb) {
            CategoryDTO categoryDTO = CategoryDTO.builder().title(category.getTitle()).slug(category.getSlug()).description(category.getDescription()).status(category.getStatus()).imageUrl(category.getImageUrl()).id(category.getId()).createdAt(category.getCreatedAt()).updateAt(category.getUpdateAt()).build();
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    @Transactional
    @Override
    public Category createCategory(CategoryInputDTO categoryInputDTO) {
        Category category = Category.builder().title(categoryInputDTO.getTitle()).description(categoryInputDTO.getDescription()).slug(categoryInputDTO.getSlug()).imageUrl(categoryInputDTO.getImageUrl()).status(categoryInputDTO.getStatus()).build();
        Category categorySlug = categoryRepository.findBySlug(category.getSlug()).orElse(null);

        if (categorySlug == null) {
            return categoryRepository.save(category);
        } else {
            throw new AlreadyExist("Category already exists");
        }

    }


    @Override
    public CategoryInputDTO updateCategory(Long id, CategoryInputDTO categoryInputDTO) {
        Category categoryDb = categoryRepository.findById(id).orElseThrow(() -> new NotFound("Category Not Found"));
        if (!categoryInputDTO.getTitle().equals(categoryDb.getTitle()) && !categoryInputDTO.getTitle().isBlank()) {
            categoryDb.setTitle(categoryInputDTO.getTitle());
        }
        if (!categoryInputDTO.getDescription().equals(categoryDb.getDescription()) && !categoryInputDTO.getDescription().isBlank()) {
            categoryDb.setDescription(categoryInputDTO.getDescription());
        }
        if (!categoryInputDTO.getSlug().equals(categoryDb.getSlug()) && !categoryInputDTO.getSlug().isBlank()) {
            categoryDb.setSlug(categoryInputDTO.getSlug());
        }
        if (!categoryInputDTO.getImageUrl().equals(categoryDb.getImageUrl()) && !categoryInputDTO.getImageUrl().isBlank()) {
            categoryDb.setImageUrl(categoryInputDTO.getImageUrl());
        }
        if (!categoryInputDTO.getStatus().equals(categoryDb.getStatus())) {
            categoryDb.setStatus(categoryInputDTO.getStatus());
        }

        categoryRepository.save(categoryDb);
        return categoryInputDTO;
    }

    @Override
    public void deleteCategory(Long id) {
        Category categoryDb = categoryRepository.findById(id).orElse(null);

        if (categoryDb == null) {
            throw new NotFound("Category Not Found");
        } else {
            categoryRepository.delete(categoryDb);
        }
    }
}
