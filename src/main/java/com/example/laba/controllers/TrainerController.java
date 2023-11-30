package com.example.laba.controllers;

import org.springframework.web.bind.annotation.*;
import com.example.laba.models.Trainer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@RestController

@RequestMapping("api/trainers")
public class TrainerController {
    private final List<Trainer> trainers;

    public TrainerController(List<Trainer> trainerList) {

        try {
            String strDate1 = "14.10.2003";
            String strDate2 = "28.02.2003";
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = formatter.parse(strDate1);
            Date date2 = formatter.parse(strDate2);

            Trainer n1 = new Trainer(1L,"Alex","Nemkov","Anatolyevich",
                    true, date1, 45000);
            Trainer n2 = new Trainer(2L,"Danil","Maslov","Borisovich",
                    true, date2, 34000);

            trainers = new ArrayList<>(List.of(n1,n2));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping
    public List<Trainer> getTrainers() {
        return trainers;
    }

    @GetMapping("/{trainerId}")
    public Trainer getTrainer(@PathVariable("trainerId") Long trainerId) {
        return trainers.stream()
                .filter(trainer -> Objects.equals(trainer.id(), trainerId))
                .findAny()
                .orElse(null);
    }

    @DeleteMapping("/{trainerId}")
    public void deleteTrainer(@PathVariable("trainerId") Long trainerId) {
        trainers.remove(getTrainer(trainerId));
    }

    @PostMapping("add/{trainerId}")
    public Trainer addTrainer(@RequestBody Trainer newTrainers) {
        trainers.add(newTrainers);
        return newTrainers;
    }

    @PutMapping("/{trainerId}")
    public void putTrainer(@PathVariable("trainerId") Long trainerId, @RequestBody Trainer newTrainers) {
        trainers.remove(getTrainer(trainerId));
        if (newTrainers != null) {
            trainers.add(newTrainers);

        }
    }
}


