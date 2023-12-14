package com.example.laba.models;

import java.util.Date;
public record Trainer(Long id,
                      String name,
                      String surname,
                      String patronymic,
                      boolean gender,
                      Date birth,
                      Integer salary)

{}

