package com.taller1.holidayvalidator.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taller1.holidayvalidator.entities.Festivo;

@Repository
public interface FestivoRepository extends JpaRepository<Festivo, Integer>{

    @Query("SELECT f FROM Festivo f WHERE f.mes = :month AND f.dia = :day")
    Festivo findStaticHoliday(@Param("month") Integer month, @Param("day") Integer day);

    @Query("SELECT f FROM Festivo f WHERE f.diaspascua != 0")
    List<Festivo> findHolidaysByDiasPascua();

    @Query(value = "SELECT * FROM getAllHolidays(:year)", nativeQuery = true)
    List<Festivo> getAllHolidays(@Param("year") Integer year);

}
