package com.micosoft.estoreback.market;

import com.micosoft.estoreback.categories.Category;
import com.micosoft.estoreback.categories.CategoryRepository;
import com.micosoft.estoreback.errors.exceptions.NotFound;
import com.micosoft.estoreback.errors.exceptions.ServerError;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class MarketServiceImpl implements MarketServices {
    @Autowired
    private final MarketRepository marketRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<MarketDTO> getMarkets() {
        List<Market> marketList = marketRepository.findAll();
        List<MarketDTO> marketDTOList = new ArrayList<>();
        for (Market market : marketList) {
            MarketDTO marketDTO = MarketDTO.builder().id(market.getId()).title(market.getTitle()).logo(market.getLogo()).isActive(market.getIsActive()).createdAt(market.getCreatedAt()).updatedAt(market.getUpdatedAt()).build();
            marketDTOList.add(marketDTO);
        }
        return marketDTOList;
    }

    @Override
    public List<Market> unFilteredMarkets() {
        return marketRepository.findAll();
    }

    @Override
    public Market createMarket(MarketInputDTO marketInputDTO) {
            Market market = Market.builder()
                    .title(marketInputDTO.getTitle())
                    .logo(marketInputDTO.getLogo())
                    .isActive(marketInputDTO.getIsActive())
                    .build();
            Set<Long> categoriesId=marketInputDTO.getCategories();
        try {
            if (!categoriesId.isEmpty()){
                List<Category> categories=categoryRepository.findAllById(categoriesId);
                if (!categories.isEmpty()){
                for (Category category:categories){
                    category.getMarkets().add(market);
                }
                }
            }

            return marketRepository.save(market);

        } catch (Exception e) {
            throw new ServerError("Something Went Wrong");
        }
    }

    @Override
    public Market updateMarket(Long id, MarketInputDTO marketInputDTO) {
        Market marketDb = marketRepository.findById(id).orElseThrow(() -> new NotFound("Market Not Found"));
        List<Category> categoryList=categoryRepository.findAllById(marketInputDTO.getCategories());
        Set<Category> categorySet= new LinkedHashSet<>(categoryList);
        if (!marketInputDTO.getTitle().equals(marketDb.getTitle()) && !marketInputDTO.getTitle().trim().isBlank()) {
            marketDb.setTitle(marketInputDTO.getTitle());
        }
        if (!marketInputDTO.getLogo().equals(marketDb.getLogo()) && !marketInputDTO.getLogo().trim().isBlank()) {
            marketDb.setLogo(marketInputDTO.getLogo());
        }
        if (!marketInputDTO.getIsActive().equals(marketDb.getIsActive())) {
            marketDb.setIsActive(marketInputDTO.getIsActive());
        }
        if (!categorySet.equals(marketDb.getCategories())){
            marketDb.setCategories(categorySet);
        }
        marketDb.setUpdatedAt();
        return marketRepository.save(marketDb);

    }

    @Override
    public void deleteMarket(Long id) {
        Market marketDb = marketRepository.findById(id).orElseThrow(() -> new NotFound("Market Not Found"));
        try {
            Set<Category> categoriesRef= marketDb.getCategories();
            for (Category category: categoriesRef){
                category.getMarkets().remove(marketDb);
            }
        marketRepository.delete(marketDb);
        }catch (Exception e){
            throw new ServerError("Something Went wrong");
        }
    }
}
