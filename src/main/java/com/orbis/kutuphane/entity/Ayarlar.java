package com.orbis.kutuphane.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ayarlar")
@Getter
@Setter
public class Ayarlar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer varsayilanOduncGunu;
    private Double gunlukUcret;
    private Double gunlukCezaUcreti;
}
