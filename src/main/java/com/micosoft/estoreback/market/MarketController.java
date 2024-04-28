package com.micosoft.estoreback.market;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/markets")
public class MarketController {
    @Autowired
    private final MarketServices marketServices;
    @GetMapping
    ResponseEntity<List<MarketDTO>> getMarkets(){
        List<MarketDTO> marketDTOList=marketServices.getMarkets();
        return new ResponseEntity<>(marketDTOList, HttpStatus.OK);
    }
    @GetMapping("/unfiltered")
    ResponseEntity<List<Market>> getUnfileredMarkets(){
        List<Market> marketList=marketServices.unFilteredMarkets();
        return new ResponseEntity<>(marketList, HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<?>createMarket(@RequestBody MarketInputDTO marketInputDTO){
        Market marketDTOCreate= marketServices.createMarket(marketInputDTO);
        return  new ResponseEntity<>(marketDTOCreate, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    ResponseEntity<?>updateMarket(@PathVariable Long id, @RequestBody MarketInputDTO marketInputDTO){
        Market marketDTOCreate= marketServices.updateMarket(id, marketInputDTO);
        return  new ResponseEntity<>(marketDTOCreate, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    ResponseEntity<?>deleteMarket(@PathVariable Long id){
        marketServices.deleteMarket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
