package com.micosoft.estoreback.market;

import com.micosoft.estoreback.categories.Category;
import com.micosoft.estoreback.categories.CategoryRepository;
import com.micosoft.estoreback.errors.exceptions.NotFound;
import com.micosoft.estoreback.errors.exceptions.ServerError;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class MarketServiceImpl implements MarketServices {
    @Autowired
    private final MarketRepository marketRepository;
    @Autowired
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
    public Market createMarket(MarketInputDTO marketInputDTO) {
        try {
            Market market = Market.builder()
                    .title(marketInputDTO.getTitle())
                    .logo(marketInputDTO.getLogo())
                    .isActive(marketInputDTO.getIsActive())
                    .build();
            Set<Long> categoriesId=marketInputDTO.getCategories();
            if (categoriesId!=null && !categoriesId.isEmpty()){
                List<Category> categoryList =categoryRepository.findAllById(categoriesId);
                for (Category category: categoryList){
                    market.getCategories().add(category);
                }
            }
            return marketRepository.save(market);

        } catch (Exception e) {
            throw new ServerError("Something Went Wrong");
        }
    }

    @Override
    public MarketDTO updateMarket(Long id, MarketDTO marketDTO) {
        Market marketDb = marketRepository.findById(id).orElseThrow(() -> new NotFound("Market Not Found"));
        if (!marketDTO.getTitle().equals(marketDb.getTitle()) || !marketDTO.getTitle().isBlank()) {
            marketDb.setTitle(marketDTO.getTitle());
        }
        if (!marketDTO.getLogo().equals(marketDb.getLogo()) || !marketDTO.getLogo().isBlank()) {
            marketDb.setLogo(marketDTO.getLogo());
        }
        if (!marketDTO.getIsActive().equals(marketDb.getIsActive())) {
            marketDb.setIsActive(marketDTO.getIsActive());
        }
        marketDb.setUpdatedAt();
        marketRepository.save(marketDb);
        return marketDTO;
    }

    @Override
    public void deleteMarket(Long id) {
        Market marketDb = marketRepository.findById(id).orElseThrow(() -> new NotFound("Market Not Found"));
        try {

        marketRepository.delete(marketDb);
        }catch (Exception e){
            throw new ServerError("Something Went wrong");
        }
    }
}
