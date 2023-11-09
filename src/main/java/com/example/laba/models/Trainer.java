package com.example.laba.models;

import java.util.Date;
public record Trainer(Long Id,
                      String Name,
                      String Surname,
                      String Patronymic,
                      Boolean Gender,
                      Date Birth,
                      Integer Salary)

{}

