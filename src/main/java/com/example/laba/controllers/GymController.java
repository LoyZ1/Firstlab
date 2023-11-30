package com.example.laba.controllers;

import org.springframework.web.bind.annotation.*;
import com.example.laba.models.Gym;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/gyms")
public class GymController {

    private final List<Gym> gymList;

    public GymController() {
        gymList = new ArrayList<>(List.of(new Gym(1L, "GreenGoril",
                1L)));

    }

    @GetMapping
    public List<Gym> getGym() {
        return gymList;
    }

    @GetMapping("/{gymId}")
    public Gym getGym(@PathVariable("gymId") Long GymId) {
        return gymList.stream()
                .filter(trainer -> trainer.id() == GymId)
                .findAny()
                .orElse(null);
    }
    @DeleteMapping("/{gymId}")
    public void deleteGym(@PathVariable("gymId") Long gymId) {
        gymList.remove(getGym(gymId));
    }

    @PostMapping("/add/{gymId}")
    public void createGym(@PathVariable("gymId") Long gymId, @RequestBody Gym gym) {
        if (gym != null) {
            gymList.add(gym);
        }
    }

    @PutMapping("/{gymId}")
    public void putGym(@PathVariable("gymId") Long gymId, @RequestBody Gym newgyms) {
        gymList.remove(getGym(gymId));

        if (newgyms != null) {
            gymList.add(newgyms);
        }
    }
}
