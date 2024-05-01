package com.micosoft.estoreback.training;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/training")
public class TrainingController {
    @Autowired
    private final TrainingServices trainingServices;
    @PostMapping
    ResponseEntity<?> createTraining(@RequestBody TrainingInputDTO trainingInputDTO){
        Training createTraining= trainingServices.createTraining(trainingInputDTO);
        return new ResponseEntity<>(createTraining, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    ResponseEntity<?> getSingleTraining(@PathVariable UUID id){
        Training singleTraining= trainingServices.getSingleTraining(id);
        return new ResponseEntity<>(singleTraining, HttpStatus.OK);
    }
    @GetMapping
    ResponseEntity<List<Training>> getAllTraining(){
        List<Training> trainingList= trainingServices.getTraining();
        return new ResponseEntity<>(trainingList, HttpStatus.OK);
    }
    @PutMapping("{id}")
    ResponseEntity<?> updateTraining(@PathVariable UUID id, @RequestBody TrainingInputDTO trainingInputDTO){
        Training trainingUpdate= trainingServices.updateTraining(id, trainingInputDTO);
        return  new ResponseEntity<>(trainingUpdate, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    ResponseEntity<?> deleteTraining(@PathVariable UUID id){
        trainingServices.deleteTraining(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
