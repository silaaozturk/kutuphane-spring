package com.orbis.kutuphane.entity;

import com.orbis.kutuphane.GENEL;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "kullanicilar")
@Data
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ad", nullable = false)
    private String ad;

    @Column(name = "soyad", nullable = false)
    private String soyad;

    @Column(name = "eposta", unique = true, nullable = false)
    private String eposta;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "sifre", nullable = false)
    private String sifre;

    @Column(name = "rol")
    @Enumerated(EnumType.ORDINAL)
    private GENEL.Rol rol;

    @Column(name = "yasakli")
    private boolean yasakli = false;
}