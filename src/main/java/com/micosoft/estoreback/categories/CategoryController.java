package com.micosoft.estoreback.categories;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;
    @GetMapping
    List<Category> fetchCategories(){
        return categoryService.gettingCategory();
    }
}
