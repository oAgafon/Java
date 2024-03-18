package com.example.java.fourthLab;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Перечисление хранящее традиционные гендеры
 */
@RequiredArgsConstructor
@Getter
@ToString
public enum Gender {

    MALE("Male"),

    FEMALE("Female"),

    ;

    @NotNull
    private final String value;

    public static @NotNull Gender getGender(String value) {
        return Arrays.stream(values())
                .filter(gender -> gender.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("There is no such gender in Russia"));
    }

}
