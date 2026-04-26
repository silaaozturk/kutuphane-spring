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

    @OneToMany(mappedBy = "yayinevi")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Kitaplar> kitaplar;
}