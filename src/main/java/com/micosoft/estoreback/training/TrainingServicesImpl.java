package com.micosoft.estoreback.training;

import com.micosoft.estoreback.categories.Category;
import com.micosoft.estoreback.categories.CategoryRepository;
import com.micosoft.estoreback.errors.exceptions.NotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class TrainingServicesImpl implements TrainingServices{
    @Autowired
    private final TrainingRepository trainingRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public Training createTraining(TrainingInputDTO trainingInputDTO) {
        Training training= Training.builder()
                .title(trainingInputDTO.getTitle())
                .slug(trainingInputDTO.getSlug())
                .description(trainingInputDTO.getDescription())
                .content(trainingInputDTO.getContent())
                .thumbnail(trainingInputDTO.getThumbnail())
                .isPublished(trainingInputDTO.getIsPublished())
                .build();
        Category category= categoryRepository.findById(trainingInputDTO.getCategories()).orElse(null);
        if(category!=null){
            training.setCategory(category);
        }
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> getTraining() {
        return trainingRepository.findAll();
    }

    @Override
    public Training getSingleTraining(UUID id) {
        return trainingRepository.findById(id).orElseThrow(()->new NotFound("Training not Found"));
    }

    @Override
    public Training updateTraining(UUID id, TrainingInputDTO trainingInputDTO) {
        Training trainingDb = trainingRepository.findById(id).orElseThrow(()-> new NotFound("Training not found"));
        if (!trainingInputDTO.getTitle().isBlank()&&!trainingInputDTO.getTitle().equals(trainingDb.getTitle())){
            trainingDb.setTitle(trainingInputDTO.getTitle());
        }
        if (!trainingInputDTO.getSlug().isBlank()&&!trainingInputDTO.getSlug().equals(trainingDb.getSlug())){
            trainingDb.setTitle(trainingInputDTO.getSlug());
        }
        if (!trainingInputDTO.getDescription().isBlank()&&!trainingInputDTO.getDescription().equals(trainingDb.getDescription())){
            trainingDb.setDescription(trainingInputDTO.getDescription());
        }
        if (!trainingInputDTO.getContent().isBlank()&&!trainingInputDTO.getContent().equals(trainingDb.getContent())){
            trainingDb.setContent(trainingInputDTO.getContent());
        }
        if (!trainingInputDTO.getThumbnail().isBlank()&&!trainingInputDTO.getThumbnail().equals(trainingDb.getThumbnail())){
            trainingDb.setThumbnail(trainingInputDTO.getThumbnail());
        }
        if (!trainingInputDTO.getIsPublished().equals(trainingDb.getIsPublished())){
            trainingDb.setIsPublished(trainingInputDTO.getIsPublished());
        }
        trainingDb.setUpdatedAt();
        return trainingRepository.save(trainingDb);
    }

    @Override
    public void deleteTraining(UUID id) {
        Training trainingDb = trainingRepository.findById(id).orElseThrow(()-> new NotFound("Training not Found"));
        trainingRepository.delete(trainingDb);
    }
}
