package com.example.java.fourthLab;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@Data
public class Division {

    /**Идентификатор данного подразделения*/
    @NotNull
    private final Long id;

    /**Название данного подразделения*/
    @NotNull
    private final String name;
}
