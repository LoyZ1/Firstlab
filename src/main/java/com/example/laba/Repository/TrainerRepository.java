package com.example.laba.Repository;

import com.example.laba.models.Trainer;

import java.util.List;

public interface TrainerRepository {
    Trainer read(Long id);

    List<Trainer> readAll();

    void create(Trainer trainer);

    void updateTrainer(Trainer trainer, Integer trainerId);

    void deleteTrainer(Integer trainerId);
}
