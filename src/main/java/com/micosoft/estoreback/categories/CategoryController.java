package com.micosoft.estoreback.categories;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;
    @GetMapping
    ResponseEntity<List<CategoryDTO>> fetchCategories(){
      List<CategoryDTO> categoryDTOS=categoryService.getCategories();
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }
    @PostMapping("{id}")
    ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryCreate=categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(categoryCreate, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryDTOUpdate=categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(categoryDTOUpdate, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    ResponseEntity<?> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
