package com.example.laba.controllers;

import com.example.laba.Repository.TrainerRepository;
import com.example.laba.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.laba.models.Trainer;

import java.util.List;


@RestController

@RequestMapping("api/trainers")
public class TrainerController {
    private final TrainerRepository trainerRepository;


    @Autowired
    public TrainerController(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;

    }
    @GetMapping
    public List<Trainer> getTrainers() {
        return trainerRepository.readAll();
    }

    @GetMapping("/{trainerId}")
    public Trainer getTrainer(@PathVariable("trainerId") Long trainerId) {
       return trainerRepository.read(trainerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Trainer trainer) {
        trainerRepository.create(trainer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{trainerId}")
    public void updateTrainer(@RequestBody Trainer trainer, @PathVariable("trainerId") int trainerId) {
        trainerRepository.updateTrainer(trainer, trainerId);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{trainerId}")
    public void deleteMember(@PathVariable("trainerId") int trainerId) {
        trainerRepository.deleteTrainer(trainerId);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleException(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}


