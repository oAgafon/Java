package com.example.java.fourthLab;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@ToString
public class Employee {

    /**Идентификационный номер сотрудника*/
    @NotNull
    private final Long id;

    /**Имя сотрудника*/
    @NotNull
    private final String name;

    /**Гендер сотрудника*/
    @NotNull
    private final Gender gender;

    /**Дата рождения сотрудника*/
    @NotNull
    private final LocalDate birthDate;

    /**Подразделение сотрудника*/
    @NotNull
    private final Division division;

    /**Зарплата сотрудника*/
    @NotNull
    private final BigDecimal salary;

}
