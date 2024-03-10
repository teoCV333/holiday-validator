package com.taller1.holidayvalidator.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
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
        if (!isValid(dateString)) {
            return "Fecha no valida";
        }
        if (!!validateHoliday(year, month, day)) {
            return "festivo!!";
        } else {
            return "no es festivo!!";
        }
    }

    public boolean isValid(String date) {
        try {
            dateConverter.parseStringToDate(date, "yyyy/MM/dd");
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
            if(holiday.getMes() == month && holiday.getDia() == day) {
                result = true;
                break;
            }
        }
        return result;
    }

    /* public boolean isValid(String date) {
        try {
            dateConverter.parseStringToDate(date, "yyyy/MM/dd");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Festivo isStaticHoliday(Integer year, Integer month, Integer day) {
        try {
            Festivo festivo = this.festivoRepo.findStaticHoliday(month, day);
            return festivo;
        } catch (Exception e) {
            return null;
        }
    }

    public Festivo isDinamicHoliday(Integer year, Integer month, Integer day) {
        try {
            String dateForConvert = validateZeros(year, month, day);
            LocalDate dateForValidate = DateConverter.parseStringToDate(dateForConvert, "yyyy/MM/dd");
            LocalDate pascuaInit = pascuaInit(year);
            List<Festivo> festivosByDiasPascua = this.festivoRepo.findHolidaysByDiasPascua();
            Festivo result = null;
            for (Festivo x : festivosByDiasPascua) {
                LocalDate pascuaPlusDays = pascuaInit.plusDays(x.getDiaspascua());
                if (dateForValidate.isEqual(pascuaPlusDays) && x.getIdtipo() == 3) {
                    result = x;
                    break;
                } else if (nextMonday(dateForValidate).isEqual(pascuaPlusDays) && x.getIdtipo() == 4) {
                    result = x;
                    break;
                }
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public LocalDate pascuaInit(Integer year) {
        Integer pascuaMonth = 3;
        Integer pascuaDay = 15;
        Integer a = year % 19;
        Integer b = year % 4;
        Integer c = year % 7;
        Integer d = (19 * a + 24) % 30;
        Integer days = d + (2 * b + 4 * c + 6 * d + 5) % 7;
        LocalDate pascua = LocalDate.of(year, pascuaMonth, pascuaDay);
        LocalDate pascuaInit = pascua.plusDays(days + 7);
        return pascuaInit;
    }

    public String validateZeros(Integer year, Integer month, Integer day) {

        String dateString = null;

        if (day < 10 && month < 10) {
            dateString = year + "/0" + month + "/0" + day;
        } else if (day < 10 && month > 9) {
            dateString = year + "/" + month + "/0" + day;
        } else if (month < 10 && day > 9) {
            dateString = year + "/0" + month + "/" + day;
        } else {
            dateString = year + "/" + month + "/" + day;
        }
        return dateString;
    }

    public LocalDate nextMonday(LocalDate date) {
        if (date.getDayOfWeek().getValue() > DayOfWeek.MONDAY.getValue()) {
            date = date.plusDays(9 - date.getDayOfWeek().getValue());
        } else if (date.getDayOfWeek().getValue() < DayOfWeek.MONDAY.getValue()) {
            date = date.plusDays(1);
        }
        return date;
    } */
}
