package com.micosoft.estoreback.market;

import java.util.List;

public interface MarketServices {
    List<MarketDTO> getMarkets();
    Market createMarket(MarketInputDTO marketInputDTO);
    MarketDTO updateMarket(Long id, MarketDTO marketDTO);
    void deleteMarket(Long id);
}
