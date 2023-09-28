package com.example.laba.controllers;

import org.springframework.web.bind.annotation.*;
import com.example.laba.models.Trainer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/trainer")


public class TrainerController {
    private final List<Trainer> trainerLists;

    public TrainerController() {
        try {
            String strDate1 = "11.03.2003";
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = formatter.parse(strDate1);

            String strDate2 = "17.12.1981";
            Date date2 = formatter.parse(strDate2);

            Trainer u1 = new Trainer(1L,"Маслов","Данил","Борисович", date1, 3);
            Trainer u2 = new Trainer(2L,"Душинский","Александр","Анатольевич", date2, 15);


            trainerLists = List.of(u1, u2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Trainer> getTrainers() {
        return trainerLists;
    }

    @GetMapping("/{trainer_id}")
    public Trainer getTrainer(@PathVariable("Trainer_id") Long Trainer_id) {
        return trainerLists.stream()
                .filter(trainer -> trainer.id().equals(Trainer_id))
                .findAny()
                .orElse(null);
    }
}

