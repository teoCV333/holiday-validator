package com.taller1.holidayvalidator.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

@Component
public class DateConverter {

    public static LocalDate convertToLocalDate(int year, int month, int day) throws DateTimeParseException {
        return LocalDate.of(year, month, day);
    }

}