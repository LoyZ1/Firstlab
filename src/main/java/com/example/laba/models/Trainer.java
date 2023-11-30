package com.example.laba.models;

import java.util.Date;
public record Trainer(Long id,
                      String name,
                      String surname,
                      String patronymic,
                      Boolean gender,
                      Date birth,
                      Integer salary)

{}

