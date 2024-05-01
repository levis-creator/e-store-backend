package com.micosoft.estoreback.training;

import java.util.List;
import java.util.UUID;

public interface TrainingServices {
    Training createTraining(TrainingInputDTO trainingInputDTO);
    List<Training> getTraining();
    Training getSingleTraining(UUID id);
    Training updateTraining(UUID id, TrainingInputDTO trainingInputDTO);
    void  deleteTraining(UUID id);
}
