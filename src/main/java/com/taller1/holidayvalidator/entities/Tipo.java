package com.taller1.holidayvalidator.entities;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "tipo")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_tipo")
    @GenericGenerator(name = "secuencia_tipo", strategy = "increment")
    private Integer id;

    @Column(length = 100, nullable = false)
    private String tipo;
}
