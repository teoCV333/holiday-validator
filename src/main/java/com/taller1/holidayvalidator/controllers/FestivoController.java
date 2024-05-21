package com.taller1.holidayvalidator.controllers;

import org.springframework.web.bind.annotation.*;

import com.taller1.holidayvalidator.entities.Festivo;
import com.taller1.holidayvalidator.services.FestivoService;
import com.taller1.holidayvalidator.utils.GenericResponseModel;

import java.util.List;

import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/festivos")
public class FestivoController {

    private final FestivoService festivoService;

    public FestivoController(FestivoService festivoService) {
        this.festivoService = festivoService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/verificar/{year}/{month}/{day}")
    public ResponseEntity<String> validate(
        @PathVariable Integer year, 
        @PathVariable Integer month, 
        @PathVariable Integer day
        ) {
            try {
                String validate = festivoService.validate(year, month, day);
                return GenericResponseModel.createResponse(HttpStatus.OK, validate);
            } catch (Exception e) {
                return GenericResponseModel.createResponse(HttpStatus.INTERNAL_SERVER_ERROR, null);
            }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{year}")
    public ResponseEntity<List<Festivo>> getAllHolidays(@PathVariable Integer year) {
        if(!isNumeric(year)) {
            //CREAR UN RESPONSEMODEL QUE RETORNE ISERROR, CODEERROR, MESSAGE QUE SEA REUTILIZABLE EN TODA LA API
            //return ResponseEntity.badRequest().body(response);
        }
        List<Festivo> holidays = festivoService.getAllHolidays(year);
        return ResponseEntity.ok(holidays);
    }

    private boolean isNumeric(Integer value) {
        if(value == null) {
            return false;
        }
        try {
            Integer.parseInt(String.valueOf(value));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
