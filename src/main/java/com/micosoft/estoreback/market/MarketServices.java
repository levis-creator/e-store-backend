package com.micosoft.estoreback.market;

import java.util.List;

public interface MarketServices {
    List<MarketDTO> getMarkets();
    List<Market>unFilteredMarkets();
    Market createMarket(MarketInputDTO marketInputDTO);
    Market updateMarket(Long id, MarketInputDTO marketInputDTO);
    void deleteMarket(Long id);
}
