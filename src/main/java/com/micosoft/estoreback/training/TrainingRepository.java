package com.micosoft.estoreback.training;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TrainingRepository extends JpaRepository<Training, UUID> {

    Optional<Training> findBySlug(String slug);
}