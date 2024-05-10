package com.micosoft.estoreback.categories;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO>getCategories();
    Category createCategory(CategoryInputDTO categoryInputDTO);
    CategoryInputDTO updateCategory(Long id, CategoryInputDTO categoryInputDTO);
    void  deleteCategory(Long id);
}
