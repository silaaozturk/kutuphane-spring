package com.orbis.kutuphane.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "yazarlar")
@Getter
@Setter
public class Yazar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;
    private String soyad;

    @OneToMany(mappedBy = "yazar")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Kitaplar> kitaplar;

}
