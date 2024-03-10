package com.taller1.holidayvalidator.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

@Component
public class DateConverter {

    public static LocalDate parseStringToDate(String dateString, String format) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(dateString, formatter);
    }
}