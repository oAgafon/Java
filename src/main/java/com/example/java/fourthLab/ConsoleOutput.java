package com.example.java.fourthLab;

import java.util.List;

public class ConsoleOutput {
    public static void main(String[] args) {
        // Архитектура отсутствует... Юниты сделать проблематично. Без них в этот раз получается
        List<Employee> employeeList = CSVReader.readCSVFile("src/main/resources/foreign_naes.csv");

        for (int i = 0; i < 10; i++) {
            System.out.println(employeeList.get(i));
        }
    }
}
