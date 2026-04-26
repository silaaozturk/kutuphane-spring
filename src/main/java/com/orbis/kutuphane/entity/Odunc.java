package com.orbis.kutuphane.entity;

import com.orbis.kutuphane.GENEL;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "odunc")
@Getter
@Setter
public class Odunc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Hangi kitap kopyası
    @ManyToOne
    @JoinColumn(name = "kitap_kopya_id", nullable = false)
    private KitapKopya kitapKopya;

    // Hangi kullanıcı
    @ManyToOne
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;

    private LocalDate alisTarihi;
    private LocalDate iadeTarihi;
    private LocalDate gercekIadeTarihi;

    @Enumerated(EnumType.STRING)
    private GENEL.OduncDurum durum;

    private Double cezaTutari;


}