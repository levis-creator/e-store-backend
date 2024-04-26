package com.micosoft.estoreback.categories;

import com.micosoft.estoreback.errors.exceptions.BadRequest;
import com.micosoft.estoreback.errors.exceptions.NotFound;
import com.micosoft.estoreback.errors.exceptions.ServerError;
import com.micosoft.estoreback.market.Market;
import com.micosoft.estoreback.market.MarketDTO;
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
public class CategoryServiceImp implements CategoryService{
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final MarketRepository marketRepository;
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
                    .createdAt(category.getCreatedAt())
                    .updateAt(category.getUpdateAt())
                    .build();
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }
    @Transactional
    @Override
    public CategoryInputDTO createCategory(CategoryInputDTO categoryInputDTO) {
        try {
            if (categoryInputDTO == null) {
                throw new IllegalArgumentException("Category input DTO cannot be null");
            }

            Category category = Category.builder()
                    .title(categoryInputDTO.getTitle())
                    .description(categoryInputDTO.getDescription())
                    .slug(categoryInputDTO.getSlug())
                    .imageUrl(categoryInputDTO.getImageUrl())
                    .status(categoryInputDTO.getStatus())
                    .build();

            Set<Long> marketIds = categoryInputDTO.getMarkets();
            if (marketIds != null&& !marketIds.isEmpty()) {
                List<Market> marketList=marketRepository.findAllById(marketIds);
                for (Market market: marketList){
                    market.getCategories().add(category);
                }
            }
             categoryRepository.save(category);
            return categoryInputDTO; // This line is preserved, but you may want to return something else.
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new ServerError("Error creating category", e);
        }
    }


    @Override
    public CategoryInputDTO updateCategory(Long id, CategoryInputDTO categoryInputDTO) {
        Category categoryDb=categoryRepository.findById(id).orElseThrow(()->new NotFound("Category Not Found"));
        Set<Market> marketList=new LinkedHashSet<>();
        if (!categoryInputDTO.getTitle().equals(categoryDb.getTitle())|| !categoryInputDTO.getTitle().isBlank()){
            categoryDb.setTitle(categoryInputDTO.getTitle());
        }
        if (!categoryInputDTO.getDescription().equals(categoryDb.getDescription())|| !categoryInputDTO.getDescription().isBlank()){
            categoryDb.setDescription(categoryInputDTO.getDescription());
        }
        if (!categoryInputDTO.getSlug().equals(categoryDb.getSlug())|| !categoryInputDTO.getSlug().isBlank()){
            categoryDb.setSlug(categoryInputDTO.getSlug());
        }
        if (!categoryInputDTO.getImageUrl().equals(categoryDb.getImageUrl())|| !categoryInputDTO.getImageUrl().isBlank()){
            categoryDb.setImageUrl(categoryInputDTO.getImageUrl());
        }
        if (!categoryInputDTO.getStatus().equals(categoryDb.getStatus())){
            categoryDb.setStatus(categoryInputDTO.getStatus());
        }
        if (categoryInputDTO.getMarkets()!=null){
            for (Long marketId: categoryInputDTO.getMarkets()){
                Market market = marketRepository.findById(marketId).orElse(null);
                if (market!=null){
                    marketList.add(market);
                }
            }
            if (!marketList.equals(categoryDb.getMarkets())){
                categoryDb.setMarkets(marketList);
            }
        }


        categoryDb.setUpdateAt();
         categoryRepository.save(categoryDb);
        return categoryInputDTO;
    }

    @Override
    public void deleteCategory(Long id) {
            Category categoryDb=categoryRepository.findById(id).orElse(null);

            if (categoryDb==null){
                throw new NotFound("Category Not Found");
            }else {
            categoryRepository.delete(categoryDb);
            }
    }
}
