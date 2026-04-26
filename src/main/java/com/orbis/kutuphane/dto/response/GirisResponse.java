package com.orbis.kutuphane.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GirisResponse {
    private String ad;
    private String soyad;
    private Long id;
    private int rol;
    private String mesaj;
}