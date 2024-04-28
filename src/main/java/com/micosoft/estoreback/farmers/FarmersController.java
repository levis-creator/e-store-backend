package com.micosoft.estoreback.farmers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/farmers")
public class FarmersController {
    @Autowired
    private final FarmerServices farmerServices;
    @GetMapping
    ResponseEntity<List<FarmerDTO>> getAllFarmer(){
        List<FarmerDTO> farmerDTOS=farmerServices.getFarmers();
        return new ResponseEntity<>(farmerDTOS, HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<?> creatingFarmer(@RequestBody FarmerInputDTO farmerInputDTO){
        Farmer createdFarmer=farmerServices.createFarmer(farmerInputDTO);
        return new ResponseEntity<>(createdFarmer, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    ResponseEntity<?> updateFarmer(@PathVariable Long id, @RequestBody FarmerInputDTO farmerInputDTO){
        Farmer updatedFarmer=farmerServices.updateFarmer(id, farmerInputDTO);
        return new ResponseEntity<>(updatedFarmer, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    ResponseEntity<?> deleteFarmer(@PathVariable Long id){
        farmerServices.deleteFarmer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
