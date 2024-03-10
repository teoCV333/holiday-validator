package com.taller1.holidayvalidator.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.holidayvalidator.entities.Festivo;
import com.taller1.holidayvalidator.services.FestivoService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/festivos")
public class FestivoController {

    private final FestivoService festivoService;

    public FestivoController(FestivoService festivoService) {
        this.festivoService = festivoService;
    }

    @GetMapping("/verificar/{year}/{month}/{day}")
    public String getMethodName(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        return this.festivoService.validate(year, month, day);
    }

    @GetMapping("/{year}")
    public List<Festivo> getAllHolidays(@PathVariable Integer year) {
        return this.festivoService.getAllHolidays(year);
    }

}
