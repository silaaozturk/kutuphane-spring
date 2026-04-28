package com.orbis.kutuphane.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitaplarDTO {
    private String ad;
    private Long yazarId;
    private Long yayineviId;
    private Long subeId;
    private int sayfaSayisi;
}
