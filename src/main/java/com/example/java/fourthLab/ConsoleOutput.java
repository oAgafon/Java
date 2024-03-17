package com.example.java.fourthLab;

import java.util.List;

public class ConsoleOutput {
    public static void main(String[] args) {

        List<Employee> employeeList = CSVReader.readCSVFile("src/main/resources/foreign_names.csv");

        for (int i = 0; i < 10; i++) {
            System.out.println(employeeList.get(i));
        }
    }
}
