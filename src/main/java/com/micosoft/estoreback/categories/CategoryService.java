package com.micosoft.estoreback.categories;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO>getCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    void  deleteCategory(Long id);
}
