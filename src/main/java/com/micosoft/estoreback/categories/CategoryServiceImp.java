package com.micosoft.estoreback.categories;

import com.micosoft.estoreback.errors.exceptions.NotFound;
import com.micosoft.estoreback.errors.exceptions.ServerError;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class CategoryServiceImp implements CategoryService{
    @Autowired
    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categoriesDb=categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS=new ArrayList<>();
        for (Category category:categoriesDb){
            CategoryDTO categoryDTO= CategoryDTO.builder()
                    .title(category.getTitle())
                    .slug(category.getSlug())
                    .description(category.getDescription())
                    .status(category.getStatus())
                    .imageUrl(category.getImageUrl())
                    .id(category.getId())
                    .build();
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        try {
            Category category= Category.builder()
                    .title(categoryDTO.getTitle())
                    .description(categoryDTO.getDescription())
                    .slug(categoryDTO.getSlug())
                    .imageUrl(categoryDTO.getImageUrl())
                    .status(categoryDTO.getStatus())
                    .build();
            categoryRepository.save(category);
            return categoryDTO;
        }catch (Exception e){
            throw new ServerError("Something went wrong", e.getCause());
        }
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        try {

        Category categoryDb=categoryRepository.findById(id).orElseThrow(()->new NotFound("Category Not Found"));
        if (!categoryDTO.getTitle().equals(categoryDb.getTitle())|| !categoryDTO.getTitle().isBlank()){
            categoryDb.setTitle(categoryDTO.getTitle());
        }
        if (!categoryDTO.getDescription().equals(categoryDb.getDescription())|| !categoryDTO.getDescription().isBlank()){
            categoryDb.setDescription(categoryDTO.getDescription());
        }
        if (!categoryDTO.getSlug().equals(categoryDb.getSlug())|| !categoryDTO.getSlug().isBlank()){
            categoryDb.setSlug(categoryDTO.getSlug());
        }
        if (!categoryDTO.getImageUrl().equals(categoryDb.getImageUrl())|| !categoryDTO.getImageUrl().isBlank()){
            categoryDb.setImageUrl(categoryDTO.getImageUrl());
        }
        if (!categoryDTO.getStatus().equals(categoryDb.getStatus())){
            categoryDb.setStatus(categoryDTO.getStatus());
        }
        categoryDb.setUpdateAt();
        categoryRepository.save(categoryDb);
        return categoryDTO;
        }catch (Exception e){
            throw new ServerError("Something Went Wrong", e.getCause());
        }
    }

    @Override
    public void deleteCategory(Long id) {
        try{
            Category categoryDb=categoryRepository.findById(id).orElseThrow(()->new NotFound("Category Not Found"));
            categoryRepository.delete(categoryDb);
        }catch (Exception e){
            throw new ServerError("Something Went Wrong", e.getCause());
        }
    }
}
