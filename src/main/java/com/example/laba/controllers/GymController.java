package com.example.laba.controllers;

import com.example.laba.Repository.GymRepository;
import com.example.laba.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.laba.models.Gym;


import java.util.List;


@RestController
@RequestMapping("api/gyms")
public class GymController {

    private final GymRepository gymRepository;


    @Autowired
    public GymController(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @GetMapping
    public List<Gym> getGym() {
        return gymRepository.readAll();
    }

    @GetMapping("/{gymId}")
    public Gym getGym(@PathVariable("gymId") Long gymId) {
        return gymRepository.read(gymId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void Create(@RequestBody Gym gym) {
        gymRepository.createGym(gym);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{gymId}")
    public void updateGym(@RequestBody Gym gym, @PathVariable("gymId") int gymId) {
        gymRepository.updateGym(gym, gymId);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{gymId}")
    public void deleteGym(@PathVariable("gymId") int gymId) {
        gymRepository.deleteGym(gymId);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleException(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
