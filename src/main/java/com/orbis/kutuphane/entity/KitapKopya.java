package com.orbis.kutuphane.entity;

import com.orbis.kutuphane.GENEL;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "kitap_kopyalari")
@Getter
@Setter
public class KitapKopya {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String barkodNo;

    private boolean mevcut;

    @ManyToOne
    @JoinColumn(name = "kitap_id")
    private Kitaplar kitap;

    @ManyToOne
    @JoinColumn(name = "sube_id")
    private Sube sube;

    @Enumerated(EnumType.STRING)
    private GENEL.KitapDurum durum;
}