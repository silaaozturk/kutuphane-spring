package com.orbis.kutuphane.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "kitaplar", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ad", "yayinevi_id"})
})
@Getter
@Setter
public class Kitaplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;

    @ManyToOne
    @JoinColumn(name = "yazar")
    private Yazar yazar;

    @ManyToOne
    @JoinColumn(name = "yayinevleri")
    private YayinEvi yayinevi;

    private int sayfaSayisi;

}