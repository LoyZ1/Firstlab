package com.example.laba.Repository;

import com.example.laba.models.Gym;

import java.util.List;
public interface GymRepository {
    Gym read(Long id);

    List<Gym> readAll();

    void createGym(Gym gym);

    void updateGym(Gym gym, Integer gymId);

    void deleteGym(Integer gymId);
}
