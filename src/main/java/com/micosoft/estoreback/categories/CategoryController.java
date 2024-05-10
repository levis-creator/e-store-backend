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
    @PostMapping
    ResponseEntity<?> createCategory(@RequestBody CategoryInputDTO categoryInputDTO){
        Category categoryCreate=categoryService.createCategory(categoryInputDTO);
        return new ResponseEntity<>(categoryCreate, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryInputDTO categoryInputDTO){
        CategoryInputDTO categoryDTOUpdate=categoryService.updateCategory(id, categoryInputDTO);
        return new ResponseEntity<>(categoryDTOUpdate, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    ResponseEntity<?> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
