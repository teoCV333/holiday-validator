package com.taller1.holidayvalidator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taller1.holidayvalidator.entities.Festivo;

@Repository
public interface FestivoRepository extends JpaRepository<Festivo, Integer>{

    @Query(value = "SELECT * FROM getAllHolidays(:year)", nativeQuery = true)
    List<Festivo> getAllHolidays(@Param("year") Integer year);

}
