package com.micosoft.estoreback.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
@Autowired
    private CategoryRepository categoryRepository;
List<Category> gettingCategory(){
    return categoryRepository.findAll();
}

}
