package com.example.java.fourthLab;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class CSVReader {
    public static List<Employee> readCSVFile(String filePath) {
        List<Employee> employeeList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try {
            Reader reader = new FileReader(filePath);
            CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(';').parse(reader);

            boolean skipHeader = true;
            for (CSVRecord csvRecord : csvParser) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                try {
                    employeeList.add(
                            Employee.builder()
                                    .id(Long.valueOf(csvRecord.get(0)))
                                    .name(csvRecord.get(1))
                                    .gender(Objects.requireNonNull(Gender.getGender(csvRecord.get(2))))
                                    .birthDate(LocalDate.parse(csvRecord.get(3), formatter))
                                    .division(new Division(csvRecord.getRecordNumber(), csvRecord.get(4)))
                                    .salary(new BigDecimal(csvRecord.get(5)))
                                    .build()
                    );
                } catch (IllegalStateException isex) {
                    log.warn("An employee {} with a non-existent gender ({}) was found.", csvRecord.get(1), csvRecord.get(2));
                }
            }

            csvParser.close();
            reader.close();
        } catch (Exception ex) {
            log.error("Can`t process file: {}", filePath, ex);
        }

        return employeeList;
    }

}
