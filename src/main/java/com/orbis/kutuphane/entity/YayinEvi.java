package com.orbis.kutuphane.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "yayinevleri")
@Getter
@Setter
public class YayinEvi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;
    private String adres;

    @OneToMany(mappedBy = "yayinevi") // Kitaplar sınıfındaki değişken adı 'yayinevi' ise böyle olmalı
    private List<Kitaplar> kitaplar;
}