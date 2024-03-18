package com.example.java.fourthLab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVReaderTest {

    private List<Employee> employeeList;

    @BeforeEach
    void setup() {
        employeeList = CSVReader.readCSVFile("src/main/resources/foreign_names.csv");
    }

    @Test
    void positive() {
        assertEquals(25897, employeeList.size());
    }

}
