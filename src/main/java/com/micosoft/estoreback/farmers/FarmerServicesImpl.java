package com.micosoft.estoreback.farmers;

import com.micosoft.estoreback.errors.exceptions.NotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmerServicesImpl implements FarmerServices {
    @Autowired
    private final FarmerRepository farmerRepository;

    @Override
    public Farmer createFarmer(FarmerInputDTO farmerInputDTO) {
        Farmer farmer= Farmer.builder()
                .name(farmerInputDTO.getName())
                .phone(farmerInputDTO.getPhone())
                .email(farmerInputDTO.getEmail())
                .physicalAddress(farmerInputDTO.getPhysicalAddress())
                .contactPerson(farmerInputDTO.getContactPerson())
                .contactPersonPhone(farmerInputDTO.getContactPersonPhone())
                .terms(farmerInputDTO.getTerms())
                .notes(farmerInputDTO.getNotes())
                .isActive(farmerInputDTO.getIsActive())
                .build();
        return farmerRepository.save(farmer);
    }

    @Override
    public List<FarmerDTO> getFarmers() {
        List<Farmer> farmerList= farmerRepository.findAll();
        List<FarmerDTO> farmerDTOS= new ArrayList<>();
        for (Farmer farmer: farmerList){
            FarmerDTO farmerDTO= FarmerDTO.builder()
                    .id(farmer.getId())
                    .name(farmer.getName())
                    .phone(farmer.getPhone())
                    .email(farmer.getEmail())
                    .physicalAddress(farmer.getPhysicalAddress())
                    .contactPerson(farmer.getContactPerson())
                    .contactPersonPhone(farmer.getContactPersonPhone())
                    .terms(farmer.getTerms())
                    .notes(farmer.getNotes())
                    .isActive(farmer.getIsActive())
                    .build();
            farmerDTOS.add(farmerDTO);
        }
        return farmerDTOS ;
    }

    @Override
    public Farmer updateFarmer(Long id, FarmerInputDTO farmerInputDTO) {
        Farmer farmerDb=farmerRepository.findById(id).orElse(null);
        if (farmerDb==null){
            throw new NotFound("Farmer Not Found");
        }else {
            if (!farmerInputDTO.getName().isBlank()&&!farmerInputDTO.getName().equals(farmerDb.getName())){
                farmerDb.setName(farmerInputDTO.getName());
            }
            if (!farmerInputDTO.getPhone().isBlank()&& !farmerInputDTO.getPhone().equals(farmerDb.getPhone())){
                farmerDb.setPhone(farmerDb.getPhone());
            }
            if (!farmerInputDTO.getEmail().isBlank()&& !farmerInputDTO.getEmail().equals(farmerDb.getEmail())){
                farmerDb.setEmail(farmerDb.getEmail());
            }
            if (!farmerInputDTO.getPhysicalAddress().isBlank()&& !farmerInputDTO.getPhysicalAddress().equals(farmerDb.getPhysicalAddress())){
                farmerDb.setPhysicalAddress(farmerDb.getPhysicalAddress());
            }
            if (!farmerInputDTO.getContactPerson().isBlank()&& !farmerInputDTO.getContactPerson().equals(farmerDb.getContactPerson())){
                farmerDb.setContactPerson(farmerDb.getContactPersonPhone());
            }
            if (!farmerInputDTO.getContactPersonPhone().isBlank()&& !farmerInputDTO.getContactPersonPhone().equals(farmerDb.getContactPersonPhone())){
                farmerDb.setContactPersonPhone(farmerDb.getContactPersonPhone());
            }
            if (!farmerInputDTO.getTerms().isBlank()&& !farmerInputDTO.getTerms().equals(farmerDb.getTerms())){
                farmerDb.setTerms(farmerDb.getTerms());
            }
            if (!farmerInputDTO.getNotes().isBlank()&& !farmerInputDTO.getNotes().equals(farmerDb.getNotes())){
                farmerDb.setNotes(farmerDb.getNotes());
            }
            if ( !farmerInputDTO.getIsActive().equals(farmerDb.getIsActive())){
                farmerDb.setIsActive(farmerDb.getIsActive());
            }
            farmerDb.setUpdatedAt();
            return  farmerRepository.save(farmerDb);
        }

    }

    @Override
    public void deleteFarmer(Long id) {
        Farmer farmerDb= farmerRepository.findById(id).orElseThrow(()->new NotFound("Farmer Not Found"));
        farmerRepository.delete(farmerDb);
    }
}
