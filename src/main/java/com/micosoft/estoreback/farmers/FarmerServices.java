package com.micosoft.estoreback.farmers;

import java.util.List;

public interface FarmerServices {
Farmer createFarmer(FarmerInputDTO farmerInputDTO);
List<FarmerDTO> getFarmers();
Farmer updateFarmer(Long id, FarmerInputDTO farmerInputDTO);
void deleteFarmer(Long id);
}
