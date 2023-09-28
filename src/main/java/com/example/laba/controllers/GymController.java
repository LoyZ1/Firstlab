package com.example.laba.controllers;

import org.springframework.web.bind.annotation.*;
import com.example.laba.models.Gym;

import java.util.List;

@RestController
@RequestMapping("api/trainers/{trainer_id}/gyms")
public class GymController {

    private final List<Gym> gymList;

    public GymController() {
        gymList = List.of(new Gym(1L, 1L, "Gymnasium", 40000),
                new Gym(2L, 2L, "Kpd", 38000));
    }

    @GetMapping()
    public List<Gym> getGymList(@PathVariable("Trainer_id") Long Trainer_id) {
        return gymList.stream()
                .filter(gym -> gym.Trainer_id().equals(Trainer_id))
                .toList();
    }
}
