package com.taller1.holidayvalidator.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taller1.holidayvalidator.entities.Festivo;
import com.taller1.holidayvalidator.interfaces.IFestivoService;
import com.taller1.holidayvalidator.repositories.FestivoRepository;
import com.taller1.holidayvalidator.utils.DateConverter;

@Service
public class FestivoService implements IFestivoService {

    private final FestivoRepository festivoRepo;
    private final DateConverter dateConverter;

    public FestivoService(FestivoRepository festivoRepo, DateConverter dateConverter) {
        this.festivoRepo = festivoRepo;
        this.dateConverter = dateConverter;
    }

    @Override
    public String validate(Integer year, Integer month, Integer day) {
        String dateString = year + "-" + month + "-" + day;
        if (!isValid(year, month, day)) {
            return "Fecha no valida";
        }
        if (validateHoliday(year, month, day)) {
            return "festivo!!";
        } else {
            return "no es festivo!!";
        }
    }

    public boolean isValid(Integer year, Integer month, Integer day) {
        try {
            dateConverter.convertToLocalDate(year, month, day);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Festivo> getAllHolidays(Integer year) {
        List<Festivo> holidays = festivoRepo.getAllHolidays(year);
        return holidays;
    }

    public Boolean validateHoliday(Integer year, Integer month, Integer day) {
        List<Festivo> holidays = getAllHolidays(year);
        Boolean result = false;
        for (Festivo holiday : holidays) {
            if (holiday.getMes() == month && holiday.getDia() == day) {
                result = true;
                break;
            }
        }
        return result;
    }
}
